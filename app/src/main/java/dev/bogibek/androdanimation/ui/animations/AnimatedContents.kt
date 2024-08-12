package dev.bogibek.androdanimation.ui.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.bogibek.androdanimation.ui.utils.UiState


@Composable
fun AnimatedContents(
    modifier: Modifier = Modifier,
    loading: @Composable () -> Unit,
    success: @Composable () -> Unit,
    error: @Composable () -> Unit
) {
    var state by remember {
        mutableStateOf(UiState.Loading)
    }
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(3000)
            ) togetherWith fadeOut(animationSpec = tween(3000))
        },
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            state = when (state) {
                UiState.Loading -> UiState.Success
                UiState.Success -> UiState.Error
                UiState.Error -> UiState.Loading
            }
        },
        label = "Animated Content"
    ) { targetState ->
        when (targetState) {
            UiState.Loading -> loading.invoke()
            UiState.Success -> success.invoke()
            UiState.Error -> error.invoke()
        }
    }

}