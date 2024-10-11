package com.example.lib_ui.components.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.image.IconSource
import com.example.lib_ui.components.image.ImageFromSource
import com.example.lib_ui.theme.AppTheme

@Composable
internal fun AppSnackBar(
    visuals: SnackbarVisuals,
) {
    val appVisuals = visuals as? AppSnackbarVisuals
    val background = appVisuals?.status?.getBackground() ?: AppTheme.palette.background.primary

    Snackbar(
        modifier = Modifier
            .padding(
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp,
            )
            .clip(RoundedCornerShape(size = 16.dp)),
        containerColor = background,
    ) {
        Row(
            modifier = Modifier
                .background(color = background),
        ) {
            if (appVisuals?.resourceId != null) {
                ImageFromSource(
                    iconSource = IconSource.FromResource(
                        resourceId = appVisuals.resourceId,
                    )
                )

                Spacer(modifier = Modifier.width(16.dp))
            }

            Text(
                text = visuals.message,
                color = AppTheme.palette.text.staticWhite,
            )
        }
    }
}