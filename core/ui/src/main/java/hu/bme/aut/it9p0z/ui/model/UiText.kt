package hu.bme.aut.it9p0z.ui.model

import android.content.Context

sealed class UiText {
    data class DynamicString(val text: String): UiText()
    data class StringResource(val id: Int, val args: List<Any>? = null): UiText()

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> text
            is StringResource -> if(args == null) context.getString(id) else context.getString(id,args)
        }
    }
}