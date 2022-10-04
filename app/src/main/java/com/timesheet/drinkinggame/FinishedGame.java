package com.timesheet.drinkinggame;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishedGame extends AppCompatActivity {

    Button btEnd;
    TextView tvEnd;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_finished_game);


        btEnd = findViewById(R.id.btFinished);
        tvEnd = findViewById(R.id.tvFinishedGame);
        view = findViewById(R.id.gameFinish);
        view.setBackgroundColor(getResources().getColor(R.color.colourFour));


        tvEnd.setText(getString(R.string.Game_Over));
        btEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                finish();
            }
        });
    }
}
