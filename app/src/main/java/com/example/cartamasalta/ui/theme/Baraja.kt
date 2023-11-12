package com.example.cartamasalta.ui.theme

class Baraja {
    companion object{
        val baraja: ArrayList<Carta> = arrayListOf()


        fun creaBaraja(){
            baraja.clear()
            for(palo in Palos.values()){
                for(naipe in Naipes.values())
                    baraja.add(Carta(naipe, palo))
            }
        }

        fun barajar(){
            baraja.shuffle()
        }

        fun dameCarta(): Carta{
            val carta = baraja.removeAt(baraja.size - 1)
            return carta
        }
    }
}

