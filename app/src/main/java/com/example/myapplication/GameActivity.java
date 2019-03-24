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

import java.util.HashMap;


class CharacterChoices {
    Choice[] choices;
    CharacterChoices(Choice...choices) {

        this.choices = choices;

    }
    void showChoices(final LinearLayout choicesLayout, final GameActivity theClass)
    {

        for (final Choice choice : choices)
        {

            Button choiceButton = new Button(theClass);
            choicesLayout.addView(choiceButton);

            choiceButton.setText(choice.name);

            choiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choicesLayout.removeAllViews();
                }

            });


        }
    }

}

class Choice {
    Choice(String name, int cost, String...dialogue) {
        this.name = name;
        this.cost = cost;
        this.dialogue = dialogue;

    }

    void showDialogue(final TextView convoText) {

        convoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (current + 1 == dialogue.length) {
                    showChoices(choicesLayout, theClass);
                }
                else {
                    current++;
                    convoText.setText(dialogue[current]);
                }
            }

        });


    }
    int current;
    String[] dialogue;
    String name;
    int cost;

}

public class GameActivity extends AppCompatActivity {
    static HashMap<String, CharacterChoices> characterChoices = new HashMap<String, CharacterChoices>();

    static int time = 10;
    static int day = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        characterChoices.put("Bob", new CharacterChoices(
                new Choice("Hello", 1, "Bob: Hello."),
                new Choice("asdf?", 2, "Bob: qwer."),
                new Choice("How are you", 2, "Bob: good.")
        ));


        Intent intent = getIntent();
        String employee = intent.getStringExtra("Employee");


        final TextView convoText = findViewById(R.id.convoText);
        final LinearLayout choicesLayout = findViewById(R.id.choices);
        if (!employee.equals("None")) {
            dialogueGraphs.get(employee).printDialogue(convoText);

            dialogueGraphs.get(employee).showChoices(choicesLayout, convoText, this);
        }





        final TextView dayCount = findViewById(R.id.day);
        dayCount.setText(String.valueOf(day));

        Button nextDay = findViewById(R.id.nextDay);
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

        Button showEmp = findViewById(R.id.showEmp);
        showEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, EmployeesActivity.class);
                startActivity(intent);

            }

        });
    }

}