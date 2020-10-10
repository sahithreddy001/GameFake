package com.example.sahith.gamefake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;
    private boolean player2 = true;
    private Button player2Turn;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private boolean k = true;
    private boolean d = true;
    private boolean g = true;
    private int m;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        Button player2Turn = (Button) findViewById(R.id.player2Turn);
        player2Turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player2){
                    secondTurn();
                    roundCount++;
                    if (checkForWin()) {
                        if (player1Turn) {
                            player1Wins();
                        } else {
                            player2Wins();
                        }
                    } else if (roundCount == 9) {
                        draw();
                    } else {
                        g=true;
                        d=true;
                    }

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
            player1Turn = false;
            player2 = true;
            roundCount++;
        }
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            g=true;
            d=true;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        k=true;
        d=true;
        g=true;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        k=true;
        d=true;
        g=true;
        Toast.makeText(this, "Humans can't win computers mate!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        k=true;
        d=true;
        g=true;
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
        player2 = true;
        k=true;
        d=true;
        g=true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
        player1Turn = true;
        player2 = true;
        k=true;
        d=true;
        g=true;
    }
    private void secondTurn(){

            player2 = false;
            player1Turn = true;
            String[][] field2 = new String[3][3];
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    field2[i][j] = buttons[i][j].getText().toString();
                }
            }
            if (k) {
                if (field2[1][1].equals("")) {
                    buttons[1][1].setText("O");
                    k=false;
                } else {
                    buttons[2][0].setText("O");
                    k=false;
                }
            }
            else {
                String[] r = new String[3];
                String[] c = new String[3];
                String[] c1 = new String[3];
                String[] c2 = new String[3];
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        c[1 + j] = field2[1 + i][1 + j];
                        r[1 + j] = field2[1 + j][1 + i];
                    }
                    if (r[0].equals(r[1]) && !r[0].equals("") && r[2].equals("") && r[0].equals("O") && g) {
                        buttons[2][1 + i].setText("O");
                        g = false;
                    } else if (r[1].equals(r[2]) && !r[1].equals("") && r[1].equals("O") && r[0].equals("") && g) {
                        buttons[0][1 + i].setText("O");
                        g = false;
                    } else if (r[0].equals(r[2]) && !r[2].equals("") && r[2].equals("O") && r[1].equals("") && g) {
                        buttons[1][1 + i].setText("O");
                        g = false;
                    }
                    if (c[0].equals(c[1]) && !c[0].equals("") && c[0].equals("O") && c[2].equals("") && g) {
                        buttons[1 + i][2].setText("O");
                        g = false;
                    } else if (c[1].equals(c[2]) && !c[1].equals("") && c[1].equals("O") && c[0].equals("") && g) {
                        buttons[1 + i][0].setText("O");
                        g = false;
                    } else if (c[0].equals(c[2]) && !c[2].equals("") && c[2].equals("O") && c[1].equals("") && g) {
                        buttons[1 + i][1].setText("O");
                        g = false;
                    }
                }
                for (int i = -1; i < 2; i++) {
                    c1[i + 1] = field2[1 + i][1 - i];
                    c2[1 + i] = field2[1 + i][1 + i];
                }
                if (c1[0].equals(c1[1]) && !c1[0].equals("") && c1[0].equals("O") && c1[2].equals("") && g) {
                    buttons[2][0].setText("O");
                    g = false;
                } else if (c1[1].equals(c1[2]) && !c1[1].equals("") && c1[1].equals("O") && c1[0].equals("") && g) {
                    buttons[0][2].setText("O");
                    g = false;
                } else if (c1[0].equals(c1[2]) && !c1[0].equals("") && c1[0].equals("O") && c1[1].equals("") && g) {
                    buttons[1][1].setText("O");
                    g = false;
                }
                if (c2[0].equals(c2[1]) && !c2[0].equals("") && c2[0].equals("O") && c2[2].equals("") && g) {
                    buttons[2][2].setText("O");
                    g = false;
                } else if (c2[0].equals(c2[2]) && !c2[0].equals("") && c2[0].equals("O") && c2[1].equals("") && g) {
                    buttons[1][1].setText("O");
                    g = false;
                } else if (c2[1].equals(c2[2]) && !c2[1].equals("") && c2[1].equals("O") && c2[0].equals("") && g) {
                    buttons[0][0].setText("O");
                    g = false;
                }
                if (g) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            c[1 + j] = field2[1 + i][1 + j];
                            r[1 + j] = field2[1 + j][1 + i];
                        }
                        if (c[0].equals(c[1]) && !c[0].equals("") && c[2].equals("") && c[0].equals("X") && g) {
                            buttons[1 + i][2].setText("O");
                            g = false;
                        } else if (c[1].equals(c[2]) && !c[1].equals("") && c[0].equals("") && c[1].equals("X") && g) {
                            buttons[1 + i][0].setText("O");
                            g = false;
                        } else if (c[0].equals(c[2]) && !c[0].equals("") && c[1].equals("") && c[0].equals("X") && g) {
                            buttons[1 + i][1].setText("O");
                            g = false;
                        }
                        if (r[0].equals(r[1]) && !r[0].equals("") && r[2].equals("") && r[0].equals("X") && g) {
                            buttons[2][1 + i].setText("O");
                            g = false;
                        } else if (r[1].equals(r[2]) && !r[1].equals("") && r[0].equals("") && r[1].equals("X") && g) {
                            buttons[0][1 + i].setText("O");
                            g = false;
                        } else if (r[0].equals(r[2]) && !r[0].equals("") && r[0].equals("X") && r[1].equals("") && g) {
                            buttons[1][1 + i].setText("O");
                            g = false;
                        }
                    }
                    for (int i = -1; i < 2; i++) {
                        c1[i + 1] = field2[1 + i][1 - i];
                        c2[1 + i] = field2[1 + i][1 + i];
                    }
                    if (c1[0].equals(c1[1]) && !c1[0].equals("") && c1[0].equals("X") && c1[2].equals("") && g) {
                        buttons[2][0].setText("O");
                        g = false;
                    } else if (c1[1].equals(c1[2]) && !c1[1].equals("") && c1[1].equals("X") && c1[0].equals("") && g) {
                        buttons[0][2].setText("O");
                        g = false;
                    } else if (c1[0].equals(c1[2]) && !c1[0].equals("") && c1[0].equals("X") && c1[1].equals("") && g) {
                        buttons[1][1].setText("O");
                        g = false;
                    }
                    if (c2[0].equals(c2[1]) && !c2[0].equals("") && c2[0].equals("X") && c2[2].equals("") && g) {
                        buttons[2][2].setText("O");
                        g = false;
                    } else if (c2[0].equals(c2[2]) && !c2[0].equals("") && c2[0].equals("X") && c2[1].equals("") && g) {
                        buttons[1][1].setText("O");
                        g = false;
                    } else if (c2[1].equals(c2[2]) && !c2[1].equals("") && c2[1].equals("X") && c2[0].equals("") && g) {
                        buttons[0][0].setText("O");
                        g = false;
                    }
                    if (g) {
                        if (field2[0][1].equals(field2[1][0]) && field2[0][0].equals("") && !field2[0][1].equals("")) {
                            buttons[0][0].setText("O");
                            g = false;
                        } else if (field2[1][2].equals(field2[2][1]) && field2[2][2].equals("") && !field2[1][2].equals("")) {
                            buttons[2][2].setText("O");
                            g = false;
                        } else if (field2[1][0].equals(field2[2][1]) && field2[2][0].equals("") && !field2[1][0].equals("")) {
                            buttons[2][0].setText("O");
                            g = false;
                        } else if (field2[0][1].equals(field2[1][2]) && field2[0][2].equals("") && !field2[0][1].equals("")) {
                            buttons[0][2].setText("O");
                            g = false;
                        }
                    }
                    if (field2[0][2].equals(field2[1][1]) && field2[2][0].equals("O") && field2[2][2].equals("")) {
                        buttons[2][2].setText("O");
                        g = false;
                    }
                    if (field2[0][0].equals(field2[1][1]) && field2[2][2].equals("O") && field2[2][0].equals("")) {
                        buttons[2][0].setText("O");
                        g = false;
                    }
                    if (g) {
                        d = true;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (field2[i][j].equals("O") && g) {
                                    m = i;
                                    n = j;
                                    int t = 1;
                                    if ((n + t) <= 2 && (n - t) >= 0 && field2[m][n - t].equals("") && field2[m][n + t].equals("")) {
                                        buttons[m][n + t].setText("O");
                                        g = false;
                                    } else if ((m + t) <= 2 && (m - t) >= 0 && field2[m - t][n].equals("") && field2[m + t][n].equals("")) {
                                        buttons[m + t][n].setText("O");
                                        g = false;
                                    } else if (m == 1 && n == 1 && field2[0][2].equals("") && field2[2][0].equals("")) {
                                        buttons[0][2].setText("O");
                                        g = false;
                                    } else if (m == 1 && n == 1 && field2[0][0].equals("") && field2[2][2].equals("")) {
                                        buttons[0][0].setText("O");
                                        g = false;
                                    } else if ((n + t) <= 2 && field2[m][n + t].equals("")) {
                                        buttons[m][n + t].setText("O");
                                        g = false;
                                    } else if ((n - t) >= 0 && field2[m][n - t].equals("")) {
                                        buttons[m][n - t].setText("O");
                                        g = false;
                                    } else if ((m + t) <= 2 && field2[m + t][n].equals("")) {
                                        buttons[m + t][n].setText("O");
                                        g = false;
                                    } else if ((m - t) >= 0 && field2[m - t][n].equals("")) {
                                        buttons[m - t][n].setText("O");
                                        g = false;
                                    }


                                }
                            }
                        }
                    }
                }
            }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}