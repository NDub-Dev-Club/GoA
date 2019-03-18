package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {

    int day = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get the intent and its data.
        Intent intent = getIntent();
        String message = intent.getStringExtra("Employee");
        final TextView employee_msg = findViewById(R.id.employeeText);
        employee_msg.setText(message);






        final TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(day));

        Button nextDay = findViewById(R.id.nextDay);
        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day++;
                textView.setText(String.valueOf(day));
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
