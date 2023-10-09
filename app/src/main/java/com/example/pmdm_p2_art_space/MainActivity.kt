package com.example.pmdm_p2_art_space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdm_p2_art_space.ui.theme.PMDM_P2_Art_SpaceTheme

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
                    ArtSpaceApp();
                }
            }
        }
    }
}

@Composable
fun ArtSpaceCarousel(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    val numImages = 5

    val artData = GetArtData(result)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.my_dark_gray)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.art_space_title),
                fontSize = 50.sp,
                color = colorResource(id = R.color.white)
            )
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(artData.imageResource),
                contentDescription = result.toString(),
                modifier = Modifier.size(400.dp)
            )
        }
        Row(
            Modifier
                .height(50.dp)
                .width(350.dp)
                .background(colorResource(R.color.my_dark_middle)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(artData.titleResource),
                fontSize = 25.sp,
                color = colorResource(id = R.color.white)
            )
        }
        Row(
            Modifier
                .height(50.dp)
                .width(350.dp)
                .background(colorResource(R.color.my_dark_middle)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(artData.authorResource),
                fontSize = 23.sp,
                color = colorResource(id = R.color.white)
            )
            Text(
                text = stringResource(artData.yearResource),
                fontSize = 23.sp,
                color = colorResource(id = R.color.white)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    result = (result - 1 + numImages) % numImages
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.my_dark_middle))
            ) {
                Text(
                    stringResource(R.string.previous),
                    fontSize = 24.sp
                )
            }
            Button(
                onClick = {
                    result = (result + 1) % numImages
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.my_dark_middle))
            ) {
                Text(
                    stringResource(R.string.next),
                    fontSize = 24.sp
                )
            }
        }
    }
}

class ArtData(
    val imageResource: Int,
    val titleResource: Int,
    val authorResource: Int,
    val yearResource: Int
);

@Composable
fun GetArtData(result:Int = 1): ArtData {
    return when(result) {
        1 -> ArtData(R.drawable.obra_1, R.string.title_1, R.string.author_1, R.string.year_1)
        2 -> ArtData(R.drawable.obra_2, R.string.title_2, R.string.author_2, R.string.year_2)
        3 -> ArtData(R.drawable.obra_3, R.string.title_3, R.string.author_3, R.string.year_3)
        4 -> ArtData(R.drawable.obra_4, R.string.title_4, R.string.author_4, R.string.year_4)
        else -> ArtData(R.drawable.obra_5, R.string.title_5, R.string.author_5, R.string.year_5)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    ArtSpaceCarousel(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}