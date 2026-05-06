package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.di.external.ApiModuleTest.provideRetrofitTest
import br.com.usinasantafe.cav.external.retrofit.api.stable.OptionDataLocalApi
import br.com.usinasantafe.cav.infra.models.retrofit.stable.OptionDataLocalRetrofitModel
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import kotlin.test.assertEquals

class IOptionDataLocalRetrofitDatasourceTest {

    @Test
    fun `Check return failure if token is invalid`() =
        runTest {
            val server = MockWebServer()
            server.start()
            server.enqueue(
                MockResponse().setBody("{ error : Authorization header is missing }")
            )
            val retrofit = provideRetrofitTest(
                server.url("").toString()
            )
            val service = retrofit.create(OptionDataLocalApi::class.java)
            val datasource = IOptionDataLocalRetrofitDatasource(service)
            val result = datasource.listAll("TOKEN")
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IOptionDataLocalRetrofitDatasource.listAll",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                "java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path \$",
                result.exceptionOrNull()!!.cause.toString()
            )
            server.shutdown()
        }

    @Test
    fun `Check return failure if have Error 404`() =
        runTest {
            val server = MockWebServer()
            server.start()
            server.enqueue(
                MockResponse().setResponseCode(404)
            )
            val retrofit = provideRetrofitTest(
                server.url("").toString()
            )
            val service = retrofit.create(OptionDataLocalApi::class.java)
            val datasource = IOptionDataLocalRetrofitDatasource(service)
            val result = datasource.listAll("TOKEN")

            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IOptionDataLocalRetrofitDatasource.listAll",
                result.exceptionOrNull()!!.message
            )
            assertEquals(
                NullPointerException().toString(),
                result.exceptionOrNull()!!.cause.toString()
            )
            server.shutdown()
        }

    @Test
    fun `Check return correct`() =
        runTest {
            val response = """
                [
                  {"id":1,"desc":"TEST1"},
                  {"id":2,"desc":"TEST2"}
                ]
            """.trimIndent()
            val server = MockWebServer()
            server.start()
            server.enqueue(
                MockResponse().setBody(response)
            )
            val retrofit = provideRetrofitTest(
                server.url("").toString()
            )
            val service = retrofit.create(OptionDataLocalApi::class.java)
            val datasource = IOptionDataLocalRetrofitDatasource(service)
            val result = datasource.listAll("TOKEN")

            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                Result.success(
                    listOf(
                        OptionDataLocalRetrofitModel(
                            id = 1,
                            desc = "TEST1"
                        ),
                        OptionDataLocalRetrofitModel(
                            id = 2,
                            desc = "TEST2"
                        )
                    )
                ),
                result
            )
            server.shutdown()
        }

}