package com.mina.dog.breed.ui.main.tabs

import androidx.fragment.app.Fragment

internal data class Tab constructor(val title: String, val tabFragment: TabFragment)

internal enum class TabFragment {
    BREEDS,
    NOT_IMPLEMENTED
}