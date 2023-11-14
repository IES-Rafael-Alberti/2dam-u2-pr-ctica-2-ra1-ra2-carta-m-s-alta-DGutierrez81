package com.example.cartamasalta.ui.theme

class Baraja {
    companion object{
        val baraja = mutableListOf<Carta>()


        /**
         * Crea la baraja de cartas francesa.
         */
        fun creaBaraja(): MutableList<Carta>{
            baraja.clear()
            for(palo in Palos.values()){
                for(naipe in Naipes.values())
                    baraja.add(Carta(naipe, palo))
            }
            return baraja
        }

        /**
         * Baraja el mazo de cartas.
         * @param baraja Recibe el mazo de cartas a barajar.
         * @return Retorna el mazo ya barajdo.
         */
        fun barajar(baraja: MutableList<Carta>): Unit = baraja.shuffle()

        /**
         * Elimina una carta del mazo y la devuelve
         * @param baraja Recibe el mazo de la que hay que quitar la carta
         * @return Devuelve la carta eliminada.
         */
        fun dameCarta(baraja: MutableList<Carta>): Carta = baraja.removeAt(baraja.size - 1)

    }
}

