package com.example.cartamasalta.ui.theme

class Baraja {
    companion object{
        val baraja = mutableListOf<Carta>()


        fun creaBaraja(): MutableList<Carta>{
            baraja.clear()
            for(palo in Palos.values()){
                for(naipe in Naipes.values())
                    baraja.add(Carta(naipe, palo))
            }
            return baraja
        }

        fun barajar(baraja: MutableList<Carta>): Unit = baraja.shuffle()

        fun dameCarta(baraja: MutableList<Carta>): Carta = baraja.removeLast()

    }
}

