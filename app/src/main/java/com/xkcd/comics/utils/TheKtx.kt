package com.xkcd.comics.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.showSnack(message: String, action: String = "", actionListener: () -> Unit = {}): Snackbar {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    if (action != "") {
        snackbar.duration = Snackbar.LENGTH_INDEFINITE
        snackbar.setAction(action) {
            actionListener()
            snackbar.dismiss()
        }
    }
    snackbar.show()
    return snackbar
}

fun Fragment.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun EditText.dismissKeyboard() {
    val imm: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(windowToken, 0)
}