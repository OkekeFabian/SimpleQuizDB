package com.example.simplequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.Switch;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuestionDb extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    public static final String DB_NAME = "DB_QUESTIONS";

    public QuestionDb(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Question.CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Question.TABLE_NAME);
        onCreate(db);
    }


    public List<Integer> getAllId() {
        SQLiteDatabase qdb = this.getWritableDatabase();
        String SELECT = "SELECT id from questions";

        Cursor cursor = qdb.rawQuery(SELECT, null);
        List<Integer> idList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            idList.add(cursor.getInt(cursor.getColumnIndex("id")));
            while (cursor.moveToNext()) {
                idList.add(cursor.getInt(cursor.getColumnIndex("id")));
            }
        }
        return idList;
    }

    public long addQuestion(Question question) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Question.COLUMN_QUESTION_BODY, question.getBody());
        values.put(Question.COLUMN_CORRECT_OPTIONS, serialize(question.getAnswers()));
        values.put(Question.COLUMN_OPTIONS_TEXT, serialize(question.getOptions()));

        return sqLiteDatabase.insert(Question.TABLE_NAME, null, values);

    }

    public List<Question> getAllQuestions() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        List<Question> questions = new ArrayList<>();

        Cursor c;

        c = sqLiteDatabase.rawQuery("SELECT * FROM " + Question.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question(
                        c.getString(c.getColumnIndex(Question.COLUMN_QUESTION_BODY)),
                        deserialize(c.getString(c.getColumnIndex(Question.COLUMN_OPTIONS_TEXT))),
                        deserialize(c.getString(c.getColumnIndex(Question.COLUMN_CORRECT_OPTIONS)))
                );
                questions.add(question);
            } while (c.moveToNext());
        }
        return questions;
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Question.TABLE_NAME);

    }

    private String serialize(String[] arr) {
        return TextUtils.join("@#@", arr);
    }

    private String[] deserialize(String input) {
        return input.split("@#@");
    }
}
