package com.example.sterling.hw1;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Stop Quiz")
                .setMessage("Are you sure you want to exit the quiz before finishing?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
