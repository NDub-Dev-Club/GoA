package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

class DialogueGraph {


    DialogueNode[] data;
    int currentNode = 0;

    void printDialogue(TextView toPrint)
    {

        toPrint.setText(data[currentNode].dialogue);
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

        Intent intent = getIntent();
        String employee = intent.getStringExtra("Employee");


        TextView convoText = findViewById(R.id.convoText);
        dialogueGraphs.get(employee).printDialogue(convoText);






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