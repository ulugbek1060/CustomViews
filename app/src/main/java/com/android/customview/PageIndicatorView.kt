package com.android.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View

class PageIndicatorView constructor(
   context: Context,
   attrs: AttributeSet?,
   defStyleAttr: Int,
   defStyleRes: Int
) : View(context, attrs, defStyleAttr, defStyleRes) {

   constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
      context,
      attrs,
      defStyleAttr,
      0
   )

   constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
   constructor(context: Context) : this(context, null)

   init {
      val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PageIndicatorView, defStyleAttr, defStyleRes)
      val count = typedArray.getInt(R.styleable.PageIndicatorView_piv_count, 0)
      typedArray.recycle()
   }

   override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
      val widthMode = MeasureSpec.getMode(widthMeasureSpec)
      val widthSize = MeasureSpec.getSize(widthMeasureSpec)
      val heightMode = MeasureSpec.getMode(heightMeasureSpec)
      val heightSize = MeasureSpec.getSize(heightMeasureSpec)
      val width: Int
   }
}