package yayang.setiyawan.utsmobile.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayang.setiyawan.utsmobile.contracts.LoginActivityContract
import yayang.setiyawan.utsmobile.models.User
import yayang.setiyawan.utsmobile.utilities.Utils
import yayang.setiyawan.utsmobile.webservices.Api
import yayang.setiyawan.utsmobile.webservices.WrappedResponse

class LoginActivityPresenter(v: LoginActivityContract.View?):LoginActivityContract.Interaction {
    private var view:LoginActivityContract.View? =v
    private var api = Api.instance()
    override fun validate(id: String, password: String): Boolean {
        view?. passwordError(null)
        if (!Utils.isValidPassword(password)){
            view?.passwordError("Password Tidak Valid")
            return false
        }
        return true
    }
    override fun login(email: String, password: String) {
        view?.isLoading(true)
        api.login(email,password).enqueue(object : Callback<WrappedResponse<User>>{
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.toast("koneksi tidak bisa")
                view?.notConnect()
            }
            override fun onResponse(
                call: Call<WrappedResponse<User>>,
                response: Response<WrappedResponse<User>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body !=null && body.status){
                        view?.toast("Selamat Datang ${body.data!!.name}")
                        println("body"+body.data)
                        val token = body.data?.token!!
                        val id_user = body.data?.id_user!!
                        view?.success(token,id_user)
                    }
                }else{
                    view?.toast("Ada yang tidak beres, coba lagi nanti, atau hubungi admin")
                }
                view?.isLoading(false)
            }

        })
    }
    override fun destroy() {
        view = null
    }


}