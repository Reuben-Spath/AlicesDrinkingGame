package com.timesheet.drinkinggame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PlayerPage extends AppCompatActivity {

    public ArrayList<String> list = new ArrayList<>();
    ArrayList<String> lines = new ArrayList<>();
    ArrayList<String> players = new ArrayList<>();
    ArrayList<String> game = new ArrayList<>();
    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<String> RuleBook = new ArrayList<>();
    ArrayList<Integer> colourCounter = new ArrayList<>();
    ArrayList<Integer> rulesAdd = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button add;
    private EditText player;
    private String playerName;
    private Button toGamePage;
    private int gameSize = 30;
    private int colour = 0;
    private String rules = "Rules";
    private String AliceBirthday = "AliceBirthday";
    private String Trial = "Trial";
    private List<example> exampleList = new ArrayList<>();
    private boolean Alice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_player_page);
        recyclerView = findViewById(R.id.RecyclerView);
        player = findViewById(R.id.player);
        add = findViewById(R.id.add);
        toGamePage = findViewById(R.id.toGamePage);
        Alice = getIntent().getBooleanExtra("Alice", Alice);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.isNestedScrollingEnabled();
        final Adapter adapter = new Adapter(exampleList);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName = player.getText().toString().trim();
                if (playerName.equals("")) {
                    Toast.makeText(PlayerPage.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    player.getText().clear();
                    exampleList.add(new example(R.drawable.ic_delete, playerName));
                    adapter.notifyDataSetChanged();
                }
            }
        });

        toGamePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (exampleList.size() > 0) {
                    list.clear();
                    for (int i = 0; i < exampleList.size(); i++) {
                        players.add(exampleList.get(i).getText1());
                    }
//                    players.addAll(list);
                    if (Alice) {
                        try {
                            readLines(rules);
                            readLines(AliceBirthday);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            readLines(rules);
                            readLines(Trial);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    createNonSequence();

                    Intent intent = new Intent(PlayerPage.this, GamePage.class);
                    intent.putExtra("colourCounter", colourCounter);
                    intent.putExtra("game", game);
                    intent.putExtra("numbers", numbers);
                    intent.putExtra("rulesAdd", rulesAdd);


//                    intent.putExtra("arraylist", list);
                    startActivity(intent);
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    public void createNonSequence() {
        int min = 0;
        int max = lines.size() - 1;
        int ruleMin = 0;
        int ruleMax = gameSize;
        Random r = new Random();
        String updated;
        String value;
        boolean rule;
        for (int j = 0; j < 5; j++) {
            rulesAdd.add(r.nextInt(ruleMax - ruleMin + 1) + ruleMin);
        }

        for (int i = 0; i < gameSize; i++) {

            int i1 = r.nextInt(max - min + 1) + min;
            int i2 = r.nextInt(RuleBook.size() - min) + min;
            if (rulesAdd.contains(i)) {
                value = RuleBook.get(i2);
                rule = true;
            } else {
                value = lines.get(i1);
                rule = false;
            }
            if (players.size() > 2 && value.contains("X") && value.contains("Y") && value.contains("Z")) {
                updated = nameSwap(value, 3);
                colour = 4;
            } else if (value.contains("X") && value.contains("Y")) {
                updated = nameSwap(value, 2);
                colour = 3;
            } else if (value.contains("X")) {
                updated = nameSwap(value, 1);
                colour = 2;
            } else {
                updated = value;
                colour = 1;
            }

            if (!game.contains(value)) {
                numbers.add(updated);
                game.add(value);
                if (rule) {
                    colour = 0;
                }
                colourCounter.add(colour);
            } else {
                i = i - 1;
            }
        }
    }

    public List<Integer> randomPlayer() {
        int min = 0;
        int max = players.size() - 1;
        Random r = new Random();
        List<Integer> randP = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            int i1 = r.nextInt(max - min + 1) + min;
            if (!randP.contains(i1)) {
                randP.add(i1);
            } else {
                i = i - 1;
            }
        }
        return randP;
    }

    public String nameSwap(String line, int number) {
        Scanner s = new Scanner(line);
        String next = s.nextLine();
        String passX;
        String passXY;
        String passXYZ;
        int first = 0;
        int second = 0;
        int third = 0;

        if (players.size() >= 3) {
            List<Integer> pass = randomPlayer();
            first = pass.get(0);
            second = pass.get(1);
            third = pass.get(2);
        }
        if (players.size() == 2) {
            List<Integer> pass = randomPlayer();
            first = pass.get(0);
            second = pass.get(1);
        }
        if (players.size() == 1) {
            List<Integer> pass = randomPlayer();
            first = pass.get(0);
        }

        if (next.contains("X") && next.contains("Y") && next.contains("Z") && number > 2) {
            passX = next.replace("X", players.get(first));
            passXY = passX.replace("Y", players.get(second));
            passXYZ = passXY.replace("Z", players.get(third));
            return passXYZ;
        }
        if (next.contains("X") && next.contains("Y") && number == 2) {
            passX = next.replace("X", players.get(first));
            passXY = passX.replace("Y", players.get(second));
            return passXY;
        }
        if (next.contains("X") && number == 1) {
            passX = next.replace("X", players.get(third));
            return passX;
        }
        s.close();
        return next;
    }

    public List<String> readLines(String filename) throws IOException {

        AssetManager assets = this.getAssets();
        BufferedReader reader = new BufferedReader(new InputStreamReader(assets.open(filename)));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            if (filename.equals("Rules")) {
                RuleBook.add(line);
            } else {
                lines.add(line);
            }
        }
        return lines;
    }

}
/* if image from array is pressed, get array number, remove from array list
 *  set listener for button to be pressed, apply way for item to be updated
 *
 * */