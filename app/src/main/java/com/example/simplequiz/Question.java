package com.example.simplequiz;

import java.util.Arrays;

public class Question {

    private String body;

    private String[] options;

    private String[] answers;

    public static final String TABLE_NAME = "questions";
    public static final String COLUMN_QUESTION_BODY = "question_body";
    public static final String COLUMN_OPTIONS_TEXT = "options";
    public static final String COLUMN_CORRECT_OPTIONS = "answer";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_QUESTION_BODY + " varchar(250)," +
            COLUMN_OPTIONS_TEXT + " varchar(250)," +
            COLUMN_CORRECT_OPTIONS + " varchar(50) " +
            ")";

    public Question(String body, String[] options, String[] answers) {
        this.body = body;
        this.options = options;
        this.answers = answers;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "body='" + body + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }
}
