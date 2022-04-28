package com.example.rickandmortyapicatalog.ui.utils

import android.content.Context
import android.widget.Toast


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.extractYear() = this.substring(0,4)+ "."