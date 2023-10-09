/**
 * INTEGRANTES DEL GRUPO
 * - Sergio Ania Lázaro
 * - Ricardo de Antonio Aguirre
 * - Irene López Melero
 */

package com.example.pmdm_p2_art_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdm_p2_art_space.ui.theme.PMDM_P2_Art_SpaceTheme

/**
 * CLASE PRINCIPAL PARA MOSTRAR LA APLICACIÓN
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PMDM_P2_Art_SpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Creamos la aplicación Art Space
                    ArtSpaceApp()
                }
            }
        }
    }
}

/**
 * FUNCIÓN DONDE CREAMOS LA ESTRUCTURA PARA MOSTRAR LAS OBRAS DE ARTE
 * modifier: Modifier -> representa el modifier que se aplica
 */
@Composable
fun ArtSpaceCarousel(
    modifier: Modifier = Modifier
) {
    // índice de la obra
    var index by remember { mutableStateOf(1) }
    // número de obras totales
    val numImages = 5

    // obtenemos imagen, título, autor y año de la obra
    val artData = getArtData(index)

    // columna donde pondremos los elementos necesarios para mostrar la información de cada obra
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // título de la aplicación
        Row(
            modifier = Modifier
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.art_space_title),
                fontSize = 50.sp,
                fontFamily = FontFamily.Cursive,
                color = colorResource(id = R.color.white)
            )
        }
        // información de la obra
        Column(
            modifier = Modifier
                .padding(15.dp, 15.dp, 15.dp, 0.dp)
                .background(colorResource(id = R.color.white)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // imagen de la obra
            Image(
                painter = painterResource(artData.imageResource),
                contentDescription = index.toString(),
                modifier = Modifier
                    .size(400.dp)
                    .padding(5.dp)
            )
            // título de la obra
            Text(
                text = stringResource(artData.titleResource),
                fontSize = 25.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.black)
            )
            // autor y año de la obra
            Row(
                Modifier
                    .height(50.dp)
                    .width(350.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(artData.authorResource),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black)
                )
                Text(
                    text = stringResource(artData.yearResource),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black)
                )
            }
        }
        // botones de la aplicación
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    index = (index - 1 + numImages) % numImages
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.my_dark_middle)),
                border = BorderStroke(2.dp, colorResource(id = R.color.white))
            ) {
                Text(
                    stringResource(R.string.previous),
                    fontSize = 24.sp
                )
            }
            Button(
                onClick = {
                    index = (index + 1) % numImages
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.my_dark_middle)),
                border = BorderStroke(2.dp, colorResource(id = R.color.white))
            ) {
                Text(
                    stringResource(R.string.next),
                    fontSize = 24.sp
                )
            }
        }
    }
}

/**
 * CLASE CON LOS ATRIBUTOS DESCRIPTIVOS DE LA OBRA
 * imageResource: Int -> representa el recurso empleado para la imagen
 * titleResource: Int -> representa el recurso empleado para el título
 * authorResource: Int -> representa el recurso empleado para el autor
 * yearResource: Int -> representa el recurso empleado para el año
 */
class ArtData(
    val imageResource: Int,
    val titleResource: Int,
    val authorResource: Int,
    val yearResource: Int
)

/**
 * FUNCIÓN PARA DEVOLVER UN OBJETO DE TIPO ArtData CON LOS DATOS DE LA OBRA CORRESPONDIENTE
 * index: Int -> representa el índice de la obra de la que queremos obtener la información
 * return -> objeto ArtData con los recursos de la obra
 */
@Composable
fun getArtData(index:Int = 1): ArtData {
    return when(index) {
        1 -> ArtData(R.drawable.obra_1, R.string.title_1, R.string.author_1, R.string.year_1)
        2 -> ArtData(R.drawable.obra_2, R.string.title_2, R.string.author_2, R.string.year_2)
        3 -> ArtData(R.drawable.obra_3, R.string.title_3, R.string.author_3, R.string.year_3)
        4 -> ArtData(R.drawable.obra_4, R.string.title_4, R.string.author_4, R.string.year_4)
        else -> ArtData(R.drawable.obra_5, R.string.title_5, R.string.author_5, R.string.year_5)
    }
}

/**
 * FUNCIÓN PARA PREVISUALIZAR LA APLICACIÓN
 */
@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    // llamamos a la función que estructura la aplicación
    ArtSpaceCarousel(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.my_dark_gray)))
}