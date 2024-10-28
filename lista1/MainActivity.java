package com.example.lista1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView mainField;
    RadioButton btnOne;
    RadioButton btnTwo;
    RadioButton btnThree;
    RadioButton btnFour;
    TextView mainText;
    ProgressBar mainBar;
    Button button;

    int questionNum = 0;
    int points = 0;

    String q[][] = {
            {"What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "Paris"},
            {"Which planet is known as the Red Planet?", "Venus", "Mars", "Jupiter", "Saturn", "Mars"},
            {"How many continents are there on Earth?", "5", "6", "7", "8", "7"},
            {"What is the boiling point of water at sea level?", "90°C", "100°C", "110°C", "120°C", "100°C"},
            {"What gas do plants absorb from the atmosphere?", "Oxygen", "Carbon Dioxide", "Nitrogen", "Helium", "Carbon Dioxide"},
            {"Which element has the atomic number 1?", "Oxygen", "Helium", "Hydrogen", "Carbon", "Hydrogen"},
            {"Who wrote 'Romeo and Juliet'?", "Charles Dickens", "Mark Twain", "William Shakespeare", "Jane Austen", "William Shakespeare"},
            {"What is the largest mammal?", "Elephant", "Blue Whale", "Giraffe", "Great White Shark", "Blue Whale"},
            {"How many colors are in a rainbow?", "5", "6", "7", "8", "7"},
            {"Which ocean is the largest?", "Atlantic", "Indian", "Arctic", "Pacific", "Pacific"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Collections.shuffle(Arrays.asList(q));

        mainField = findViewById(R.id.mainField);
        mainText = findViewById(R.id.mainText);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        mainBar = findViewById(R.id.mainBar);
        button = findViewById(R.id.buttonMain);

        loadQuestion(questionNum);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                RadioGroup mainGroup = (RadioGroup) findViewById(R.id.mainGroup);
                int selectedId = mainGroup.getCheckedRadioButtonId();
                RadioButton selectedButton = findViewById(selectedId);
                if (selectedButton != null && selectedButton.getText().equals(q[questionNum][5])) {
                    points++;
                }
                questionNum++;
                if (questionNum < q.length) {
                    loadQuestion(questionNum);
                    mainText.setText("Question " + (questionNum + 1) + "/10");
                } else {
                    mainText.setText("Congratulations! Your result: " + points + " pt");
                    mainBar.setVisibility(View.GONE);
                    mainGroup.setVisibility(View.GONE);
                    mainField.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                }

                mainGroup.clearCheck();
            }
        });
    }
    private void loadQuestion(int index) {
        mainField.setText(q[index][0]);
        btnOne.setText(q[index][1]);
        btnTwo.setText(q[index][2]);
        btnThree.setText(q[index][3]);
        btnFour.setText(q[index][4]);
    }
}