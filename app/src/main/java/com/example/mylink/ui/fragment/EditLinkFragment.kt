package com.example.mylink.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.mylink.R
import com.example.mylink.data.SjLink
import com.example.mylink.databinding.FragmentEditLinkBinding
import com.example.mylink.ui.component.SjTagChip
import com.example.mylink.ui.fragment.basic.ViewBindingBasicFragment
import com.example.mylink.viewmodel.ViewLinkViewModel


class EditLinkFragment : ViewBindingBasicFragment<FragmentEditLinkBinding>() {
    private val viewModel: ViewLinkViewModel by activityViewModels()
    override fun layoutId(): Int = R.layout.fragment_edit_link

    fun setDomainDetailTextView() {
        val builder = StringBuilder()
        builder.append(viewModel.getDomainUrlByPosition(binding.domainSpinner.selectedItemPosition))
        builder.append(binding.linkEditText.text.toString())
        binding.domainDetailTextView.setText(builder.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //replace lifecycleOwner to this(fragment) to viewLifecycleOwner
        //because fragment lives longer after its view was destroyed.
        viewModel.domainNames.observe(viewLifecycleOwner, {
            binding.domainSpinner.adapter =
                ArrayAdapter(this.context!!, android.R.layout.simple_spinner_dropdown_item, it);
        })

        viewModel.tags.observe(viewLifecycleOwner, {
            for (tag in it) {
                val chip = SjTagChip(context!!, tag)
                binding.tagChipGroup.addView(chip)
            }
        })
        binding.domainSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    val builder = StringBuilder()
                    builder.append(viewModel.getDomainUrlByPosition(binding.domainSpinner.selectedItemPosition))
                    builder.append(binding.linkEditText.text.toString())
                    binding.domainDetailTextView.setText(builder.toString())
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Log.i(getClassName(), "domainSpinner select is null")
                    binding.domainSpinner.setSelection(0)
                }
            }
        viewModel.loadDomainNamesAndUrls()
        viewModel.loadTags()



        binding.linkEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setDomainDetailTextView()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.saveButton.setOnClickListener {
            viewModel.insertLinks(
                SjLink(
                    did = viewModel.getDomainIdByPosition(binding.domainSpinner.selectedItemPosition),
                    name = binding.nameEditText.text.toString(),
                    url = binding.linkEditText.text.toString()
                ), binding.tagChipGroup.checkedChipIds
            )
            requireActivity().finish()
        }
    }


}