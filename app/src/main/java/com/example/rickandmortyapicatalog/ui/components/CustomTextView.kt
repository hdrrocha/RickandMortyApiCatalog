package com.example.rickandmortyapicatalog.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rickandmortyapicatalog.R
import kotlinx.android.synthetic.main.view_custom_text_view.view.*


class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.view_custom_text_view, this, true)
    }

    fun setInformation(information: String?) {
        when (information?.lowercase()) {
            "alive" -> tvInformationValue.setTextColor(context.getColor(R.color.green500))
            "unknown" -> tvInformationValue.setTextColor(context.getColor(R.color.neutralYellowPure))
            else -> {
                tvInformationValue.setTextColor(context.getColor(R.color.red500))
            }
        }
        tvInformationValue.text = "$information,"
    }
}
