package com.timesheet.drinkinggame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GamePage extends AppCompatActivity {

    private final static String LOG_TAG_UI = "LOG_TAG_UI";
    final private int gameSize = 30;
    View view;
    Button back;
    TextView rule;
    TextView gamePageTV;
    //    private TextView mTextViewCountDown;
//    private Button mButtonStartPause;
    ArrayList<String> lines = new ArrayList<>();
    ArrayList<String> players = new ArrayList<>();
    ArrayList<String> game = new ArrayList<>();
    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<String> RuleBook = new ArrayList<>();
    ArrayList<Integer> colourCounter = new ArrayList<>();
    ArrayList<Integer> rulesAdd = new ArrayList<>();
    private int counter = 0;
    private int colour = 0;
    //    private String onePlayer = "gameRules";
//    private String twoPlayer = "gameRules1";
//    private String threePlayer = "gameRules2";
    private String rules = "Rules";
    private String Trial = "Trial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_game_page);

//        ArrayList<String> list = getIntent().getStringArrayListExtra("arraylist");
        ArrayList<String> game = getIntent().getStringArrayListExtra("Game");
//        ArrayList<String> numbers = getIntent().getStringArrayListExtra("numbers");

        colourCounter = getIntent().getIntegerArrayListExtra("colourCounter");
        rulesAdd = getIntent().getIntegerArrayListExtra("rulesAdd");
        numbers = getIntent().getStringArrayListExtra("numbers");

//        counter = 0;
//        ArrayList<String> list = getIntent().getStringArrayListExtra("arraylist");
//        players.addAll(list);
        gamePageTV = findViewById(R.id.textView_gamePage);
        view = findViewById(R.id.gameLayout);
        back = findViewById(R.id.backButton);
        rule = findViewById(R.id.tvRule);

        if (savedInstanceState != null) {
            Log.d(LOG_TAG_UI, "Main activity onCreate savedInstanceState is not null.");
//            game = savedInstanceState.getStringArrayList("games");
//            numbers = savedInstanceState.getStringArrayList("numbers");
            colourCounter = savedInstanceState.getIntegerArrayList("colour");
//            rulesAdd = savedInstanceState.getIntegerArrayList("rule");
            counter = savedInstanceState.getInt("counter");
            setActivityBackgroundColor(colourCounter.get(counter));

        } else {
            Log.d(LOG_TAG_UI, "Main activity onCreate savedInstanceState is null.");
//            setActivityBackgroundColor(colourCounter.get(0));
        }
//        mTextViewCountDown = findViewById(R.id.tvTimer);
//        mButtonStartPause = findViewById(R.id.btTimer);

//        countPlayer();
//        try {
//            readLines(rules);
//            readLines(Trial);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        createNonSequence();
        if (rulesAdd.contains(counter)) {
            rule.setVisibility(View.VISIBLE);
        } else {
            rule.setVisibility(View.INVISIBLE);
        }
        setActivityBackgroundColor(colourCounter.get(counter));
//        setActivityBackgroundColor(colourCounter.get(0));
        gamePageTV.setText(numbers.get(counter));

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                counter = counter + 1;

                if (counter >= gameSize) {
                    finishGame();
                } else {
                    if (rulesAdd.contains(counter)) {
                        rule.setVisibility(View.VISIBLE);
                    } else {
                        rule.setVisibility(View.INVISIBLE);
                    }
                    gamePageTV.setText(numbers.get(counter));
                    setActivityBackgroundColor(colourCounter.get(counter));
                }
                return false;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
//                setActivityBackgroundColor(colourCounter.get(counter));
            }
        });
//        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!mTimerRunning){
//                    startTimer();
//                }else{
//                    pauseTimer();
//                }
//            }
//        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putStringArrayList("games", game);
//        outState.putStringArrayList("numbers", numbers);
        outState.putIntegerArrayList("colour", colourCounter);
