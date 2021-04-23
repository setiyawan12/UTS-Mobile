package yayang.setiyawan.utsmobile.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Barang(
    @SerializedName("id") var id : String? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("description") var description : String? = null,
) : Parcelable
