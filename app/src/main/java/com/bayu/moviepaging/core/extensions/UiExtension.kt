package com.bayu.moviepaging.core.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.hide() {
    isVisible = false
}

fun View.visible() {
    isVisible = true
}