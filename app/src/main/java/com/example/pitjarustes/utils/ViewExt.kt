package com.example.pitjarustes.utils

import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.isNotEmpty(): Boolean {
    if (this.text.toString().trim().isEmpty()) {
        this.error = "${this.hint} belum di isi!"
        this.requestFocus()
        return false
    }
    return true
}

fun TextInputEditText.isSame(target: TextInputEditText): Boolean {
    return if (this.text.toString().trim() == target.text.toString().trim()) true
    else {
        target.error = "Kata sandi tidak sesuai!"
        target.requestFocus()
        target.text = null
        false
    }
}