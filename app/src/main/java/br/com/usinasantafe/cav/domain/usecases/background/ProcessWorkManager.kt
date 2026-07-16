package br.com.usinasantafe.cav.domain.usecases.background

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import br.com.usinasantafe.cav.domain.usecases.card.HasSendCard
import br.com.usinasantafe.cav.domain.usecases.card.SendCard
import br.com.usinasantafe.cav.domain.usecases.config.GetConfig
import br.com.usinasantafe.cav.domain.usecases.config.SetStatusSend
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.handleFailure
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ProcessWorkManager @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getConfig: GetConfig,
    private val setStatusSend: SetStatusSend,
    private val hasSendCard: HasSendCard,
    private val sendCard: SendCard,
): CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {

        val config = getConfig().getOrElse {
            handleFailure(it, getClassAndMethod())
            return Result.success()
        }
        if (config == null) return Result.success()

        val hasCard = hasSendCard().getOrElse {
            handleFailure(it, getClassAndMethod())
            return Result.success()
        }
        if (!hasCard) return Result.success()

        setStatusSend(StatusSend.SEND).onFailure {
            handleFailure(it, getClassAndMethod())
        }

        val sendSuccess = sendCard().getOrElse {
            handleFailure(it, getClassAndMethod())
            return Result.retry()
        }

        if(!sendSuccess) return Result.retry()

        setStatusSend(StatusSend.SENT).onFailure {
            handleFailure(it, getClassAndMethod())
        }

        return Result.success()
    }
}