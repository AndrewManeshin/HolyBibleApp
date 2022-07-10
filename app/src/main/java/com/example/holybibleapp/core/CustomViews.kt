package com.example.holybibleapp.core

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.holybibleapp.R

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), TextMapper {

    override fun map(data: String) {
        text = data
    }
}

interface TextMapper : Abstract.Mapper.Data<String, Unit>

class CollapseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr), CollapseMapper {

    override fun map(data: Boolean) {
        val iconId = if (data) {
            R.drawable.ic_baseline_expand_more_24
        } else {
            R.drawable.ic_baseline_expand_less_24
        }
        setImageResource(iconId)
    }
}

interface CollapseMapper : Abstract.Mapper.Data<Boolean, Unit>