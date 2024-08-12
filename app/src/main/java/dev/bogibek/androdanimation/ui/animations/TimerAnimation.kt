package dev.bogibek.androdanimation.ui.animations

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.seconds


@SuppressLint("ConstantLocale")
val secondFormatter = SimpleDateFormat("ss", Locale.getDefault())

@SuppressLint("ConstantLocale")
val minuteFormatter = SimpleDateFormat("mm", Locale.getDefault())

@SuppressLint("ConstantLocale")
val hourFormatter = SimpleDateFormat("HH", Locale.getDefault())

@Composable
fun TimerAnimation(
    modifier: Modifier = Modifier
) {
    var seconds by remember {
        mutableIntStateOf(secondFormatter.format(Date()).toInt())
    }
    var second by remember {
        mutableIntStateOf(secondFormatter.format(Date()).toInt() / 10)
    }
    var minutes by remember {
        mutableIntStateOf(minuteFormatter.format(Date()).toInt())
    }
    var minute by remember {
        mutableIntStateOf(minuteFormatter.format(Date()).toInt() / 10)
    }
    var hours by remember {
        mutableIntStateOf(hourFormatter.format(Date()).toInt())
    }
    var hour by remember {
        mutableIntStateOf(hourFormatter.format(Date()).toInt() / 10)
    }


    LaunchedEffect(seconds) {
        delay(1.seconds)
        seconds = secondFormatter.format(Date()).toInt()
        if (seconds % 10 == 0) second = secondFormatter.format(Date()).toInt() / 10
        if (seconds == 0) minutes = minuteFormatter.format(Date()).toInt()
        if (minutes % 10 == 0) minute = minuteFormatter.format(Date()).toInt() / 10
        if (hours % 10 == 0) hour = hourFormatter.format(Date()).toInt() / 10
        if (minutes == 0) hours = hourFormatter.format(Date()).toInt()
    }

    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        AnimatedContent(
            targetState = hour,
            label = "hour",
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
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }

        AnimatedContent(
            targetState = hours,
            label = "hours",
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
                text = "${it % 10}",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
        Text(
            text = " : ",
            style = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily.SansSerif
            )
        )

        AnimatedContent(
            targetState = minute,
            label = "minute",
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
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }

        AnimatedContent(
            targetState = minutes,
            label = "minutes",
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
                text = "${it % 10}",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
        Text(
            text = " : ",
            style = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily.SansSerif
            )
        )
        AnimatedContent(
            targetState = second,
            label = "second",
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
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }

        AnimatedContent(
            targetState = seconds,
            label = "seconds",
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
                text = "${it % 10}",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
    }

}