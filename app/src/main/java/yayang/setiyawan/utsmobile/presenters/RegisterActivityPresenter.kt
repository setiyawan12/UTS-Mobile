package yayang.setiyawan.utsmobile.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayang.setiyawan.utsmobile.contracts.RegisterActivityContract
import yayang.setiyawan.utsmobile.models.User
import yayang.setiyawan.utsmobile.webservices.Api
import yayang.setiyawan.utsmobile.webservices.WrappedResponse

class RegisterActivityPresenter (v: RegisterActivityContract.View):RegisterActivityContract.Interaction{
    private var view: RegisterActivityContract.View?=v
    private var api = Api.instance()
    override fun validate(
        name: String,
        email: String,
        password: String,
        conf_password: String
    ): Boolean {
        return true
    }

    override fun register(name: String, email: String, password: String, conf_password: String) {
        api.register(name, email, password, conf_password).enqueue(object : Callback<WrappedResponse<User>>{
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.showToast("koneksi tidak bisa")
            }
            override fun onResponse(
                call: Call<WrappedResponse<User>>,
                response: Response<WrappedResponse<User>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status){
                        println("body"+body)
                        view?.success("berhasil")
                    }
                }else{
                    view?.showToast("ada yang tidak beres")
                }
                view?.isLoading(false)
            }

        })
    }

    override fun destroy() {
        view = null
    }

}