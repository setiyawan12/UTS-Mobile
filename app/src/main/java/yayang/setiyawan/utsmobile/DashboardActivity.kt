package yayang.setiyawan.utsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*
import yayang.setiyawan.utsmobile.utilities.Utils

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        Logout()
        Barang()
        Kelola()
    }
    private fun Logout(){
        btnLogout.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java)).also {
                Utils.clearToken(this)
                finish()
            }
        }
    }

    private fun Barang(){
        btn_barang.setOnClickListener {
            startActivity(Intent(this,BarangActivity::class.java))
        }
    }

    private fun Kelola(){
        kelola.setOnClickListener {
            startActivity(Intent(this,KelolaActivity::class.java))
        }
    }

}