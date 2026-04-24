package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.entities.variable.Config
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import javax.inject.Inject
import kotlin.test.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class ISendConfigTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var usecase: SendConfig

    @Test
    fun check_return_failure_if_not_have_data() =
        runTest {

            hiltRule.inject()

            val result = usecase("16997417840", "12345", "1.00")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISendConfig -> IConfigRepository.send -> IConfigRetrofitDatasource.recoverToken"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.net.ConnectException: Failed to connect to localhost/127.0.0.1:8080"
            )
        }

    @Test
    fun check_return_failure_if_response_is_404() =
        runTest {
            val server = MockWebServer()
            server.start(8080)
            server.enqueue(
                MockResponse().setResponseCode(404)
            )

            hiltRule.inject()

            val result = usecase(
                number = "16997417840",
                password = "12345",
                version = "1.00"
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISendConfig -> IConfigRepository.send -> IConfigRetrofitDatasource.recoverToken"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NullPointerException"
            )
            server.shutdown()
        }

    @Test
    fun check_return_failure_if_input_data_config_is_incorrect() =
        runTest {
            val server = MockWebServer()
            server.start(8080)
            server.enqueue(
                MockResponse().setBody("""{"idServ":1}""")
            )

            hiltRule.inject()

            val result = usecase(
                number = "16997417840a",
                password = "12345",
                version = "1.00"
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISendConfig -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"16997417840a\""
            )
            server.shutdown()
        }

    @Test
    fun check_return_failure_if_service_return_empty() =
        runTest {
            val server = MockWebServer()
            server.start(8080)
            server.enqueue(MockResponse().setBody(""))

            hiltRule.inject()

            val result = usecase(
                number = "16997417840",
                password = "12345",
                version = "1.00"
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISendConfig -> IConfigRepository.send -> IConfigRetrofitDatasource.recoverToken"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.io.EOFException: End of input at line 1 column 1 path \$"
            )
            server.shutdown()
        }


    @Test
    fun check_return_correct_if_process_execute_successfully() =
        runTest {

            val server = MockWebServer()
            server.start(8080)
            server.enqueue(
                MockResponse().setBody("""{"idServ":1}""")
            )

            hiltRule.inject()

            val result = usecase(
                number = "16997417840",
                password = "12345",
                version = "1.00"
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                Config(
                    idServ = 1
                )
            )
            server.shutdown()
        }

}