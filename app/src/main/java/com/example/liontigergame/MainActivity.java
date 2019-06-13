package com.example.liontigergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    enum Player {
        ONE, TWO, NOTCHOSEN
    }

    Player currentPlayer = Player.ONE;
    Player[] playerChoices = new Player[9];
    int [][] winnerRowsColumns = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    private boolean gameOver = false;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        playerChoices[0] = Player.NOTCHOSEN;
        playerChoices[1] = Player.NOTCHOSEN;
        playerChoices[2] = Player.NOTCHOSEN;
        playerChoices[3] = Player.NOTCHOSEN;
        playerChoices[4] = Player.NOTCHOSEN;
        playerChoices[5] = Player.NOTCHOSEN;
        playerChoices[6] = Player.NOTCHOSEN;
        playerChoices[7] = Player.NOTCHOSEN;
        playerChoices[8] = Player.NOTCHOSEN;
    }

    public void imageViewIsTapped(View imageView) {

        ImageView tappedImageView = (ImageView) imageView;
        int tappedImageViewTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tappedImageViewTag] == Player.NOTCHOSEN && gameOver == false) {
            tappedImageView.setTranslationX(-2000);


            playerChoices[tappedImageViewTag] = currentPlayer;

            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }

            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                        && playerChoices[winnerColumns[0]] != Player.NOTCHOSEN) {

                    gameOver = true;
                    String winnerOfGame = "";

                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Player Two ";
                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "Player One ";
                    }

                    Toast.makeText(this, winnerOfGame + "is the Winner!", Toast.LENGTH_LONG).show();


                }
            }
        } else if (gameOver == false) {
            Toast.makeText(this, "Please choose a space that is not chosen", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please reset the game", Toast.LENGTH_LONG).show();

        }

    }

}
