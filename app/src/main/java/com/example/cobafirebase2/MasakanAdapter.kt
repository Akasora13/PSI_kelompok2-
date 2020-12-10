package com.example.cobafirebase2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.listmakanan.view.*

class MasakanAdapter : RecyclerView.Adapter<MasakanAdapter.MasakanViewModel>() {

    private var masakans = mutableListOf<Masakan>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MasakanViewModel { //aneh :( error disini pokok e
         val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.listmakanan, parent, false) //bisa jadi error
        val vh = createViewHolder(v as ViewGroup, viewType) // error nih mungkin
        return vh
    }

    override fun getItemCount() = masakans.size

    override fun onBindViewHolder(holder: MasakanViewModel, position: Int) {
        holder.view.tv_namamasakan.text = masakans[position].nama //get data ya rek
    }

    fun setMasakan(masakan: List<Masakan>){
        this.masakans = masakan as MutableList<Masakan>
        notifyDataSetChanged()
    }

    class MasakanViewModel(val view: View) : RecyclerView.ViewHolder(view)

}

