package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean P1Turn = true;

    private int Counter = 0;
    private int P1Points = 0;
    private int P2Points = 0;

    private TextView tvP1;
    private TextView tvP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvP1 = findViewById(R.id.text_view_p1);
        tvP2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; i++) {
                String BtnId = "Button_" + i + j;
                int RID = getResources().getIdentifier(BtnId, "id", getPackageName());
                buttons[i][j] = findViewById(RID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void onClick(View view) {

        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (P1Turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        Counter++;

        if (Winner()) {
            if (P1Turn) {
                P1Win();
            } else {
                P2Win();
            }
        } else if (Counter == 9) {
            Draw();
        } else {
            P1Turn = !P1Turn;
        }
    }

    private boolean Winner() {
        String[][] Field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; i < Field[i].length; i++) {
                Field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (Field[i][0].equals(Field[i][1])
                    && Field[i][0].equals(Field[i][2])
                    && !Field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (Field[0][i].equals(Field[1][i])
                    && Field[0][i].equals(Field[2][i])
                    && !Field[0][i].equals("")) {
                return true;
            }
        }
        if (Field[0][0].equals(Field[1][1])
                && Field[0][0].equals(Field[2][2])
                && !Field[0][0].equals("")) {
            return true;
        }
        if (Field[0][2].equals(Field[1][1])
                && Field[0][2].equals(Field[2][0])
                && !Field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    public void P1Win() {
        P1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        IncPoints();
        Reset();

    }

    public void P2Win() {
        P2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        IncPoints();
        Reset();
    }

    public void Draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        Reset();

    }

    @SuppressLint("SetTextI18n")
    public void IncPoints() {
        tvP1.setText("Player 1: " + P1Points);
        tvP1.setText("Player 2: " + P2Points);

    }

    public void Reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }
        Counter = 0;
        P1Turn = true;
    }
}