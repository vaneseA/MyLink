package com.example.mylink.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.mylink.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LinkPasteBottomSheet(
    val url: String,
    val saveOperation: (String) -> Unit
) : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(url: String, saveOperation: (String) -> Unit): LinkPasteBottomSheet {
            return LinkPasteBottomSheet(url, saveOperation)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val saveButton = view.findViewById<Button>(R.id.nextButton)
        val urlTextView = view.findViewById<TextView>(R.id.urlTextView)
        saveButton.setOnClickListener { saveOperation(url) }
        urlTextView.setText(url)
    }

}