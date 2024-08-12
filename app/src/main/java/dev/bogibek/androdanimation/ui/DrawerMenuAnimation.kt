package dev.bogibek.androdanimation.ui

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import dev.bogibek.androdanimation.ui.screens.HomeScreen
import dev.bogibek.androdanimation.ui.screens.InfoScreen
import dev.bogibek.androdanimation.ui.screens.SettingScreen
import kotlinx.coroutines.launch

@Composable
fun DrawerMenuAnimation(
    modifier: Modifier = Modifier
) {
    var drawerState =
        rememberDrawerState(initialValue = DrawerValue.Closed, confirmStateChange = { false })
    val decay = rememberSplineBasedDecay<Float>()
    val drawerWidth = 200f
    val screenState = remember {
        mutableStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()
    val translationX = remember {
        Animatable(initialValue = 0f)
    }
    translationX.updateBounds(0f, drawerWidth)

    val draggableState = rememberDraggableState {
        coroutineScope.launch {
            translationX.snapTo(targetValue = translationX.value + it)
        }
    }

    Box(
        modifier = modifier
    ) {

        DrawerMenu {
            screenState.value = it
            coroutineScope.launch {
                Log.d("@@@@@", "0DrawerMenuAnimation: ${drawerState.currentValue}")

                if (drawerState.currentValue == DrawerValue.Open) {
                    translationX.animateTo(0f)
                    Log.d("@@@@@", "1DrawerMenuAnimation: ${drawerState.currentValue}")
                } else {
                    translationX.animateTo(drawerWidth)
                }
                drawerState = DrawerState(
                    initialValue = if (drawerState.currentValue == DrawerValue.Open) DrawerValue.Closed else DrawerValue.Open
                )
                Log.d("@@@@@", "2DrawerMenuAnimation: ${drawerState.currentValue}")



            }
        }
        ScreenContent(
            screenState = screenState.value,
            modifier = Modifier

                .graphicsLayer {
                    this.translationX = translationX.value
                    val scale = lerp(
                        start = 1f,
                        stop = 0.8f,
                        fraction = translationX.value / drawerWidth
                    )
                    this.scaleX = scale
                    this.scaleY = scale
                }

                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        val decayX = decay.calculateTargetValue(
                            initialValue = translationX.value,
                            initialVelocity = it
                        )

                        coroutineScope.launch {
                            val targetX = if (decayX > drawerWidth * 0.5) decayX else 0f

                            val canReachTargetWithDecay =
                                (decayX > targetX && targetX == drawerWidth)
                                        || (decayX < targetX && targetX == 0f)

                            if (canReachTargetWithDecay) {
                                translationX.animateDecay(
                                    initialVelocity = it,
                                    animationSpec = decay
                                )
                            } else {
                                translationX.animateTo(
                                    targetValue = targetX,
                                    initialVelocity = it,

                                    )
                            }
                            drawerState = DrawerState(
                                initialValue = if (targetX == drawerWidth) DrawerValue.Open else DrawerValue.Closed
                            )
                        }

                    }
                )
        )

    }

    fun toggleDrawerState() {

    }

}


@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    screenState: Int
) {
    when (screenState) {
        0 -> HomeScreen(modifier)
        1 -> InfoScreen(modifier)
        2 -> SettingScreen(modifier)
    }
}

@Composable
fun DrawerMenu(
    itemClick: ((Int) -> Unit)
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
    ) {

        Spacer(modifier = Modifier.height(100.dp))
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                itemClick(0)
            },
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home")
            Text(text = "Home")

        }

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                itemClick(1)
            },
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = "Info")
            Text(text = "Info")

        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                itemClick(2)
            },
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Settings, contentDescription = "Setting")
            Text(text = "Setting")

        }
    }
}


data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
