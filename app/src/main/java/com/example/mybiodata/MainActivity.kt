package com.example.mybiodata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var namaInput : String =""
    private var emailInput : String =""
    private var teleponInput : String =""
    private var alamatInput : String =""
    private var genderInput : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDataSpinnerGender()

        button_simpan.setOnClickListener { validasiInput() }
    }

    private fun setDataSpinnerGender(){
        val adapter = ArrayAdapter.createFromResource(this,R.array.jenis_kelamin,
        android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner_kelamin.adapter = adapter
    }


    private fun validasiInput() {
        namaInput = edit_nama.text.toString()
        emailInput = edit_email.text.toString()
        teleponInput = edit_telepon.text.toString()
        alamatInput = edit_alamat.text.toString()
        genderInput = spinner_kelamin.selectedItem.toString()

        when {
            namaInput.isEmpty() -> edit_nama.error = "Nama tidak boleh kosong"
            genderInput.equals(
                "Pilih Jenis Kelamin",
                ignoreCase = true
            ) -> tampilToast("Jenis Kelamin harus dipilih")
            emailInput.isEmpty() -> edit_email.error = "Email tidak boleh kosong"
            teleponInput.isEmpty() -> edit_telepon.error = "Telp tidak boleh kosong"
            alamatInput.isEmpty() -> edit_alamat.error = "Alamat tidak boleh kosong"
            else -> {
                tampilToast("Navigasi ke halaman profil")
                goToProfilActivity()
            }
        }


    }

    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



    private fun goToProfilActivity() {
        val intent = Intent(this, ProfilActivity::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", teleponInput)
        bundle.putString("alamat", alamatInput)

        intent.putExtras(bundle)

        startActivity(intent)
    }
}




