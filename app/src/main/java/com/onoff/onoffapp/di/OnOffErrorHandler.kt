
import com.onoff.onoffapp.util.FlagConstants.API_CIERRE
import com.onoff.onoffapp.util.FlagConstants.API_CONEXION
import com.onoff.onoffapp.util.FlagConstants.API_SERVE
import com.onoff.onoffapp.util.FlagConstants.API_TIME
import com.onoff.onoffapp.util.FlagConstants.OK
import okhttp3.*
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class OnOffErrorHandler : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        try {
            val response = chain.proceed(request)
            val codigo = response.code()
            val bodyString = response.body()!!.string()
            return when (codigo) {
                OK -> response.newBuilder()
                    .body(ResponseBody.create(response.body()?.contentType(), bodyString))
                    .build()

                else -> Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(response.code())
                    .message(bodyString.toString())
                    .body(ResponseBody.create(null, "{${response.message()}}")).build()
            }

        } catch (e: Exception) {

            e.printStackTrace()
            val msg = when (e) {
                is SocketTimeoutException -> API_TIME
                is UnknownHostException -> API_CONEXION
                is ConnectionShutdownException -> API_CIERRE
                is IOException -> API_SERVE
                is IllegalStateException -> "${e.message}"
                else -> "${e.message}"

            }

            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body(ResponseBody.create(null, "{${e}}")).build()
        }

    }
}
