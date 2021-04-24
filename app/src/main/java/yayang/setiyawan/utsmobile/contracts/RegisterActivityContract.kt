package yayang.setiyawan.utsmobile.contracts


interface RegisterActivityContract {

    interface View{
        fun showToast(message : String)
        fun success(message: String?)
        fun showLoading()
        fun isLoading(state: Boolean)
    }
    interface Interaction{
        fun validate(name: String, email : String, password : String, conf_password:String):Boolean
        fun register(name: String, email : String, password : String, conf_password:String)
        fun destroy()
    }
}