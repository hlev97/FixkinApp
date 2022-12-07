package hu.bme.aut.it9p0z.network

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.bme.aut.it9p0z.network.TestResponse.getAllConditionLogs
import hu.bme.aut.it9p0z.network.api.FixkinApiClient
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.network.util.MoshiAdapters
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class NetworkDatasourceTest {

    private lateinit var datasource: NetworkDatasource

    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var moshi: Moshi
    private lateinit var api: FixkinApiClient

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .add(MoshiAdapters)
            .build()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(FixkinApiClient::class.java)

        datasource = NetworkDatasourceImpl(api)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get all condition logs for user, returns results successfully`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(getAllConditionLogs)
        )

        val result = datasource.getAllConditionLogs("test_user","test_pass")
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()?.size).isEqualTo(10)
    }

    @Test
    fun `Get all condition logs for user, unauthorized`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(401)
        )

        val result = datasource.getAllConditionLogs("random_user","random_pass")
        assertThat(result.isFailure).isTrue()
    }

}