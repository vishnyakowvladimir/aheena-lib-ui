package com.example.lib_ui.theme.typography

enum class ViewScale(
    val id: Int,
    val textMultiplier: Float,
) {
    M(0, 1f),
    L(1, 1.1f),
    XL(2, 1.3f);

    companion object {
        fun getById(id: Int): ViewScale {
            return entries.find { it.id == id } ?: M
        }

        fun getByName(name: String): ViewScale {
            return entries.find { it.name == name } ?: M
        }
    }
}

fun getMin(viewScale1: ViewScale, viewScale2: ViewScale?): ViewScale {
    return if (viewScale2 != null && viewScale2.textMultiplier < viewScale1.textMultiplier) viewScale2 else viewScale1
}