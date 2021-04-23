package yayang.setiyawan.utsmobile.presenters

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yayang.setiyawan.utsmobile.contracts.BarangActivityContract
import yayang.setiyawan.utsmobile.models.Barang
import yayang.setiyawan.utsmobile.webservices.Api
import yayang.setiyawan.utsmobile.webservices.WrappedResponse

class DeleteActivityPresenter(v: BarangActivityContract.ViewDelete):BarangActivityContract.InteractionDelete {
    private var view : BarangActivityContract.ViewDelete?= v
    private var api = Api.instance()
    override fun delete(id: Int, token: String) {
    val request = api.deleteData(id,token)
        request.enqueue(object : Callback<WrappedResponse<Barang>>{
            override fun onFailure(call: Call<WrappedResponse<Barang>>, t: Throwable) {
                view?.toast("Koneksi tidak bisa")
            }
            override fun onResponse(call: Call<WrappedResponse<Barang>>, response: Response<WrappedResponse<Barang>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if(body != null && body.status){
                        view?.success("Berhasil")
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