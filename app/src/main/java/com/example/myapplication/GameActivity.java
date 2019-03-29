package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.EnumMap;
import java.util.HashMap;

enum Employee {
    NONE,
    BOB,

}


class ChoiceFinder {
    ChoiceFinder(Employee charName, int i) {
        characterName = charName;
        index = i;


    }

    Employee characterName;
    int index;

}


public class GameActivity extends AppCompatActivity {


    class CharacterChoices {
        Choice[] choices;
        CharacterChoices(Choice...mChoices) {

            choices = mChoices;
            for (Choice choice : choices) {
                choice.character = this;


            }
        }
        void showChoices()
        {

            for (final Choice choice : choices)
            {
                if (choice.checkUsable()) {
                    Button choiceButton = new Button(GameActivity.this);
                    choicesLayout.addView(choiceButton);

                    choiceButton.setText(choice.name);

                    choiceButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            choicesLayout.removeAllViews();
                            choice.showDialogue();
                        }

                    });
                }

            }
        }

    }

    class Choice {
        Choice(String name, int cost, String[] dialogue, ChoiceFinder...needed) {
            this.name = name;
            this.cost = cost;
            this.dialogue = dialogue;
            neededPrev = needed;
        }

        boolean checkUsable() {
            if (visited) {

                return false;
            }

            for (ChoiceFinder needed : neededPrev) {
                if (!characterChoices.get(needed.characterName).choices[needed.index].visited) {
                    return false;

                }

            }
            return true;
        }

        void showDialogue() {
            visited = true;
            nextDay.setEnabled(false);
            showEmp.setEnabled(false);
            convoText.setText(dialogue[0]);
            convoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (current == dialogue.length - 1) {
                        convoText.setText("");
                        nextDay.setEnabled(true);
                        showEmp.setEnabled(true);
                        character.showChoices();
                    }
                    else {
                        current++;
                        convoText.setText(dialogue[current]);

                    }
                }

            });


        }
        CharacterChoices character;
        ChoiceFinder[] neededPrev;
        int current;
        String[] dialogue;
        String name;
        int cost;
        boolean visited = false;
    }



    static EnumMap<Employee, CharacterChoices> characterChoices = new EnumMap<>(Employee.class);;


    TextView convoText;
    LinearLayout choicesLayout;
    Button nextDay;
    Button showEmp;
    static int time = 10;
    static int day = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        convoText = findViewById(R.id.convoText);
        choicesLayout = findViewById(R.id.choices);

        Intent intent = getIntent();
        Employee employee = (Employee) intent.getSerializableExtra("Employee");


        final TextView dayCount = findViewById(R.id.day);
        dayCount.setText(String.valueOf(day));

        nextDay = findViewById(R.id.nextDay);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convoText.setText("");
                choicesLayout.removeAllViews();
                day++;
                time = 10;
                dayCount.setText(String.valueOf(day));
            }
        });

        showEmp = findViewById(R.id.showEmp);
        showEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, EmployeesActivity.class);
                startActivity(intent);

            }

        });

        if (employee != Employee.NONE) {
            characterChoices.get(employee).showChoices();
        }
        else {
            characterChoices.put(Employee.BOB, new CharacterChoices(
                    new Choice("Hello", 1, new String[]{
                            "Hello.",
                            "k"
                    }
                    ),
                    new Choice("asdf?", 2, new String[]{
                            "qwer.",
                            "qwerrr."
                    }
                    ),
                    new Choice("How are you", 2, new String[]{
                            "Good."
                    },
                            new ChoiceFinder(Employee.BOB, 1),
                            new ChoiceFinder(Employee.BOB, 0))
            ));

        }



    }

}