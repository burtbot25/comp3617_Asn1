package com.comp3617.week1.assignment1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var radioButton : RadioButton? = null;

    // Variables for score tracking
    var answer : String? = ""
    var score = 0
    var qNumber = 0
    var isCorrect = false

    var questions : Array<String?>? = null
    var questionNum : Array<String?>? = null
    var q1_choices : Array<String?>? = null
    var q2_choices : Array<String?>? = null
    var q3_choices : Array<String?>? = null
    var q4_choices : Array<String?>? = null
    var q5_choices : Array<String?>? = null
    var answers : Array<String?>? = null

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

        // variables to hold views/controls
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val questionText = findViewById<TextView>(R.id.questionText)
        val choice1 = findViewById<RadioButton>(R.id.choice1)
        val choice2 = findViewById<RadioButton>(R.id.choice2)
        val choice3 = findViewById<RadioButton>(R.id.choice3)
        val choice4 = findViewById<RadioButton>(R.id.choice4)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

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
    private fun getRightAnswer(questionNumber : Int) : String? {
        var answer = answers!![questionNumber]
        return answer;
    }

    fun checkButton(v : View){
        var radioId = radioGroup.checkedRadioButtonId
        var radioBtn : RadioButton = findViewById(radioId)
        var text : String = radioBtn.text.toString()

        Toast.makeText(this, "You chose: " + text, Toast.LENGTH_LONG).show()
    }

    fun nextQuestion(v : View){
        qNumber++
        if (qNumber < 5) {
            getNextQuestion(qNumber)
        } else {
            // start Score Activity
        }
    }

    // Replaces Questions and choices to be asked
    private fun getNextQuestion(questionNumber : Int) {
        question_title.text = getQuestionNum(questionNumber)
        questionText.text = getQuestion(questionNumber)
        choice1.text = getChoice1(questionNumber)
        choice2.text = getChoice2(questionNumber)
        choice3.text = getChoice3(questionNumber)
        choice4.text = getChoice4(questionNumber)

        answer = getRightAnswer(questionNumber)
    }
}
