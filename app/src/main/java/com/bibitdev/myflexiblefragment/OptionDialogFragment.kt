package com.bibitdev.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

class OptionDialogFragment : DialogFragment() {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbKg: RadioButton
    private lateinit var rbIp: RadioButton
    private lateinit var rbAl: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOptions = view.findViewById(R.id.rg_options)
        rbKg = view.findViewById(R.id.rb_kg)
        rbIp = view.findViewById(R.id.rb_ip)
        rbAl = view.findViewById(R.id.rb_al)

        btnChoose.setOnClickListener {
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                val coach: String? = when (checkedRadioButtonId) {
                    R.id.rb_kg -> rbKg.text.toString().trim()
                    R.id.rb_ip -> rbIp.text.toString().trim()
                    R.id.rb_al -> rbAl.text.toString().trim()
                    else -> null
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }

        btnClose.setOnClickListener {
            dialog?.cancel()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if (fragment is DetailCategoryFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    // Interface untuk callback ke fragment lain
    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}
