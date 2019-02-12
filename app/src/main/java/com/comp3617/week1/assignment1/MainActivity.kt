package com.comp3617.week1.assignment1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Variables for score tracking
    private var answer : String? = ""
    private var score = 0
    private var scoreRequest = 100
    private var qNumber = 0

    private var questions : Array<String?>? = null
    private var questionNum : Array<String?>? = null
    private var q1_choices : Array<String?>? = null
    private var q2_choices : Array<String?>? = null
    private var q3_choices : Array<String?>? = null
    private var q4_choices : Array<String?>? = null
    private var q5_choices : Array<String?>? = null
    private var answers : Array<String?>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Questions initialized
        questions = arrayOf(
            getString(R.string.q1_q),
            getString(R.string.q2_q),
            getString(R.string.q3_q),
            getString(R.string.q4_q),
            getString(R.string.q5_q))

        questionNum = arrayOf(
            getString(R.string.q1_t),
            getString(R.string.q2_t),
            getString(R.string.q3_t),
            getString(R.string.q4_t),
            getString(R.string.q5_t))

        // Choices [][]
        q1_choices = arrayOf(
            getString(R.string.q1_a),
            getString(R.string.q1_b),
            getString(R.string.q1_c),
            getString(R.string.q1_d))

        q2_choices = arrayOf(
            getString(R.string.q2_a),
            getString(R.string.q2_b),
            getString(R.string.q2_c),
            getString(R.string.q2_d))

        q3_choices = arrayOf(
            getString(R.string.q3_a),
            getString(R.string.q3_b),
            getString(R.string.q3_c),
            getString(R.string.q3_d))

        q4_choices = arrayOf(
            getString(R.string.q4_a),
            getString(R.string.q4_b),
            getString(R.string.q4_c),
            getString(R.string.q4_d))

        q5_choices = arrayOf(
            getString(R.string.q5_a),
            getString(R.string.q5_b),
            getString(R.string.q5_c),
            getString(R.string.q5_d))

        // Answers []
        answers = arrayOf(
            getString(R.string.q1_a),
            getString(R.string.q2_c),
            getString(R.string.q3_d),
            getString(R.string.q4_c),
            getString(R.string.q5_a)
        )

    }



    // Gets question
    private fun getQuestion(questionNumber : Int) : String? {
        var question = questions!![questionNumber]
        return question;
    }

    // Gets question number
    private fun getQuestionNum(questionNumber : Int) : String? {
        var questionNumber = questionNum!![questionNumber]
        return questionNumber;
    }

    // Gets selected choice
    private fun getChoice1(questionNumber : Int) : String? {
        var chosen0 = ""
        when (questionNumber) {
            0 ->
                chosen0 = q1_choices!![0].toString()
            1 ->
                chosen0 = q2_choices!![0].toString()
            2 ->
                chosen0 = q3_choices!![0].toString()
            3 ->
                chosen0 = q4_choices!![0].toString()
            4 ->
                chosen0 = q5_choices!![0].toString()
        }
        return chosen0;
    }

    private fun getChoice2(questionNumber : Int) : String? {
        var chosen1 = ""
        when (questionNumber) {
            0 ->
                chosen1 = q1_choices!![1].toString()
            1 ->
                chosen1 = q2_choices!![1].toString()
            2 ->
                chosen1 = q3_choices!![1].toString()
            3 ->
                chosen1 = q4_choices!![1].toString()
            4 ->
                chosen1 = q5_choices!![1].toString()
        }
        return chosen1;
    }

    private fun getChoice3(questionNumber : Int) : String? {
        var chosen2 = ""
        when (questionNumber) {
            0 ->
                chosen2 = q1_choices!![2].toString()
            1 ->
                chosen2 = q2_choices!![2].toString()
            2 ->
                chosen2 = q3_choices!![2].toString()
            3 ->
                chosen2 = q4_choices!![2].toString()
            4 ->
                chosen2 = q5_choices!![2].toString()
        }
        return chosen2;
    }

    private fun getChoice4(questionNumber : Int) : String? {
        var chosen3 = ""
        when (questionNumber) {
            0 ->
                chosen3 = q1_choices!![3].toString()
            1 ->
                chosen3 = q2_choices!![3].toString()
            2 ->
                chosen3 = q3_choices!![3].toString()
            3 ->
                chosen3 = q4_choices!![3].toString()
            4 ->
                chosen3 = q5_choices!![3].toString()
        }
        return chosen3;
    }

    // Retrieves correct answer for the question
    private fun getRightAnswer(questionNumber : Int) : String {
        var answer = answers!![questionNumber]
        return answer.toString()
    }

    fun checkButton(v : View) : String{
        var radioId = radioGroup.checkedRadioButtonId
        var radioBtn : RadioButton = findViewById(radioId)
        var selection : String = radioBtn.text.toString()

        Toast.makeText(this, "You chose: " + selection, Toast.LENGTH_LONG).show()
        return selection
    }

    fun nextQuestion(v : View){
        checkAnswer(v, qNumber)

        if (qNumber < 5) {
            //checkAnswer(qNumber)
            getNextQuestion(qNumber)
        } else {
            // start Score Activity
            startScoreActivity()
        }
    }

    private fun startScoreActivity(){
        val basicIntent = Intent(this, ScoreActivity::class.java)
        basicIntent.putExtra("score", score)
        startActivityForResult(basicIntent, scoreRequest)
    }

    fun checkAnswer(v : View, questionNumber: Int){
        if (checkButton(v).compareTo(getRightAnswer(questionNumber)) == 0 ) {
            updateScore()
            Toast.makeText(this, "CORRECT!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_LONG).show()
        }
        qNumber++
    }

    fun updateScore(){
        score += 10
        scoreValue.text = score.toString()
    }


    // Replaces Questions and choices to be asked
    private fun getNextQuestion(questionNumber : Int) {
        question_title.text = getQuestionNum(questionNumber)
        questionText.text = getQuestion(questionNumber)
        choice1.text = getChoice1(questionNumber)
        choice2.text = getChoice2(questionNumber)
        choice3.text = getChoice3(questionNumber)
        choice4.text = getChoice4(questionNumber)
    }

    fun startOver(v : View){
        resetValues()
    }

    private fun resetValues(){
        qNumber = 0
        score = 0
        scoreValue.text = score.toString()
        question_title.text = getQuestionNum(0)
        questionText.text = getQuestion(0)
        choice1.text = getChoice1(0)
        choice2.text = getChoice2(0)
        choice3.text = getChoice3(0)
        choice4.text = getChoice4(0)
    }

}
