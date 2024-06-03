package com.mina.dog.ui.main.tabs

internal data class Tab constructor(val title: String, val tabFragment: TabFragment)

internal enum class TabFragment {
    BREEDS,
    NOT_IMPLEMENTED,
}
