package com.timesheet.drinkinggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public int counter;
    Button button;
    Button button2;
    TextView text;
    EditText enter;
    View view;
    Boolean Alice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.TrialText);
//        enter = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        view = findViewById(R.id.MainActivity);
        view.setBackgroundColor(getResources().getColor(R.color.colourFour));

//        createNonSequence();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Alice = false;
                newPage();

//                createNonSequence();
//                if(!mTimerRunning){
//                    startTimer();
//                }else{
//                    pauseTimer();
//                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alice = true;
                newPage();
            }
        });
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                new CountDownTimer(30000, 1000){
//                    public void onTick(long millisUntilFinished){
//                        text.setText(String.valueOf(counter));
//                        counter++;
//                    }
//                    public  void onFinish(){
//                        text.setText("FINISH!!");
//
//                    }
//                }.start();
//            }
//        });


    }


//    public String createNonSequence() {
//        List<Integer> list = new ArrayList<>();
//
//        int min = 0;
//        int max = 10;
//        Random r = new Random();
//
//        for (int i = 0; i < 5; i++) {
//            int randomNumber = r.nextInt(max - min + 1) + min;
//
//            if (!list.contains(randomNumber)) {
//                list.add(randomNumber);
//            }else {
//                i = i-1;
//            }
//        }
//        return "Hey";
//    }


    public void newPage() {
        Intent intent = new Intent(MainActivity.this, PlayerPage.class);
//        Intent intent = new Intent(MainActivity.this, GamePage.class);
        intent.putExtra("Alice", Alice);
        startActivity(intent);
    }
}
