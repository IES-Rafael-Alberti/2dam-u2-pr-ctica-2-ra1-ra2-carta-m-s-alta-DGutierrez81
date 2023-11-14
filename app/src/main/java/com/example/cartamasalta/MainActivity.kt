package com.example.cartamasalta

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cartamasalta.ui.theme.Baraja
import com.example.cartamasalta.ui.theme.Carta
import com.example.cartamasalta.ui.theme.CartaMasAltaTheme
import com.example.cartamasalta.ui.theme.Naipes
import com.example.cartamasalta.ui.theme.Palos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartaMasAltaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Juego()
                }
            }
        }
    }
}

/**
 * Función principal donde se incia el juego y realiza las acciones principales.
 */
@Preview(showBackground = true)
@Composable
fun Juego(){
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.tapete),
            contentScale = ContentScale.FillBounds
        )) {

        val context = LocalContext.current
        var baraja by rememberSaveable { mutableStateOf(Baraja.creaBaraja()) }
        val mazo by rememberSaveable {mutableStateOf("vuelta")}
        var carta by rememberSaveable { mutableStateOf("vuelta")}
        val (box1, box2, box3) = createRefs()
        val topGuide = createGuidelineFromTop(0.6f)
        var show  by rememberSaveable { mutableStateOf(true)}

        Box(
            modifier = Modifier
                .constrainAs(box1){
                    bottom.linkTo(topGuide)
                    start.linkTo(parent.start)
                }
        ){
            // Mientras se tru nos muestra el mazo y baraja las cartas.
            if(show) {
                MostrarCarta(mazo, context)
                Baraja.barajar(baraja)
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(box2){
                    top.linkTo(topGuide)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ){
            MostrarCarta(carta, context)
        }
        Box(
            modifier = Modifier.
            constrainAs(box3){
                top.linkTo(box2.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            Row {
                // Al pulsarlo nos muestra la siguiente carta, si la baraja está vacia nos devuelve un mensaje
                Button(onClick = { carta = if (baraja.isEmpty()) {
                    Toast.makeText( context,"La baraja está vacía ", Toast.LENGTH_SHORT).show()
                    show = false
                    "vuelta"
                } else {
                    val cartaDada = Baraja.dameCarta(baraja)
                    "${cartaDada.nombre}_${cartaDada.palo}".lowercase()
                }}, modifier = Modifier.padding(3.dp)) {
                    Text(text = "Dar carta")
                }
                // Al pulsarlo se baraja de nuevo y se pone el mazo de nuevo en la mesa.
                Button(onClick = {baraja = Baraja.creaBaraja()
                    show = true
                    carta = "vuelta"}, modifier = Modifier.padding(3.dp)) {
                    Text(text = "Reiniciar")
                }
            }
        }
    }
    
}

/**
 * Muestra la carta indicada
 * @param carta Recibe un String con el nombre de la carta a mostrar.
 * @param context Recibe el contexto.
 */

@Composable
fun MostrarCarta(
    carta: String,
    context: Context
){
    Image(painter = painterResource(id = context.resources.getIdentifier(carta, "drawable", context.packageName) ),
        contentDescription = "Carta mostrada",
        modifier = Modifier
            .height(200.dp)
            .width(100.dp)
    )
}



