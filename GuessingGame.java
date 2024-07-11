import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GuessingGame extends JFrame {
    private int randomNumber;
    private int attempts;
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JButton guessButton;
    private JButton resetButton;
    public GuessingGame() {
        setTitle("Guessing Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        setLocationRelativeTo(null);

        resetGame();

        JLabel promptLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField();
        feedbackLabel = new JLabel("Enter your guess above.");
        attemptsLabel = new JLabel("Attempts: 0");

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonListener());
        add(promptLabel);
        add(guessField);
        add(feedbackLabel);
        add(attemptsLabel);
        add(guessButton);
        add(resetButton);
        setVisible(true);
    }

    private void resetGame() {
        randomNumber = (int) (Math.random() * 100) + 1;
        attempts = 0;
        if (attemptsLabel != null) {
            attemptsLabel.setText("Attempts: 0");
        }
        if (feedbackLabel != null) {
            feedbackLabel.setText("Enter your guess above.");
        }
        if (guessField != null) {
            guessField.setText("");
        }
    }
    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess < randomNumber) {
                    feedbackLabel.setText("Too low. Try again.");
                } else if (guess > randomNumber) {
                    feedbackLabel.setText("Too high. Try again.");
                } else {
                    feedbackLabel.setText("Correct! The number was " + randomNumber);
                    JOptionPane.showMessageDialog(null, "You guessed the number in " + attempts + " attempts.");
                    resetGame();
                }
                attemptsLabel.setText("Attempts: " + attempts);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }
    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetGame();
        }
    }
    public static void main(String[] args) {
        new GuessingGame();
    }
}