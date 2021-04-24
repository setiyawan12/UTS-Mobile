package yayang.setiyawan.utsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_kelola.*
import yayang.setiyawan.utsmobile.adapters.BarangAdapter
import yayang.setiyawan.utsmobile.contracts.BarangActivityContract
import yayang.setiyawan.utsmobile.models.Barang
import yayang.setiyawan.utsmobile.presenters.BarangActivityPresenter
import yayang.setiyawan.utsmobile.utilities.Utils

class KelolaActivity : AppCompatActivity(),BarangActivityContract.View {
    private var presenter = BarangActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola)
        presenter = BarangActivityPresenter(this)
        getData()
        create()
    }

    override fun attachToRecycle(barang: List<Barang>) {
        rvBarang.apply {
            layoutManager = LinearLayoutManager(this@KelolaActivity)
            adapter = BarangAdapter(barang,this@KelolaActivity)
        }
    }
    private fun create(){
        btnCreate.setOnClickListener { startActivity(Intent(this,CreateBarangActivity::class.java)).apply { finish() } }
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {
        TODO("Not yet implemented")
    }

    override fun notConnect(message: String?) {
        TODO("Not yet implemented")
    }
    private fun getData (){
        Utils.getToken(this)?.let { presenter.allUser(it) }
    }
    override fun onResume() {
        super.onResume()
        getData()
    }
}