package br.com.usinasantafe.cav.domain.usecases.background

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import androidx.work.testing.TestListenableWorkerBuilder
import br.com.usinasantafe.cav.domain.usecases.card.HasSendCard
import br.com.usinasantafe.cav.domain.usecases.card.SendCard
import br.com.usinasantafe.cav.domain.usecases.config.GetConfig
import br.com.usinasantafe.cav.domain.usecases.config.SetStatusSend
import br.com.usinasantafe.cav.lib.StatusSend
import br.com.usinasantafe.cav.presenter.model.ConfigModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ProcessWorkManagerTest {

    private lateinit var context: Context
    private val getConfig = mock<GetConfig>()
    private val setStatusSend = mock<SetStatusSend>()
    private val hasSendCard = mock<HasSendCard>()
    private val sendCard = mock<SendCard>()

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    private fun createWorker(): ProcessWorkManager {
        return TestListenableWorkerBuilder<ProcessWorkManager>(context)
            .setWorkerFactory(object : WorkerFactory() {
                override fun createWorker(
                    appContext: Context,
                    workerClassName: String,
                    workerParameters: WorkerParameters
                ): ListenableWorker {
                    return ProcessWorkManager(
                        appContext,
                        workerParameters,
                        getConfig,
                        setStatusSend,
                        hasSendCard,
                        sendCard
                    )
                }
            })
            .build()
    }

    @Test
    fun `doWork - Check return success if getConfig returns failure`() = runTest {
        whenever(getConfig()).thenReturn(resultFailure("GetConfig", Exception()))
        val result = createWorker().doWork()
        assertEquals(ListenableWorker.Result.success(), result)
    }

    @Test
    fun `doWork - Check return success if getConfig returns null`() = runTest {
        whenever(getConfig()).thenReturn(Result.success(null))
        val result = createWorker().doWork()
        assertEquals(ListenableWorker.Result.success(), result)
    }

    @Test
    fun `doWork - Check return success if hasSendCard returns failure`() = runTest {
        whenever(getConfig()).thenReturn(Result.success(ConfigModel("123", "456")))
        whenever(hasSendCard()).thenReturn(resultFailure("HasSendCard", Exception()))
        val result = createWorker().doWork()
        assertEquals(ListenableWorker.Result.success(), result)
    }

    @Test
    fun `doWork - Check return success if hasSendCard returns false`() = runTest {
        whenever(getConfig()).thenReturn(Result.success(ConfigModel("123", "456")))
        whenever(hasSendCard()).thenReturn(Result.success(false))
        val result = createWorker().doWork()
        assertEquals(ListenableWorker.Result.success(), result)
    }

    @Test
    fun `doWork - Check return retry if sendCard returns failure (Exception)`() = runTest {
        whenever(getConfig()).thenReturn(Result.success(ConfigModel("123", "456")))
        whenever(hasSendCard()).thenReturn(Result.success(true))
        whenever(setStatusSend(any())).thenReturn(Result.success(Unit))
        whenever(sendCard()).thenReturn(resultFailure("SendCard", Exception()))
        
        val result = createWorker().doWork()
        
        verify(setStatusSend).invoke(StatusSend.SEND)
        assertEquals(ListenableWorker.Result.retry(), result)
    }

    @Test
    fun `doWork - Check return retry if sendCard returns false`() = runTest {
        whenever(getConfig()).thenReturn(Result.success(ConfigModel("123", "456")))
        whenever(hasSendCard()).thenReturn(Result.success(true))
        whenever(setStatusSend(any())).thenReturn(Result.success(Unit))
        whenever(sendCard()).thenReturn(Result.success(false))
        
        val result = createWorker().doWork()
        
        assertEquals(ListenableWorker.Result.retry(), result)
    }

    @Test
    fun `doWork - Check return success if all functions execute successfully`() = runTest {
        whenever(getConfig()).thenReturn(Result.success(ConfigModel("123", "456")))
        whenever(hasSendCard()).thenReturn(Result.success(true))
        whenever(setStatusSend(any())).thenReturn(Result.success(Unit))
        whenever(sendCard()).thenReturn(Result.success(true))
        
        val result = createWorker().doWork()
        
        verify(setStatusSend).invoke(StatusSend.SEND)
        verify(sendCard).invoke()
        verify(setStatusSend).invoke(StatusSend.SENT)
        assertEquals(ListenableWorker.Result.success(), result)
    }

    @Test
    fun `doWork - Check return success if setStatusSend(SENT) fails but sendCard was success`() = runTest {
        whenever(getConfig()).thenReturn(Result.success(ConfigModel("123", "456")))
        whenever(hasSendCard()).thenReturn(Result.success(true))
        whenever(setStatusSend(StatusSend.SEND)).thenReturn(Result.success(Unit))
        whenever(sendCard()).thenReturn(Result.success(true))
        whenever(setStatusSend(StatusSend.SENT)).thenReturn(resultFailure("SetStatusSend", Exception()))
        
        val result = createWorker().doWork()
        
        assertEquals(ListenableWorker.Result.success(), result)
    }
}
