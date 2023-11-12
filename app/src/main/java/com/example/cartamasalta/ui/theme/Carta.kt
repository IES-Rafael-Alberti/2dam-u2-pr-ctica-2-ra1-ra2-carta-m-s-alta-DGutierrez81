package com.example.cartamasalta.ui.theme

import androidx.compose.ui.platform.LocalContext

class Carta(val nombre: Naipes, val palo: Palos) {
    var puntosMin: Int = 0
    var puntosMax: Int = 0
    var idDrawable: Int = 0
    constructor(nombre: Naipes, palo: Palos,puntosMin: Int, puntosMax: Int, idDrawable: Int): this(nombre, palo){
        this.puntosMin = puntosMin
        this.puntosMax = puntosMax
        this.idDrawable = idDrawable
    }

}