package com.android.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View

class MyCustomView constructor(
   context: Context,
   attrs: AttributeSet?,
   defStyleAttr: Int,
   defStyleRes: Int
) : View(context, attrs, defStyleAttr, defStyleRes) {
   private var mShowText = false
   private var textPos: Int = 0

   constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
      context,
      attrs,
      defStyleAttr,
      0
   )

   constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
   constructor(context: Context) : this(context, null)

   init {
      val typedArray =
         context.obtainStyledAttributes(attrs, R.styleable.MyCustomView, defStyleAttr, defStyleRes)
      mShowText = typedArray.getBoolean(R.styleable.MyCustomView_showText, false)
      textPos = typedArray.getInteger(R.styleable.MyCustomView_labelPosition, 0)
      typedArray.recycle()
   }

   fun isShowText(): Boolean {
      return mShowText
   }

   fun setShowText(showText: Boolean) {
      mShowText = showText
      invalidate()
      requestLayout()
   }


//   override fun onDraw(canvas: Canvas?) {
//      super.onDraw(canvas)
//   }
//
//   override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//      super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//   }
//
//   override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//      return super.onKeyDown(keyCode, event)
//   }


}