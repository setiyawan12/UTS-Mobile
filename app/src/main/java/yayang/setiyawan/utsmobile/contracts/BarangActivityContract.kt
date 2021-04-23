package yayang.setiyawan.utsmobile.contracts

import yayang.setiyawan.utsmobile.models.Barang

interface BarangActivityContract {
    interface View{
        fun attachToRecycle(barang: List<Barang>)
        fun toast(message : String?)
        fun isLoading(state : Boolean)
        fun notConnect(message: String?)
    }

    interface ViewCreate{
        fun success(message: String?)
        fun toast(message : String?)
        fun isLoading(state : Boolean)
    }

    interface ViewEdit{
        fun success(message: String?)
        fun toast(message: String?)
        fun isLoading(state: Boolean)
    }

    interface ViewDelete{
        fun success(message: String?)
        fun toast(message: String?)
        fun isLoading(state: Boolean)
    }

    interface Interaction{
        fun allUser(token: String)
        fun destroy()
    }

    interface InteractionPost{
        fun validate(name: String, location: String, description: String) : Boolean
        fun save(token:String, name: String, location: String, description: String)
        fun destroy()
    }

    interface InteractionUpdate {
        fun validate(name: String, location: String, description: String) : Boolean
        fun update(id: Int, token: String, name: String, location: String, description: String)
        fun destroy()
    }
    interface InteractionDelete{
        fun delete(id: Int, token: String)
        fun destroy()
    }

}