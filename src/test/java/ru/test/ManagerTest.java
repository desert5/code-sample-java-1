package ru.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.test.score.ScoreManager;
import ru.test.score.ScoreManagerImpl;
import ru.test.util.DummyScoreDAO;

/**
 * Created by Desert on 21.02.2016.
 */
@Test
public class ManagerTest extends Assert
{
    public void ScoreManagerTest()
    {
        try
        {
            ScoreManager manager = new ScoreManagerImpl(new DummyScoreDAO());
            manager.setScore("player2", 5);
            manager.setScore("player1", 10);

            assertEquals(manager.getScore("player1"), new Integer(10));
            assertEquals(manager.getRanktable().size(), 2);
            assertEquals(manager.getRanktable().get(0).getName(), "player2");
        }
        catch (Throwable e)
        {
            fail(e.getMessage());
        }
    }
}
