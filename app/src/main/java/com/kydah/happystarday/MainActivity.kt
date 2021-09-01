package com.kydah.happystarday

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ttsButton = findViewById<Button>(R.id.tts_button)
        ttsButton.setOnClickListener {
            textToSpeech(this, "Happy Star Day Mr ung. This message was brought to you by M 2 1 4 0 4.")
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        val snapHelper: LinearSnapHelper = object : LinearSnapHelper() {
            override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager, velocityX: Int, velocityY: Int): Int {
                val centerView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
                val position = layoutManager.getPosition(centerView)
                var targetPosition = -1
                if (layoutManager.canScrollHorizontally()) {
                    targetPosition = if (velocityX < 0) position - 1 else position + 1
                }
                if (layoutManager.canScrollVertically()) {
                    targetPosition = if (velocityY < 0) position - 1 else position + 1
                }
                return min(layoutManager.itemCount - 1, max(0, targetPosition))
            }
        }
        snapHelper.attachToRecyclerView(recyclerView)
        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = RecyclerViewAdapter()
        recyclerView.addItemDecoration(LinePagerIndicatorDecoration());
    }

    private fun textToSpeech(context: Context, text: String){
        var tts: TextToSpeech? = null   // funny not initialised error if not nullable
        tts = TextToSpeech(context, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS){
                val result = tts!!.setLanguage(Locale.US)
                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("TTS","This Language is not supported");
                }
                Toast.makeText(getApplicationContext(), "TTS is playing...", Toast.LENGTH_SHORT).show();
                tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            } else {
                Log.e("TTS", "Initialisation failed")
            }
        })
    }

}