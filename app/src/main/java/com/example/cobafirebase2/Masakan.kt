package com.example.cobafirebase2

import com.google.firebase.database.Exclude

data class Masakan(
    @get:Exclude
    var id: String? = null,
    var nama: String? = null,
    var bahan: String? = null,
    var resep: String? = null
)