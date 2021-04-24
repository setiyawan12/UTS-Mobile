package yayang.setiyawan.utsmobile.webservices

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import yayang.setiyawan.utsmobile.models.Barang
import yayang.setiyawan.utsmobile.models.User
import yayang.setiyawan.utsmobile.utilities.Utils
import java.util.concurrent.TimeUnit

class Api {
    companion object{
        private var retrofit: Retrofit?=null

        private var okHttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun instance(): APIServices = getClient()
            .create(APIServices::class.java)

        private fun getClient(): Retrofit {
            return if(retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(
                    Utils.API_ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }
    }
}

interface APIServices{
@FormUrlEncoded
@POST("auth/login")
fun login(
    @Field("email") email: String?=null,
    @Field("password") password: String?=null
):Call<WrappedResponse<User>>

@GET("barang")
fun get(@Header("x-access-token")token: String): Call<WrappedListResponse<Barang>>
    @FormUrlEncoded
    @POST("barang")
    fun createData(
        @Header("x-access-token") token :String,
        @Field("name") name: String? = null,
        @Field("location") location: String? = null,
        @Field("description") description: String? = null
    ): Call<WrappedResponse<Barang>>
    @FormUrlEncoded
    @PUT("barang/{id}")
    fun updateData(
            @Path("id") id: Int,
            @Header("x-access-token") token: String,
            @Field("name") name: String? = null,
            @Field("location") location: String? = null,
            @Field("description") description: String? = null
    ) : Call<WrappedResponse<Barang>>
    @DELETE("barang/{id}")
    fun deleteData(
            @Path("id") id: Int,
            @Header("x-access-token") token : String
    ) : Call<WrappedResponse<Barang>>
    @POST("api/register")
    fun register(
        @Field("name") name: String? = null,
        @Field("email") email: String?= null,
        @Field("password") password: String?= null,
        @Field("conf_password ") conf_password: String? = null
    ):Call<WrappedResponse<User>>
}
data class WrappedResponse<T>(
    @SerializedName("message") var message: String?,
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") var data : T?
){
    constructor() : this(null, false, null)
}
data class WrappedListResponse<T>(
    @SerializedName("message") var message: String?,
    @SerializedName("status") var status: Boolean,
    @SerializedName("data") var data : List<T>
){
    constructor() : this(null, false, listOf())
}