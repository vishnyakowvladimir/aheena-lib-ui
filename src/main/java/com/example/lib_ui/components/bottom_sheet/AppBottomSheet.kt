package com.example.lib_ui.components.bottom_sheet

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.example.lib_ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    ModalBottomSheet(
        sheetState = bottomSheetState,
        shape = RoundedCornerShape(64f, 64f, 0f, 0f),
        containerColor = AppTheme.palette.background.primary,
        scrimColor = AppTheme.palette.background.transparent50,
        onDismissRequest = onDismissRequest,
    ) {
        content()
    }
}
