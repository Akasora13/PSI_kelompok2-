package com.example.cobafirebase2

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception

class MasakanViewModel : ViewModel() {

    private val dbMasakan = FirebaseDatabase.getInstance().getReference("masakan")

    private val _masakans = MutableLiveData<List<Masakan>>()
    val masakans : LiveData<List<Masakan>>
        get() = _masakans


    private val _result = MutableLiveData<Exception>()
    val result : LiveData<Exception?>
        get() = _result


    fun addMasakan (masakan : Masakan){

        masakan.id = dbMasakan.push().key


        masakan.id?.let {
            dbMasakan.child(it).setValue(masakan)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        _result.value=null;
                    }else{
                        _result.value=it.exception
                    }
                }
        }


    }

    fun fetchMasakan(){
        dbMasakan.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val masakanx = mutableListOf<Masakan>()
                    for (masakansnapshot in snapshot.children){
                        val masakans = masakansnapshot.getValue(Masakan::class.java)
                        masakans?.id = masakansnapshot.key //mungkin disini bakal error
                        masakans?.let { masakanx.add(it) }

                    }
                    _masakans.value= masakanx //takut e salah si


                }
            }

        })
    }
}