package com.example.x_o;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
EditText player1Edit;
EditText player2Edit;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        player1Edit = findViewById(R.id.player1NameEditText);
        player2Edit = findViewById(R.id.player2NameEditText);
        button = findViewById(R.id.startGame);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void startGame() {
        String player1Name = player1Edit.getText().toString();
        String player2Name = player2Edit.getText().toString();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(Constant.EXTRA_PLAYER1_NAME,player1Name);
        intent.putExtra(Constant.EXTRA_PLAYER2_NAME,player2Name);
        startActivity(intent);
    }
}