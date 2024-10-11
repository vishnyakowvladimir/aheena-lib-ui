package com.example.lib_ui.components.bottom_bar.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class AppBottomBarState(
    val items: ImmutableList<AppBottomBarItemState>,
) {
    fun updateStateByIndex(selectedIndex: Int): AppBottomBarState {
        val updatedItems = items.mapIndexed { index, item ->
            item.copy(isSelected = index == selectedIndex)
        }.toImmutableList()

        return copy(items = updatedItems)
    }
}
