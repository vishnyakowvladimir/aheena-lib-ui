package com.example.lib_ui.components.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

private val dismissStateStartToEnd = SwipeToDismissBoxValue.StartToEnd

@Composable
fun AppSwipeableSnackbarHost(
    snackbarHostState: SnackbarHostState,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == dismissStateStartToEnd) {
                snackbarHostState.currentSnackbarData?.dismiss()
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue != dismissStateStartToEnd) {
            dismissState.reset()
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {},
    ) {
        SnackbarHost(snackbarHostState) { data ->
            AppSnackBar(
                visuals = data.visuals,
            )
        }
    }
}