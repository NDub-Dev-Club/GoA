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

class DialogueGraph {


    DialogueNode[] data;
    int currentNode = 0;

    void printDialogue(TextView toPrint)
    {

        toPrint.setText(data[currentNode].dialogue);
    }
    void showChoices(final LinearLayout choicesLayout, final TextView convoText, final GameActivity theClass)
    {
        int spacing = 0;
        for (final Choice choice : data[currentNode].choices)
        {

            Button choiceButton = new Button(theClass);
            choicesLayout.addView(choiceButton,0);

            choiceButton.setText(choice.name);

            choiceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choicesLayout.removeAllViews();

                    currentNode = choice.destIndex;
                    printDialogue(convoText);
                    showChoices(choicesLayout, convoText, theClass);
                }

            });

            spacing++;
        }
    }
}


class DialogueNode {
    String dialogue;
    Choice[] choices;
}

class Choice {
    String name;
    int destIndex;
    int cost;

}

public class GameActivity extends AppCompatActivity {
    static HashMap<String, DialogueGraph> dialogueGraphs = new HashMap<String, DialogueGraph>();

    static int time = 10;
    static int day = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        dialogueGraphs.put("Bob", new DialogueGraph());
        dialogueGraphs.get("Bob").data = new DialogueNode[3];
        dialogueGraphs.get("Bob").data[0] = new DialogueNode();
        dialogueGraphs.get("Bob").data[0].dialogue = "Bob: Hello.";

        dialogueGraphs.get("Bob").data[0].choices = new Choice[2];
        dialogueGraphs.get("Bob").data[0].choices[0] = new Choice();
        dialogueGraphs.get("Bob").data[0].choices[0].name = "Hello";
        dialogueGraphs.get("Bob").data[0].choices[0].cost = 1;
        dialogueGraphs.get("Bob").data[0].choices[0].destIndex = 1;

        dialogueGraphs.get("Bob").data[0].choices[1] = new Choice();
        dialogueGraphs.get("Bob").data[0].choices[1].name = "How are you";
        dialogueGraphs.get("Bob").data[0].choices[1].cost = 2;
        dialogueGraphs.get("Bob").data[0].choices[1].destIndex = 2;

        dialogueGraphs.get("Bob").data[1] = new DialogueNode();
        dialogueGraphs.get("Bob").data[1].dialogue = "Bob: ok.";
        dialogueGraphs.get("Bob").data[1].choices = new Choice[0];

        dialogueGraphs.get("Bob").data[2] = new DialogueNode();
        dialogueGraphs.get("Bob").data[2].dialogue = "Bob: good.";
        dialogueGraphs.get("Bob").data[2].choices = new Choice[0];

        Intent intent = getIntent();
        String employee = intent.getStringExtra("Employee");


        TextView convoText = findViewById(R.id.convoText);

        if (!employee.equals("None")) {
            dialogueGraphs.get(employee).printDialogue(convoText);
            LinearLayout choicesLayout = findViewById(R.id.choices);
            dialogueGraphs.get(employee).showChoices(choicesLayout, convoText, this);
        }





        final TextView dayCount = findViewById(R.id.day);
        dayCount.setText(String.valueOf(day));

        Button nextDay = findViewById(R.id.nextDay);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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