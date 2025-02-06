import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView playerChoice, computerChoice;
    private TextView resultText, scoreText;
    private Button rockButton, paperButton, scissorsButton, resetButton;
    private int playerScore = 0, computerScore = 0;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoice = findViewById(R.id.player_choice);
        computerChoice = findViewById(R.id.computer_choice);
        resultText = findViewById(R.id.result_text);
        scoreText = findViewById(R.id.score_text);
        rockButton = findViewById(R.id.rock_button);
        paperButton = findViewById(R.id.paper_button);
        scissorsButton = findViewById(R.id.scissors_button);
        resetButton = findViewById(R.id.reset_button);

        random = new Random();

        rockButton.setOnClickListener(v -> playGame("rock"));
        paperButton.setOnClickListener(v -> playGame("paper"));
        scissorsButton.setOnClickListener(v -> playGame("scissors"));
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void playGame(String playerSelection) {
        String[] choices = {"rock", "paper", "scissors"};
        String computerSelection = choices[random.nextInt(3)];

        
        setImage(playerChoice, playerSelection);
        setImage(computerChoice, computerSelection);

        String result = getResult(playerSelection, computerSelection);
        resultText.setText(result);
        scoreText.setText("Player: " + playerScore + " - Computer: " + computerScore);

        if (playerScore == 10 || computerScore == 10) {
            endGame();
        }
    }

    private void setImage(ImageView imageView, String choice) {
        String imageUrl = "";

        switch (choice) {
            case "rock":
                imageUrl = "rock.jpeg"; 
                break;
            case "paper":
                imageUrl = "paper.png"; 
                break;
            case "scissors":
                imageUrl = "scissors.png"; 
                break;
        }

        // Ngarko imazhin nga URL duke përdorur Picasso
        Picasso.get().load(imageUrl).into(imageView);
    }

    private String getResult(String player, String computer) {
        if (player.equals(computer)) {
            return "It's a tie!";
        }
        if ((player.equals("rock") && computer.equals("scissors")) ||
                (player.equals("scissors") && computer.equals("paper")) ||
                (player.equals("paper") && computer.equals("rock"))) {
            playerScore++;
            return "You win this round!";
        } else {
            computerScore++;
            return "Computer wins this round!";
        }
    }

    private void endGame() {
        rockButton.setEnabled(false);
        paperButton.setEnabled(false);
        scissorsButton.setEnabled(false);
        resetButton.setVisibility(View.VISIBLE);
        resultText.setText(playerScore == 10 ? "You win the game!" : "Computer wins the game!");
    }

    private void resetGame() {
        playerScore = 0;
        computerScore = 0;
        scoreText.setText("Player: 0 - Computer: 0");
        resultText.setText("Choose Rock, Paper, or Scissors");
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorsButton.setEnabled(true);
        resetButton.setVisibility(View.GONE);
    }
}

