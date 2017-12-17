package com.hari.kotlintutorial.common

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.annotation.StyleableRes
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.hari.kotlintutorial.R
import kotlinx.android.synthetic.main.progress_bar.view.*

/**
 * @author Hari Hara Sudhan.N
 *
 * @attr ref android.R.styleable#assignment_progress_bar_progressDrawable
 * @attr ref android.R.styleable#assignment_progress_bar_progressTextSize
 * @attr ref android.R.styleable#assignment_progress_bar_progressTextColor
 */
class ProgressBar @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null,
                                            defStyle: Int = 0)
    : ConstraintLayout(context, attributeSet, defStyle) {

    // Progress bar maximum limit
    private val maxPercentage = resources.getInteger(R.integer.progress_bar_max_value)
    // Default size of the text
    private val defaultTextSize = 12f

    init {
        View.inflate(context, R.layout.progress_bar, this)
        // Let the block to execute if the attributeSet is not null
        attributeSet?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.assignment_progress_bar)

            // Different for loop types

            // for loop with closed range from 0 to size of attributes
            for (i in 0..attributes.indexCount) {
                setAttributeStyles(attributes, attributes.getIndex(i))
            }

            // for loop from 0 until attributes size does not include the last one
            // example if `attributes.indexCount` is 10 does not include 10 while looping
//                for (i in 0 until attributes.indexCount) {
//                    setAttributeStyles(attributes, attributes.getIndex(i))
//                }

            // for loop starts from 2 to size of attributes includes the last count
//                  for(i in 2..attributes.indexCount step 2) {
//                      setAttributeStyles(attributes, attributes.getIndex(i))
//                  }

            // for loop in reverse from size of the attributes to 0
//                    for(i in attributes.indexCount downTo 0) {
//                      setAttributeStyles(attributes, attributes.getIndex(i))
//                    }

            // Same kind of check can also be done with `if`
            // if `i` is between 0 to attributes size then condition pass
            //            if(i in 0..attributes.indexCount) {
//
//            }

            attributes.recycle()
        }
    }

    /**
     * Function that set the styleable custom attributes to the view.
     * Can also used to set value at run time.
     *
     * @param attributes - contains all styleable attribute.
     * @param styleable  - styleable resource key.
     */
    fun setAttributeStyles(attributes: TypedArray, @StyleableRes styleable: Int) {
        // `when` is equivalent to `switch` statement in java.
        when (styleable) {
        // Drawable for progress bar
            R.styleable.assignment_progress_bar_progressDrawable ->
                setProgressDrawable(attributes.getDrawable(styleable))
        // Text size
            R.styleable.assignment_progress_bar_progressTextSize ->
                setTextSize(attributes.getFloat(styleable, defaultTextSize))
        // Text color
            R.styleable.assignment_progress_bar_progressTextColor ->
                setTextColor(attributes.getColor(styleable, Color.BLACK))
        }
    }

    /**
     * Sets the current progress in the progress bar.
     */
    fun setProgress(progressValue: Int, maxValue: Int) {
        val progress = progressValue * maxPercentage / maxValue
        progress_bar_view.progress = progress
        progress_percentage.text = resources.getString(R.string.assignment_current_progress, progress)
    }

    /**
     * Set drawable for progress in progress bar
     *
     * @param progressDrawable - drawable that to be set for progress.
     */
    fun setProgressDrawable(progressDrawable: Drawable?) {
        if (null != progressDrawable) {
            progress_bar_view.progressDrawable = progressDrawable
        }
    }

    /**
     * Sets the size of the text.
     *
     * @param textSize size of the text in Scale-independent Pixels.
     */
    fun setTextSize(textSize: Float) {
        progress_percentage.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
    }

    /**
     * Sets the text color.
     *
     * @param textColor color of the text.
     */
    fun setTextColor(textColor: Int) {
        progress_percentage.setTextColor(textColor)
    }
}
