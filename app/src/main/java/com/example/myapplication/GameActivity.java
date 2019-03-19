package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


class dialogueNode {
    String dialogue;
    choice[] choices;
}

class choice {
    String name;
    int destIndex;


}

public class GameActivity extends AppCompatActivity {
    dialogueNode[] BobsGraph;
    static int time = 10;
    static int day = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get the intent and its data.
        Intent intent = getIntent();
        String employee = intent.getStringExtra("Employee");

        if (employee == "Bob")
        {
            time -= 1;
            final TextView convoText = findViewById(R.id.convoText);
            convoText.setText("Hello " + employee);

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