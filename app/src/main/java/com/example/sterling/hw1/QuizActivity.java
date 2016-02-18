package com.example.sterling.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.quiz_fragment_container, ImageQuestionFragment.newInstance(null, null))
                .addToBackStack(null)
                .commit();
    }
}
