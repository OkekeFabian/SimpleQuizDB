package com.example.simplequiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuestionDb questionDb;
    RadioButton[] radioButtons = new RadioButton[5];
    RadioGroup radioGroup;
    Switch aSwitch;
    ToggleButton toggleButton;
    TextView questionTv;
    Button submit;
    CheckBox[] checkBoxes = new CheckBox[4];
    LinearLayout checkGroup;
    int currentQuestion = 0;
    int correctAnswers = 0;
    int totalQuestions = 6;
    Button clearBt;


    Question[] questions = new Question[]{
            new Question("Politechnika slaski was founded in what year?",
                    new String[]{
                            "1991", "1940", "1947", "1960", "2000"
                    }, new String[]{
                    "1940"
            }),
            new Question("What year did South Africa hosted the world cup?",
                    new String[]{
                            "1994", "2006", "2014", "1996", "2010"
                    }, new String[]{
                    "2010"
            }),

            new Question("Is Japan a city?",
                    new String[]{
                            "Yes", "No"
                    }, new String[]{
                    "No"
            })

            , new Question("Is San Antonio River in Texas?",
            new String[]{
                    "Yes", "No"
            }, new String[]{
            "Yes"
    }),
            new Question("Select the name of cities in poland?",
                    new String[]{
                            "California", "Lodz", "Katowice", "Wroclaw"
                    }, new String[]{
                    "false","true", "true","true"
            }),
            new Question("Pick programming languages?",
                    new String[]{
                            "Sleep", "JAVA", "Spring", "Python"
                    }, new String[]{
                    "false","true", "false","true"
            }),


    };


    List<Question> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkGroup = findViewById(R.id.checkGroup);
        radioGroup = findViewById(R.id.radioGroup);
        for (int i = 0; i < 5; i++) {
            radioButtons[i] = radioGroup.findViewWithTag(i + "");
        }

        aSwitch = findViewById(R.id.switchButton);
        toggleButton = findViewById(R.id.toggle);
        questionTv = findViewById(R.id.question);
        clearBt = findViewById(R.id.clearButton);
        submit = findViewById(R.id.submitButton);
        questionDb = new QuestionDb(this);

