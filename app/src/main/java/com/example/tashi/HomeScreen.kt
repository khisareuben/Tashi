package com.example.tashi

import android.app.Activity
import android.content.Context
import android.media.AsyncPlayer
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

val pictures = listOf(
    R.drawable.birthday,
    R.drawable.birthday2,
    R.drawable.birthday4,
    R.drawable.birthday3,
    R.drawable.elephant_gift,
    R.drawable.duck_doctor,
    R.drawable.cool_cat,
    R.drawable.reuben,
    R.drawable.reuben2,
    R.drawable.reuben3,
    R.drawable.reuben4,
    R.drawable.reuben5,
    R.drawable.reuben6,
    R.drawable.reuben7,
    R.drawable.reuben8,
    R.drawable.reuben9,
    R.drawable.reuben10,
    R.drawable.reuben11,
    R.drawable.tashi1,
    R.drawable.tashi2,
    R.drawable.tashi3,
    R.drawable.tashi4,
    R.drawable.tashi5,
    R.drawable.tashi6

    )



@Composable
fun HomeScreen(navController: NavController){
    
    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isDark) Color(12,53,60)
    else Color(255,255,255)

    Column(
        modifier = Modifier
            .background(backgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = Modifier.padding(top = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Magenta
                            )
                        ) {
                            append("H")
                        }
                        append("appy")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Magenta
                            )
                        ) {
                            append(" B")
                        }
                        append("irthday")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Magenta
                            )
                        ) {
                            append(" Tashi")
                        }
                        //append("ompose")
                    },
                    modifier = Modifier.padding(20.dp),
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(fontSize = 25.sp),
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace
                )

                SimpleMusicPlayer(context = LocalContext.current)

            }



        }
        val pagerState = rememberPagerState(pageCount = {pictures.size})

        HorizontalPager(state = pagerState, contentPadding = PaddingValues(38.dp)) { index ->
            CardContent(index, pagerState)
        }




    }



}

@Composable
fun CardContent(index: Int, pagerState: PagerState) {

    val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(2.dp).graphicsLayer{
            lerp(
                start = 0.85f.dp,
                stop = 1f.dp,
                fraction = 1f - pageOffset.absoluteValue.coerceIn(0f,1f)
            ).also { scale ->
                scaleX = scale.value
                scaleY = scale.value
            }
        }
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(pictures[index])
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
    }


}

@Composable
fun SimpleMusicPlayer(context: Context) {
    var mediaPlayer: MediaPlayer? = remember { MediaPlayer.create(context, R.raw.adele_make_you_feel_my_love) }
    var isPlaying by remember { mutableStateOf(false) }
    var icon = if (isPlaying)
        painterResource(id = R.drawable.baseline_pause_24)
    else painterResource(id = R.drawable.baseline_play_arrow_24)

    // Listen for song completion and reset state
    LaunchedEffect(mediaPlayer) {
        mediaPlayer?.setOnCompletionListener {
            isPlaying = false // Reset to play icon when song ends
        }
    }

    Column(
        modifier = Modifier.padding(top = 16.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "make you feel my love", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        OutlinedButton(onClick = {
            if (isPlaying) {
                mediaPlayer?.pause()
            } else {
                mediaPlayer?.start()
            }
            isPlaying = !isPlaying
        }) {
            Icon(painter = icon, contentDescription = "Play/Pause")
        }
    }
}
