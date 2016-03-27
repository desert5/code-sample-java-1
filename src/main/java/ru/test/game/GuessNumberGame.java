package ru.test.game;

import ru.test.score.ScoreManager;
import ru.test.score.ScoreTableRenderer;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Implementation of a Game interface, implementing game where user guesses the number,
 * that was calculated by program in certain range.
 */
public class GuessNumberGame implements Game
{
    /**
     * Constructor to create instance of the game
     *
     * @param guessFrom lower limit in guessing range (inclusive)
     * @param guessTo   upper limit in guessing range (inclusive)
     * @param inputStream stream to receive input data from user
     * @throws Exception
     */
    public GuessNumberGame(Integer guessFrom, Integer guessTo, InputStream inputStream) throws Exception
    {
        this.sc = new Scanner(inputStream);
        this.secretNumber = getRandom(guessFrom, guessTo);
        this.guessFrom = guessFrom;
        this.guessTo = guessTo;
        this.tryAmount = 0;
        this.started = false;
        this.finished = false;
    }

    /**
     * Constructor for executing tests with predefined secret number
     *
     * @param guessFrom lower limit in guessing range (inclusive)
     * @param guessTo upper limit in guessing range (inclusive)
     * @param inputStream stream to receive input data from user
     * @param secret predefined secret number
     * @throws Exception
     */
    GuessNumberGame(Integer guessFrom, Integer guessTo, InputStream inputStream, Integer secret) throws Exception
    {
        this(guessFrom, guessTo, inputStream);
        secretNumber = secret;
    }

    /**
     * Function to announce the start of a game and switch it to started state
     */
    @Override
    public void start()
    {
        announce("Please guess the secret number from " + guessFrom + " to " + guessTo);
        started = true;
    }

    /**
     * This implementation reads the user input and compares it with calculated secret value.
     * After that user is informed whether the number he inputted
     * is less, equal or greater than the secret. If the user inputs incorrect data he gets informed
     * about incorrectness and showed the value read by program from the input
     */
    @Override
    public void update()
    {
        if (!finished)
        {
            try
            {
                // Reading number from the stream
                int input = sc.nextInt();
                // Skipping the rest
                sc.nextLine();

                ++tryAmount;

                if (input == secretNumber)
                {
                    announce("You are correct!");
                    finished = true;
                }
                else if (input > secretNumber)
                {
                    announce("The input is greater than secret");
                }
                else if (input < secretNumber)
                {
                    announce("The input is lesser than secret");
                }
            }
            catch (InputMismatchException e)
            {
                announce("Wrong input: " + sc.nextLine());
            }
        }
    }

    /**
     * Executes all actions related to the game finish:
     * 1) announces how much steps user has spent trying to guess a secret
     * 2) requests user's name
     * 3) saves user score
     * 4) renders the score table
     * @throws Exception
     */
    @Override
    public void finish() throws Exception
    {
        announce("You have guessed the secret with " + tryAmount + " steps");
        announce("Please enter your name:");
        String name = sc.nextLine();
        scoreManager.setScore(name, tryAmount);
        tableRenderer.render(scoreManager.getRanktable(), System.out);
        scoreManager.persist();
    }

    @Override
    public boolean isStarted()
    {
        return started;
    }

    @Override
    public boolean isFinished()
    {
        return finished;
    }

    /**
     * Injects implementation of {@link ScoreManager}
     * @param scoreManager dependency instance to be injected
     */
    public void setScoreManager(ScoreManager scoreManager)
    {
        this.scoreManager = scoreManager;
    }

    /**
     * Injects implementation of {@link ScoreTableRenderer}
     * @param tableRenderer dependency instance to be injected
     */
    public void setTableRenderer(ScoreTableRenderer tableRenderer)
    {
        this.tableRenderer = tableRenderer;
    }

    /**
     * Sends message to user
     * @param message message
     */
    private void announce(String message)
    {
        System.out.println(message);
    }

    private int getRandom(int min, int max)
    {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

    private Integer secretNumber;
    private Integer tryAmount;
    private Scanner sc;
    private ScoreManager scoreManager;
    private ScoreTableRenderer tableRenderer;
    private boolean started;
    private boolean finished;
    private Integer guessFrom;
    private Integer guessTo;
}
