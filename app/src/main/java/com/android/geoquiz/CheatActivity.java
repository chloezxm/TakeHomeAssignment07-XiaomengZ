package com.android.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private TextView answerTextView;
    private Button showAnswerButton;

    private boolean cheated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerTextView = findViewById(R.id.answer_text);
        answerTextView.setText(R.string.empty_string);

        showAnswerButton = findViewById(R.id.show_answer_button);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheated = true;
                Intent intent = getIntent();
                boolean trueAnswer = intent.getBooleanExtra(Keys.ANSWER, false);
                if (trueAnswer) {
                    answerTextView.setText(R.string.true_answer);
                } else {
                    answerTextView.setText(R.string.false_answer);
                }

                Intent data = new Intent();
                data.putExtra(Keys.CHEATED, true);
                setResult(RESULT_OK, data);
            }
        });
    }
}
