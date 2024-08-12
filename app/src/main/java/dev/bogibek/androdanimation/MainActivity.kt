package dev.bogibek.androdanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.bogibek.androdanimation.ui.DrawerMenuAnimation
import dev.bogibek.androdanimation.ui.animations.TimerAnimation
import dev.bogibek.androdanimation.ui.theme.AndrodAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndrodAnimationTheme {
                TimerAnimation()
            }
        }
    }
}
