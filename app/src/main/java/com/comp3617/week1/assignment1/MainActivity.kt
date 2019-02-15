package com.comp3617.week1.assignment1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var score = 0
    private var scoreRequest = 100
    private var qNumber = 0
    private var id: String = ""
    private var titleId: String = ""
    private var answers: Array<String?>? = null
    private var totalNumQuestions = 0
    private var selection = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Answers []
        answers = resources.getStringArray(R.array.answers)
        answers!!.forEach {
            println("answer: " + it)
            totalNumQuestions++
        }
    }

    // Gets question number text
    private fun getQuestion(): String? {
        var question = getString(resources.getIdentifier(id, "string", this.packageName))
        return question;
    }

    // Gets question number
    private fun getQuestionNum(questionNumber: Int): String? {
        setQuestionId(questionNumber)
        setTitleId(id)
        println("TITLE ID" + titleId)
        var num = getString(resources.getIdentifier(titleId, "string", this.packageName))
        return num;
    }

    // Retrieves correct answer for the question
    private fun getRightAnswer(questionNumber: Int): String {
        var answer = answers!![questionNumber]
        return answer.toString()
    }

    // Tells user what they chose and returns that selection as a string
    fun checkButton(v: View) {
        var radioId = radioGroup.checkedRadioButtonId
        var radioBtn: RadioButton = findViewById(radioId)
        selection = radioBtn.text.toString()

        Toast.makeText(this, "You chose: " + selection, Toast.LENGTH_SHORT).show()
    }

    // Event-handler for "Next" Button
    fun nextQuestion(v: View) {
        checkAnswer(v, qNumber)
        qNumber++
        if (qNumber < totalNumQuestions) {
            updateQuestion(qNumber)
        } else {
            startScoreActivity()
        }
    }

    // Opens the Score Activity
    private fun startScoreActivity() {
        val basicIntent = Intent(this, ScoreActivity::class.java)
        basicIntent.putExtra("score", score)
        startActivityForResult(basicIntent, scoreRequest)
    }

    // Checks if answer chosen is correct.  Award points if correct
    private fun checkAnswer(v: View, questionNumber: Int) {
        if (selection.compareTo(getRightAnswer(questionNumber)) == 0) {
            updateScore()
            Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
        }
    }

    // Updates Score
    private fun updateScore() {
        score += 10
        scoreValue.text = score.toString()
    }

    // Event-handler for "Start" button. Resets values
    fun startOver(v: View) {
        radioGroup.visibility = View.VISIBLE
        question_title.visibility = View.VISIBLE
        next_btn.visibility = View.VISIBLE
        scoreText.visibility = View.VISIBLE
        scoreValue.visibility = View.VISIBLE
        spidey_pose.visibility = View.VISIBLE
        questionText.visibility = View.VISIBLE
        spidey_intro.visibility = View.GONE
        introText.visibility = View.GONE
        resetValues()
    }

    // Resets values to original state
    private fun resetValues() {
        qNumber = 0
        score = 0
        setQuestionId(qNumber)
        setTitleId(id)
        scoreValue.text = score.toString()
        question_title.text = getQuestionNum(qNumber)
        questionText.text = getQuestion()
        updateChoices(id)
    }


    // Saves the state when the phone is rotated
    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putInt("score", score)
        outState!!.putInt("qNumber", qNumber)
        super.onSaveInstanceState(outState)
    }

    // Restores the state upon rotation
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        score = savedInstanceState!!.getInt("score", 0)
        qNumber = savedInstanceState!!.getInt("qNumber", 0)

        scoreValue.text = score.toString()
        question_title.text = getQuestionNum(qNumber)
        questionText.text = getQuestion()
        updateChoices(id)
    }

    // Sets Questions ID
    private fun setQuestionId(questionNumber: Int) {
        id = "q$questionNumber"
    }

    // Updates Question Number, Question, and Choices
    private fun updateQuestion(questionNumber: Int) {
        setQuestionId(questionNumber)
        setTitleId(id)
        question_title.text = getString(resources.getIdentifier(titleId, "string", this.packageName))
        questionText.text = getString(resources.getIdentifier(id, "string", this.packageName))

        updateChoices(id)
    }

    // Sets the Title ID
    private fun setTitleId(qId: String?) {
        titleId = qId + "t"
    }

    // Updates choices
    private fun updateChoices(id: String?) {
        for (i in 0 until radioGroup.childCount) {
            var choice = id + "c" + i
            println(choice)
            (radioGroup.getChildAt(i) as RadioButton).text =
                    getString(resources.getIdentifier(choice, "string", this.packageName))
        }
    }
}
