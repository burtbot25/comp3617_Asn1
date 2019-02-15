package com.comp3617.week1.assignment1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    private var score: Int = 0
    private var scorePct: Double = 0.0

    // Will need to update this variable if more questions are added
    private var maxScore: Double = 50.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        if (intent != null) {
            score = intent.getIntExtra("score", 0)
            scoreValue.text = score.toString() + "/" + maxScore.toInt() + " pts"
        }

        scoreToPct()
        determineResult()

    }

    // Converts score to percentage
    private fun scoreToPct() {
        scorePct = score.toDouble() / maxScore
        println("SCORE PERCENTAGE IS : " + scorePct)
    }

    // Determines result text and corresponding image based on User's score
    // Each score range will generate a specific message and image
    private fun determineResult() {
        if (scorePct == 0.0) {
            imageView.setImageResource(R.drawable.spidey_no_more)
            resultText.text = getString(resources.getIdentifier("pct0", "string", this.packageName))
        } else if (0.0 < scorePct && scorePct <= 0.2) {
            imageView.setImageResource(R.drawable.spidey_facepalm)
            resultText.text = getString(resources.getIdentifier("pct20", "string", this.packageName))
        } else if (0.2 < scorePct && scorePct <= 0.4) {
            imageView.setImageResource(R.drawable.spidey_shrug)
            resultText.text = getString(resources.getIdentifier("pct40", "string", this.packageName))
        } else if (0.4 < scorePct && scorePct <= 0.6) {
            imageView.setImageResource(R.drawable.spidey_okay)
            resultText.text = getString(resources.getIdentifier("pct60", "string", this.packageName))
        } else if (0.6 < scorePct && scorePct <= 0.8) {
            imageView.setImageResource(R.drawable.spidey_spectacular)
            resultText.text = getString(resources.getIdentifier("pct80", "string", this.packageName))
        } else if (scorePct > 0.8) {
            imageView.setImageResource(R.drawable.spidey_awesome)
            resultText.text = getString(resources.getIdentifier("pct100", "string", this.packageName))
        }
    }

    // Returns to main activity
    fun restart(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun share(v: View) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$score")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Sending score of: " + score.toString()))
    }

}
