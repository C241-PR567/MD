package com.dicoding.md.ui.customView

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import com.dicoding.md.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CustomPhoneEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    init {
        val editText = TextInputEditText(context)
        editText.inputType = android.text.InputType.TYPE_CLASS_PHONE
        addView(editText)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                error = if (s.toString().isEmpty()) {
                    context.getString(R.string.phone_empty)
                } else  if (!Patterns.PHONE.matcher(s.toString()).matches()) {
                    context.getString(R.string.invalid_email)
                } else {
                    null
                }
            }
        })
    }

    fun getText(): String {
        return (getChildAt(0) as TextInputEditText).text.toString()
    }
}