//        outState.putIntegerArrayList("rule", rulesAdd);
        outState.putInt("counter", counter);
        Log.d(LOG_TAG_UI, "Main activity onSaveInstanceState.");
    }
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        savedInstanceState.putStringArrayList("games", game);
//        savedInstanceState.putStringArrayList("numbers", numbers);
//        savedInstanceState.putIntegerArrayList("colour", colourCounter);
//        savedInstanceState.putIntegerArrayList("rule", rulesAdd);
//        savedInstanceState.putInt("counter", counter);
//        Log.d(LOG_TAG_UI, "Main activity onRestoreInstanceState.");
//    }

    public void setActivityBackgroundColor(int colour) {
        View view = this.getWindow().getDecorView();
        if (colour == 0) {
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (colour == 1) {
            view.setBackgroundColor(getResources().getColor(R.color.colourOne));
        } else if (colour == 2) {
            view.setBackgroundColor(getResources().getColor(R.color.colourTwo));
        } else if (colour == 3) {
            view.setBackgroundColor(getResources().getColor(R.color.colourThree));
        } else if (colour == 4) {
            view.setBackgroundColor(getResources().getColor(R.color.colourFour));
        }
    }

    public void backButton() {
        counter = counter - 1;
        if (counter < 0) {
            finish();
        } else {
            setActivityBackgroundColor(colourCounter.get(counter));
            if (rulesAdd.contains(counter)) {
                rule.setVisibility(View.VISIBLE);
            } else {
                rule.setVisibility(View.INVISIBLE);
            }
            gamePageTV.setText(numbers.get(counter));
        }
    }

    public void finishGame() {
        if (counter == gameSize) {
            Intent intent = new Intent(GamePage.this, FinishedGame.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG_UI, "Game activity onRestart.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG_UI, "Game activity onPause.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG_UI, "Game activity onStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG_UI, "Game activity onDestroy.");
    }
}

//public void createGame() {
//        for (int i = 0; i < gameSize; i++) {
//            game.add(random());
//        }
//    }

//    public String random() {
//        int min = 0;
//        int max = lines.size() - 1;
//        Random r = new Random();
//        int i1 = r.nextInt(max - min + 1) + min;
//        String value = lines.get(i1);
//        String updated = nameSwap(value);
//        return updated;
//    }

//
//    private static final long START_TIME_IN_MILLIS = 600000;
//    private CountDownTimer mCountDownTimer;
//    private boolean mTimerRunning;
//    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
//
//    private void pauseTimer() {
//        mCountDownTimer.cancel();
//        mTimerRunning = false;
//        mButtonStartPause.setText("Start");
////        mButtonStartPause.setVisibility(View.INVISIBLE);
//    }
//    private void startTimer() {
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//            @Override
//            public void onFinish() {
//                mTimerRunning = false;
////                mButtonStartPause.setText("Start");
////                mButtonStartPause.setVisibility(View.INVISIBLE);
////                mButtonReset.setVisibility(View.VISIBLE);
//            }
//        }.start();
//        mTimerRunning = true;
////        mButtonStartPause.setText("pause");
////        mButtonReset.setVisibility(View.INVISIBLE);
//    }
//
//    private void updateCountDownText() {
//        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//        mTextViewCountDown.setText(timeLeftFormatted);
//    }

//    public void createNonSequence() {
//        int min = 0;
//        int max = lines.size() - 1;
//        int ruleMin = 0;
//        int ruleMax = gameSize;
//        Random r = new Random();
//        String updated;
//        String value;
//        boolean rule;
//        for (int j = 0; j < 5; j++) {
//            rulesAdd.add(r.nextInt(ruleMax - ruleMin + 1) + ruleMin);
//        }
//
//        for (int i = 0; i < gameSize; i++) {
//
//            int i1 = r.nextInt(max - min + 1) + min;
//            int i2 = r.nextInt(RuleBook.size() - min) + min;
//            if (rulesAdd.contains(i)) {
//                value = RuleBook.get(i2);
//                rule = true;
//            } else {
//                value = lines.get(i1);
//                rule = false;
//            }
//            if (players.size() > 2 && value.contains("X") && value.contains("Y") && value.contains("Z")) {
//                updated = nameSwap(value, 3);
//                colour = 4;
//            } else if (value.contains("X") && value.contains("Y")) {
//                updated = nameSwap(value, 2);
//                colour = 3;
//            } else if (value.contains("X")) {
//                updated = nameSwap(value, 1);
//                colour = 2;
//            } else {
//                updated = value;
//                colour = 1;
//            }
//
//            if (!game.contains(value)) {
//                numbers.add(updated);
//                game.add(value);
//                if (rule) {
//                    colour = 0;
//                }
//                colourCounter.add(colour);
//            } else {
//                i = i - 1;
//            }
//        }
//    }

//    public void countPlayer() {
//        if (players.size() <= 1) {
//            try {
//                readLines(onePlayer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (players.size() >= 2) {
//            try {
//                readLines(onePlayer);
//                readLines(twoPlayer);
//                readLines(rules);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (players.size() >= 3) {
//            try {
//                readLines(onePlayer);
//                readLines(twoPlayer);
//                readLines(threePlayer);
//                readLines(rules);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (players.size() >= 4) {
//            try {
//                readLines(onePlayer);
//                readLines(twoPlayer);
//                readLines(threePlayer);
//                readLines(fourPlayer);
//                colour = 3;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public List<Integer> randomPlayer() {
//        int min = 0;
//        int max = players.size() - 1;
//        Random r = new Random();
//        List<Integer> randP = new ArrayList<>();
//
//        for (int i = 0; i < players.size(); i++) {
//            int i1 = r.nextInt(max - min + 1) + min;
//            if (!randP.contains(i1)) {
//                randP.add(i1);
//            } else {
//                i = i - 1;
//            }
//        }
//        return randP;
//    }
//
//    public String nameSwap(String line, int number) {
//        Scanner s = new Scanner(line);
//        String next = s.nextLine();
//        String passX;
//        String passXY;
//        String passXYZ;
//        int first = 0;
//        int second = 0;
//        int third = 0;
//
//        if (players.size() >= 3) {
//            List<Integer> pass = randomPlayer();
//            first = pass.get(0);
//            second = pass.get(1);
//            third = pass.get(2);
//        }
//        if (players.size() == 2) {
//            List<Integer> pass = randomPlayer();
//            first = pass.get(0);
//            second = pass.get(1);
//        }
//        if (players.size() == 1) {
//            List<Integer> pass = randomPlayer();
//            first = pass.get(0);
//        }
//
//        if (next.contains("X") && next.contains("Y") && next.contains("Z") && number > 2) {
//            passX = next.replace("X", players.get(first));
//            passXY = passX.replace("Y", players.get(second));
//            passXYZ = passXY.replace("Z", players.get(third));
//            return passXYZ;
//        }
//        if (next.contains("X") && next.contains("Y") && number == 2) {
//            passX = next.replace("X", players.get(first));
//            passXY = passX.replace("Y", players.get(second));
//            return passXY;
//        }
//        if (next.contains("X") && number == 1) {
//            passX = next.replace("X", players.get(third));
//            return passX;
//        }
//        s.close();
//        return next;
//    }
//
//    public List<String> readLines(String filename) throws IOException {
//
//        AssetManager assets = this.getAssets();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(assets.open(filename)));
//        while (true) {
//            String line = reader.readLine();
//            if (line == null) {
//                break;
//            }
//            if (filename.equals("Rules")) {
//                RuleBook.add(line);
//            } else {
//                lines.add(line);
//            }
//        }
//        return lines;
//    }
