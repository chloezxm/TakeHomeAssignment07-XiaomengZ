package com.android.geoquiz;

import java.io.Serializable;

public class Question implements Serializable {

    private int questionId;
    private boolean answer;

    public Question(int questionId, boolean answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean isCorrectAnswer(boolean answer) {
        return this.answer == answer;
    }
}
