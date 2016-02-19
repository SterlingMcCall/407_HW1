package com.example.sterling.hw1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuizActivity extends AppCompatActivity
    implements ImageQuestionFragment.OnFragmentInteractionListener, TextQuestionFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.quiz_fragment_container, ImageQuestionFragment.newInstance(null,null))
                .addToBackStack(null)
                .commit();
    }

    private int correctAnswers = 0;
    private int totalQuestions = 0;

    protected void addCorrect() {
        correctAnswers++;
    }

    protected void addQuestion() {
        totalQuestions++;
    }

    protected int getCorrect() {
        return correctAnswers;
    }

    protected int getQuestions() {
        return totalQuestions;
    }

    protected void resetCounts() {
        totalQuestions = 0;
        correctAnswers = 0;
    }

    void onSubmit(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
