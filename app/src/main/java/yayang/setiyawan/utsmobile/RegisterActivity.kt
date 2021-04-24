package yayang.setiyawan.utsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_register.*
import yayang.setiyawan.utsmobile.contracts.RegisterActivityContract
import yayang.setiyawan.utsmobile.presenters.RegisterActivityPresenter
import yayang.setiyawan.utsmobile.utilities.Utils

class RegisterActivity : AppCompatActivity(),RegisterActivityContract.View {
    private var presenter =  RegisterActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterActivityPresenter(this)
        Save()
    }

    private fun Save(){
        btnSave.setOnClickListener {
            val token = Utils.getToken(this)
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val conf_password = etConfrim.text.toString().trim()
            if (name.isNotEmpty()&&email.isNotEmpty()&&password.isNotEmpty()&&conf_password.isNotEmpty()){
                token?.let { it->presenter.register(it,name,email,password) }
            }else{
                showToast("Isi semua form")
            }
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    override fun success(message: String?) {
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Data Di Tambahkan")
            .show()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },1000)

    }

    override fun showLoading() {
    }
    override fun isLoading(state: Boolean) {
        btnSave.isEnabled=!state
    }
}