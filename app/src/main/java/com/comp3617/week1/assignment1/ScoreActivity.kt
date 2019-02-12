package com.comp3617.week1.assignment1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    var score : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        if (intent != null){
            score = intent.getIntExtra("score", 0)
            scoreValue.text = score.toString()
        }
    }

    // Note to self: probably want to use MainActivity.score to get score values
    fun restart (v : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun share (v : View){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$score")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Sending score of: " + score.toString()))
    }

}
