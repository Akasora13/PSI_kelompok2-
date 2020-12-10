package com.example.cobafirebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_tambah_masakan.*

class TambahMasakanActivity : AppCompatActivity() {

    private lateinit var viewModel: MasakanViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_masakan)

        viewModel = ViewModelProviders.of(this).get(MasakanViewModel::class.java)



        viewModel.result.observe(
            this,
            Observer { //mungkin disini nanti akan error bro karena ownernya pake this :(
                val message = if (it == null) {
                    "Awww :3 , its work man "
                } else {
                    "Error occured. Understandable, have a good days :)"
                }
                Toast.makeText(
                    baseContext, message,
                    Toast.LENGTH_SHORT
                ).show()
            })


        btn_add_resep.setOnClickListener {
            tambahresep()
        }
    }

    fun tambahresep() {
        val nama = et_add_nama.text.toString()
        val bahan = et_add_bahan.text.toString()
        val resep = et_add_Resep.text.toString()

        if (nama.isEmpty()) {
            et_add_nama.error = "Isi nama masakan"
            et_add_nama.requestFocus()
            return
        }
        if (bahan.isEmpty()) {
            et_add_bahan.error = "Isi bahan masakan"
            et_add_bahan.requestFocus()
            return
        }
        if (resep.isEmpty()) {
            et_add_Resep.error = "Isi resep masakan"
            et_add_Resep.requestFocus()
            return
        }


        val masakans = Masakan()
        masakans.nama = nama
        masakans.bahan = bahan
        masakans.resep = resep

        viewModel.addMasakan(masakans)


    }

}

