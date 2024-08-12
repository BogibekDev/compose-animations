package dev.bogibek.androdanimation.ui.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun NumberAnimation(
    modifier: Modifier = Modifier
) {

    var count by remember {
        mutableIntStateOf(1)
    }

    LaunchedEffect(count) {
        delay(1.seconds)
        count++
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Compose Animations",
            style = TextStyle(
                fontSize = 36.sp
            )
            )
        Spacer(modifier = Modifier.height(50.dp))
        AnimatedContent(
            targetState = count,
            label = "Timer",
            transitionSpec = {
                slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = { -it }
                ).togetherWith(slideOutVertically(
                    animationSpec = tween(500),
                    targetOffsetY = { it }
                ))
            }
        ) {
            Text(
                text = "$it",
                style = TextStyle(
                    fontSize = 100.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
    }

}