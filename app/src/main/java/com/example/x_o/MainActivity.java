package com.example.x_o;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout rootview;

    TextView player1NameTextView;
    TextView player2NameTextView;
    TextView player1ScoreTextView;
    TextView player2ScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootview = findViewById(R.id.root_view);
        player1NameTextView = findViewById(R.id.player1_lb);
        player2NameTextView = findViewById(R.id.player2_lb);

        player1ScoreTextView = findViewById(R.id.player1_score);
        player2ScoreTextView = findViewById(R.id.player2_score);

        String player1 = getIntent().getStringExtra(Constant.EXTRA_PLAYER1_NAME);
        String player2 = getIntent().getStringExtra(Constant.EXTRA_PLAYER2_NAME);
        player1NameTextView.setText(player1 + " (X)");
        player2NameTextView.setText(player2 + " (O)");
    }

    String[] boardState = {"", "", "", "", "", "", "", "", ""};
    int counter = 0;
    int player1Score = 0;
    int player2Score = 0;

    public void onPlayerClick(View view) {
        Button clickedButton = (Button) view;
        if (!clickedButton.getText().toString().isEmpty()) {
            return;
        }
        if (counter % 2 == 0) {
            clickedButton.setText("X");
            changeBoardState(clickedButton, "X");
        } else {
            clickedButton.setText("O");
            changeBoardState(clickedButton, "O");

        }
        counter++;
        if (checkWinner("X")) {
            player1Score += 1;
            player1ScoreTextView.setText(String.valueOf(player1Score));
            resetBoard();
        } else if (checkWinner("O")) {
            player2Score += 1;
            player2ScoreTextView.setText(String.valueOf(player2Score));
            resetBoard();
        } else if (counter == 9) {
            resetBoard();
        }

    }

    void resetBoard() {
        counter = 0;
        boardState = new String[]{"", "", "", "", "", "", "", "", ""};
        clearButtons();
    }

    private void clearButtons() {
        /*
        for (int i = 0; i < 9; i++) {
            View view = rootview.findViewWithTag(i + "");
            Button b = (Button) view;
            b.setText(null);

}
         */

        for (int i = 0; i < rootview.getChildCount(); i++) {
            View view = rootview.getChildAt(i);
            if (view instanceof LinearLayout) {
                LinearLayout linear = (LinearLayout) view;
                for (int j = 0; j < linear.getChildCount(); j++) {
                    if (linear.getChildAt(j) instanceof Button) {
                        Button button = (Button) linear.getChildAt(j);
                        button.setText("");
                    }
                }
            }
        }
    }

    boolean checkWinner(String playerSymbol) {
        for (int i = 0; i < 9; i += 3) {
            if (boardState[i].equals(playerSymbol) &&
                    boardState[i + 1].equals(playerSymbol) &&
                    boardState[i + 2].equals(playerSymbol)) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (boardState[i].equals(playerSymbol) &&
                    boardState[i + 3].equals(playerSymbol) &&
                    boardState[i + 6].equals(playerSymbol)) {
                return true;
            }
        }
        if (boardState[0].equals(playerSymbol) &&
                boardState[4].equals(playerSymbol) &&
                boardState[8].equals(playerSymbol)) {
            return true;
        }

        if (boardState[2].equals(playerSymbol) &&
                boardState[4].equals(playerSymbol) &&
                boardState[6].equals(playerSymbol)) {
            return true;
        }
        return false;
    }

    private int getClickedButtonIndex(Button clickedButton) {
        String tag = ((String) clickedButton.getTag());
        return Integer.parseInt(tag);
        /*
        int index =0;
        if(clickedButton.getId()==R.id.btn_1){
            index=0;
        }
        if(clickedButton.getId()==R.id.btn_2){
            index=1;
        }if(clickedButton.getId()==R.id.btn_3){
            index=2;
        }
        if(clickedButton.getId()==R.id.btn_4){
            index=3;
        }
        if(clickedButton.getId()==R.id.btn_5){
            index=4;
        }
        if(clickedButton.getId()==R.id.btn_6){
            index=5;
        }
        if(clickedButton.getId()==R.id.btn_7){
            index=6;
        }
        if(clickedButton.getId()==R.id.btn_8){
            index=7;
        }
        if(clickedButton.getId()==R.id.btn_9){
            index=8;
        }
        return index;
         */

    }

    private void changeBoardState(Button clickedButton, String playerSymbol) {
        int index = getClickedButtonIndex(clickedButton);
        boardState[index] = playerSymbol;
    }
}