package com.android.customview

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView constructor(
   context: Context,
   attrs: AttributeSet? = null
) : View(context, attrs) {

   companion object {
      private const val DEFAULT_FACE_COLOR = Color.YELLOW
      private const val DEFAULT_EYES_COLOR = Color.BLACK
      private const val DEFAULT_MOUTH_COLOR = Color.BLACK
      private const val DEFAULT_BORDER_COLOR = Color.BLACK
      private const val DEFAULT_BORDER_WIDTH = 4.0f

      const val HAPPY = 0L
      const val SAD = 1L
   }

   private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
   private val mouthPath = Path()

   private var faceColor = DEFAULT_FACE_COLOR
   private var eyesColor = DEFAULT_EYES_COLOR
   private var mouthColor = DEFAULT_MOUTH_COLOR
   private var borderColor = DEFAULT_BORDER_COLOR
   private var borderWidth = DEFAULT_BORDER_WIDTH

   private val borderWith = 4.0f
   private var size = 0

   var happinessState = HAPPY
      set(state) {
         field = state
         invalidate()
      }

   init {
      paint.isAntiAlias = true
      setupAttributes(attrs)
   }

   private fun setupAttributes(attrs: AttributeSet?) {
      // Obtain a typed array of attributes
      val typedArray =
         context.theme.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView, 0, 0)

      // Extract custom attributes into member variables
      happinessState =
         typedArray.getInt(R.styleable.EmotionalFaceView_state, HAPPY.toInt()).toLong()
      faceColor = typedArray.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
      eyesColor = typedArray.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
      mouthColor =
         typedArray.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
      borderColor =
         typedArray.getColor(R.styleable.EmotionalFaceView_borderColor, DEFAULT_BORDER_COLOR)
      borderWidth =
         typedArray.getDimension(R.styleable.EmotionalFaceView_borderWidth, DEFAULT_BORDER_WIDTH)

      // TypedArray objects are shared and must be recycled.
      typedArray.recycle()
   }

   override fun onDraw(canvas: Canvas?) {
      // call the super method to keep any drawing from the parent side.
      super.onDraw(canvas)

      drawFaceBackground(canvas)
      drawEyes(canvas)
      drawMouth(canvas)
   }


   private fun drawFaceBackground(canvas: Canvas?) {
      paint.color = faceColor
      paint.style = Paint.Style.FILL
      val radius = size / 2f
      canvas?.drawCircle(size / 2f, size / 2f, radius, paint)

      paint.color = borderColor
      paint.style = Paint.Style.STROKE
      paint.strokeWidth = borderWith
      canvas?.drawCircle(size / 2f, size / 2f, radius - borderWith / 2f, paint)
   }

   private fun drawEyes(canvas: Canvas?) {
      paint.color = eyesColor
      paint.style = Paint.Style.FILL

      val leftEyesRect = RectF(
         size * 0.32f,
         size * 0.23f,
         size * 0.43f,
         size * 0.50f
      )

      canvas?.drawOval(leftEyesRect, paint)

      val rightEyesRect = RectF(
         size * 0.57f,
         size * 0.23f,
         size * 0.68f,
         size * 0.50f
      )

      canvas?.drawOval(rightEyesRect, paint)
   }

   private fun drawMouth(canvas: Canvas?) {
      // Clear
      mouthPath.reset()

      mouthPath.moveTo(size * 0.22f, size * 0.7f)

      if (happinessState == HAPPY) {
         // Happy mouth path
         mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
         mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
      } else {
         // Sad mouth path
         mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
         mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
      }

      paint.color = mouthColor
      paint.style = Paint.Style.FILL

      canvas?.drawPath(mouthPath, paint)
   }

   override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec)
      size = measuredWidth.coerceAtMost(measuredHeight)
      setMeasuredDimension(size, size)
   }

//   override fun onSaveInstanceState(): Parcelable {
//      // 1
//      val bundle = Bundle()
//      // 2
//      bundle.putLong("happinessState", happinessState)
//      // 3
//      bundle.putParcelable("superState", super.onSaveInstanceState())
//      return bundle
//   }
//
//   override fun onRestoreInstanceState(state: Parcelable) {
//      // 4
//      var viewState = state
//      if (viewState is Bundle) {
//         // 5
//         happinessState = viewState.getLong("happinessState", HAPPY)
//         // 6
//         viewState = viewState.getParcelable("superState")!!
//      }
//      super.onRestoreInstanceState(viewState)
//   }
}