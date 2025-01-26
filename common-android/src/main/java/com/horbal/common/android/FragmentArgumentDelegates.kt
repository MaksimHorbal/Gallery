package com.horbal.common.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Fragment.stringArgument(defValue: String? = null): ReadWriteProperty<Fragment, String?> =
    FragmentArgumentDelegate<String?>(
        getter = { key -> getString(key, defValue) },
        setter = { key, value -> putString(key, value) },
        defValue = defValue,
    )

private class FragmentArgumentDelegate<T>(
    private val getter: Bundle.(String) -> T,
    private val setter: Bundle.(String, T) -> Unit,
    private val defValue: T,
) : ReadWriteProperty<Fragment, T> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val args = thisRef.arguments ?: return defValue
        return getter(args, property.name) ?: defValue
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        val args = thisRef.arguments ?: return
        setter(args, property.name, value)
    }
}