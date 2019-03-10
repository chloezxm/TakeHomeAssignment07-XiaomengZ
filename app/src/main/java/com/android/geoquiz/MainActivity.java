package com.android.geoquiz;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final Question[] questions = new Question[]{
            new Question(R.string.question_longest_river, true),
            new Question(R.string.question_highest_mountain, true),
            new Question(R.string.question_largest_country, false),
            new Question(R.string.question_tallest_building, false),
            new Question(R.string.question_largest_ocean, true)
    };

    private TextView questionTextView;
    private Button answerTrueButton;
    private Button answerFalseButton;
    private Button cheatButton;
    private Button prevButton;
    private Button nextButton;

    private int currectQuestionIndex;
    private boolean cheated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currectQuestionIndex = 0;

        questionTextView = findViewById(R.id.question_text_view);
        moveToNewQuestion();

        answerTrueButton = findViewById(R.id.answer_true_button);
        answerTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        answerFalseButton = findViewById(R.id.answer_false_button);
        answerFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        cheatButton = findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                intent.putExtra(Keys.ANSWER, questions[currectQuestionIndex].getAnswer());
                startActivityForResult(intent, RequestCodes.CHEAT);
            }
        });

        prevButton = findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currectQuestionIndex--;
                if (currectQuestionIndex < 0) {
                    currectQuestionIndex = questions.length - 1;
                }
                moveToNewQuestion();
            }
        });

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currectQuestionIndex++;
                if (currectQuestionIndex >= questions.length) {
                    currectQuestionIndex = 0;
                }
                moveToNewQuestion();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RequestCodes.CHEAT && resultCode == RESULT_OK) {
            if (data.getBooleanExtra(Keys.CHEATED, false)) {
                cheated = true;
            }
        }
    }

    private void moveToNewQuestion() {
        cheated = false;
        questionTextView.setText(questions[currectQuestionIndex].getQuestionId());
    }

    private void checkAnswer(boolean answer) {
        int toastMessageId;
        if (cheated) {
            toastMessageId = R.string.cheated_toast;
        } else if (questions[currectQuestionIndex].isCorrectAnswer(answer)) {
            toastMessageId = R.string.correct_toast;
        } else {
            toastMessageId = R.string.incorrect_toast;
        }

        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}
