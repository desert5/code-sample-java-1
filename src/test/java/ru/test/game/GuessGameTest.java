package ru.test.game;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.test.score.ScoreManager;
import ru.test.score.ScoreManagerImpl;
import ru.test.score.SimpleTableRenderer;
import ru.test.util.DummyScoreDAO;

import java.io.ByteArrayInputStream;

/**
 * Created by Desert on 22.02.2016.
 */
@Test
public class GuessGameTest extends Assert
{
    public void game()
    {
        try
        {
            ByteArrayInputStream input = new ByteArrayInputStream("1\nplayer".getBytes());
            GuessNumberGame game = new GuessNumberGame(0, 2, input, 1);
            ScoreManager scoreManager = new ScoreManagerImpl(new DummyScoreDAO());
            game.setScoreManager(scoreManager);
            game.setTableRenderer(new SimpleTableRenderer());

            game.start();

            assertTrue(game.isStarted());

            game.update();

            assertTrue(game.isFinished());

            game.finish();

            assertEquals(scoreManager.getScore("player"), new Integer(1));
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
