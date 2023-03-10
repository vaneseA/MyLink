package com.example.mylink.ui.component

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class BasicDialogFragment(
    private val title: String,
    private val message: String,
    private val okOperation: (() -> Unit)?
) :
    DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("확인") { _, _ ->
                if (okOperation is () -> Unit) okOperation.invoke()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}