package com.example.cobafirebase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_admin_home.*

class AdminHomeActivity : AppCompatActivity() {
    private lateinit var viewModel: MasakanViewModel
    private val adapter = MasakanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        viewModel = ViewModelProviders.of(this).get(MasakanViewModel::class.java)

        rv_adminmain.adapter = adapter

        viewModel.fetchMasakan()

        viewModel.masakans.observe(this, Observer {
            adapter.setMasakan(it)
        })

        float_btn_add.setOnClickListener{
            startActivity(Intent(this,TambahMasakanActivity::class.java))
        }
    }
}