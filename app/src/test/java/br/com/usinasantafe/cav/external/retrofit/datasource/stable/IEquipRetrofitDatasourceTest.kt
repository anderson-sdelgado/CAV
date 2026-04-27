package br.com.usinasantafe.cav.external.retrofit.datasource.stable

import br.com.usinasantafe.cav.di.external.ApiModuleTest.provideRetrofitTest
import br.com.usinasantafe.cav.external.retrofit.api.stable.EquipApi
import br.com.usinasantafe.cav.infra.models.retrofit.stable.EquipRetrofitModel
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import kotlin.test.assertEquals

class IEquipRetrofitDatasourceTest {

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
            val service = retrofit.create(EquipApi::class.java)
            val datasource = IEquipRetrofitDatasource(service)
            val result = datasource.listAll("TOKEN")
            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IEquipRetrofitDatasource.listAll",
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
            val service = retrofit.create(EquipApi::class.java)
            val datasource = IEquipRetrofitDatasource(service)
            val result = datasource.listAll("TOKEN")

            assertEquals(
                true,
                result.isFailure
            )
            assertEquals(
                "IEquipRetrofitDatasource.listAll",
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
                  {"id":1,"nro":10,"desc":"TRATOR"},
                  {"id":2,"nro":20,"desc":"CAMINHAO"}
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
            val service = retrofit.create(EquipApi::class.java)
            val datasource = IEquipRetrofitDatasource(service)
            val result = datasource.listAll("TOKEN")

            assertEquals(
                true,
                result.isSuccess
            )
            assertEquals(
                Result.success(
                    listOf(
                        EquipRetrofitModel(
                            id = 1,
                            nro = 10,
                            desc = "TRATOR"
                        ),
                        EquipRetrofitModel(
                            id = 2,
                            nro = 20,
                            desc = "CAMINHAO"
                        )
                    )
                ),
                result
            )
            server.shutdown()
        }
}