//        System.out.println("this is the point" + questionDb.getDatabaseName());

      //  onResume();
        System.out.println();
        questionDb.getAllQuestions().stream().
                forEach(c -> System.out.println(c.toString()));

        questionList = questionDb.getAllQuestions();

        System.out.println(questionDb.getAllId());
        clearBt.setOnClickListener(v -> {
            clearBt.setText("clear");
            aSwitch.setChecked(false);
            toggleButton.setChecked(false);
            radioGroup.clearCheck();
            correctAnswers = 0;
            currentQuestion = 0;
           toggleButton.setVisibility(View.INVISIBLE);
            aSwitch.setVisibility(View.INVISIBLE);
            checkGroup.setVisibility(View.INVISIBLE);

            showQuestion();
            try {
                for (CheckBox c : checkBoxes) {
                    c.setChecked(false);
                 //   showQuestion();
                }
            } catch (Exception e) {
                Log.d("error", "Could not clear the screen");
            }
        });
    }

    private void showQuestion() {
        if (currentQuestion == 0) {
            questionTv.setText(questionList.get(0).getBody());

            for (int i = 0; i < questionList.get(0).getOptions().length; i++) {
                radioButtons[i].setText(questionList.get(0).getOptions()[i]);
            }

            radioButtons[0].setSelected(true);
            radioGroup.setVisibility(View.VISIBLE);
            final int correct_answer = 1; //set correct answer
            submit.setOnClickListener(v -> {
                radioGroup.setVisibility(View.GONE);
                if (radioGroup.getCheckedRadioButtonId() == radioButtons[correct_answer].getId()) {
                    System.out.println("was here " + radioGroup.getCheckedRadioButtonId() );
                    System.out.println("was here " + radioButtons[correct_answer].getId() );
                    correctAnswers++;
                }
                currentQuestion++;
                radioGroup.clearCheck();
                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();

            });
        } else if (currentQuestion == 1) {
            questionTv.setText(questionList.get(1).getBody());
            radioButtons[0].setSelected(true);
            for (int i = 0; i < questionList.get(1).getOptions().length; i++) {
                radioButtons[i].setText(questionList.get(1).getOptions()[i]);
            }

            radioGroup.setVisibility(View.VISIBLE);
            final int correct_answer = 4; //set correct answer
            submit.setOnClickListener(v -> {
                radioGroup.setVisibility(View.GONE);

                System.out.println(radioGroup.getCheckedRadioButtonId());
                System.out.println(radioButtons[correct_answer].getId());
//                if(questionList.get(1).getAnswers()[correct_answer] == radioButtons[correct_answer].getText()){
//                    System.out.println("print the fuck here");
//                }
                if (radioGroup.getCheckedRadioButtonId() == radioButtons[correct_answer].getId()) {
                    System.out.println("was here "+radioGroup.getCheckedRadioButtonId());
                    correctAnswers++;
                }
                currentQuestion++;
                radioGroup.clearCheck();

                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();
            });
        } else if (currentQuestion == 2) {
            questionTv.setText(questionList.get(2).getBody()); //todo
            toggleButton.setTextOn(questionList.get(2).getOptions()[0]);
            toggleButton.setTextOff(questionList.get(2).getOptions()[1]);
            toggleButton.setVisibility(View.VISIBLE);
            boolean correctSelection = false; //todo

            submit.setOnClickListener(v -> {

                toggleButton.setVisibility(View.GONE);
                if (v.isSelected() == correctSelection) {
                    System.out.println(v.isSelected() );
                    System.out.println(correctSelection);
                    correctAnswers++;
                }
                currentQuestion++;
                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();
            });
        } else if (currentQuestion == 3) {
            questionTv.setText(questionList.get(3).getBody());
            aSwitch.setVisibility(View.VISIBLE);
            boolean correctSelection = false;
            aSwitch.setTextOff(questionList.get(3).getOptions()[0]);
            aSwitch.setTextOn(questionList.get(3).getOptions()[1]);
            submit.setOnClickListener(v -> {
                aSwitch.setVisibility(View.GONE);
                System.out.println(v.isSelected() );
                if (v.isSelected() == correctSelection) {
                    System.out.println(v.isSelected() );
                    System.out.println(correctSelection);
                    correctAnswers++;
                    System.out.println("code was here");
                    Log.i("answer:", " THis was correct");
                }
                currentQuestion++;

                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();
            });
        } else if (currentQuestion == 4) {
            checkGroup.setVisibility(View.VISIBLE);
            questionTv.setText(questionList.get(4).getBody());
            for (int i = 0; i < 4; i++) {
                checkBoxes[i] = checkGroup.findViewWithTag(i + "");
                checkBoxes[i].setChecked(false);
            }

            for (int i = 0; i < questionList.get(4).getOptions().length; i++) {
                checkBoxes[i].setText(questionList.get(4).getOptions()[i]);
            }


            boolean[] correctOptions = new boolean[]{Boolean.parseBoolean(questionList.get(4).getAnswers()[0]),
                    Boolean.parseBoolean(questionList.get(4).getAnswers()[1]),
                    Boolean.parseBoolean(questionList.get(4).getAnswers()[2]),
                    Boolean.parseBoolean(questionList.get(4).getAnswers()[3])};
            submit.setOnClickListener(v -> {
                checkGroup.setVisibility(View.GONE);
                boolean correct = true;
                for (int i = 0; i < 4; i++) {
                    if (checkBoxes[i].isChecked() != correctOptions[i]) {
                        correct = false;
                        break;
                    }
                }
                if (correct) correctAnswers++;
                currentQuestion++;

                if (currentQuestion >= totalQuestions)
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                else showQuestion();
            });

        } else if (currentQuestion == 5) {
            checkGroup.setVisibility(View.VISIBLE);
            questionTv.setText(questionList.get(5).getBody());
            for (int i = 0; i < 4; i++) {
                checkBoxes[i] = checkGroup.findViewWithTag(i + "");
                checkBoxes[i].setChecked(false);
            }

            for (int i = 0; i < questionList.get(5).getOptions().length; i++) {
                checkBoxes[i].setText(questionList.get(5).getOptions()[i]);
            }

            boolean[] correctOptions = new boolean[]{Boolean.parseBoolean(questionList.get(5).getAnswers()[0]),
                    Boolean.parseBoolean(questionList.get(5).getAnswers()[1]),
                    Boolean.parseBoolean(questionList.get(5).getAnswers()[2]),
                    Boolean.parseBoolean(questionList.get(5).getAnswers()[3])};
            submit.setOnClickListener(v -> {
                checkGroup.setVisibility(View.GONE);
                boolean correct = true;
                for (int i = 0; i < 4; i++) {
                    if (checkBoxes[i].isChecked() != correctOptions[i]) {
                        correct = false;
                        break;
                    }
                }
                if (correct) correctAnswers++;
                currentQuestion++;

                if (currentQuestion >= totalQuestions) {
                    Toast.makeText(this, correctAnswers + " questions answered correctly", Toast.LENGTH_LONG).show();
                    questionTv.setText("QUIZ FINISHED");
                    currentQuestion = 0;
                } else{
                    currentQuestion = 0;
                    showQuestion();
                }
            });

        }
    }

    private void fillQuestionDb() {
        for (int i = 0; i < questions.length; i++) {
            questionDb.addQuestion(questions[i]);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        questionDb.clearDatabase();
        fillQuestionDb();
        System.out.println("RESUME");


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fillQuestionDb();
        System.out.println("RESTART");
    }
}
