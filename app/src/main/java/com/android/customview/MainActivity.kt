package com.android.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

      binding.btnSad.setOnClickListener {
         binding.emoji.happinessState = EmotionalFaceView.SAD
      }

      binding.btnHappy.setOnClickListener {
         binding.emoji.happinessState = EmotionalFaceView.HAPPY
      }
   }
}