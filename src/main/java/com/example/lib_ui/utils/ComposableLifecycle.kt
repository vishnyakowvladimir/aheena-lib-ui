package com.example.lib_ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun ComposableLifecycle(
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    key2: Any? = Unit,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit,
) {
    DisposableEffect(lifeCycleOwner, key2) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun ComposableLifecycle(
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    key2: Any? = Unit,
    onCreate: (() -> Unit)? = null,
    onStart: (() -> Unit)? = null,
    onResume: (() -> Unit)? = null,
    onPause: (() -> Unit)? = null,
    onStop: (() -> Unit)? = null,
    onDestroy: (() -> Unit)? = null,
    onAny: (() -> Unit)? = null,
) = ComposableLifecycle(
    lifeCycleOwner = lifeCycleOwner,
    key2 = key2,
    onEvent = { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> onCreate?.invoke()
            Lifecycle.Event.ON_START -> onStart?.invoke()
            Lifecycle.Event.ON_RESUME -> onResume?.invoke()
            Lifecycle.Event.ON_PAUSE -> onPause?.invoke()
            Lifecycle.Event.ON_STOP -> onStop?.invoke()
            Lifecycle.Event.ON_DESTROY -> onDestroy?.invoke()
            Lifecycle.Event.ON_ANY -> onAny?.invoke()
        }
    },
)
