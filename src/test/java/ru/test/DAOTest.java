package ru.test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import ru.test.dao.SerializedObjectScoreDAO;
import ru.test.score.SimpleTableRenderer;
import ru.test.util.Result;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Test
public class DAOTest extends Assert
{
    public void SerializedDAOTest()
    {
        try
        {
            SerializedObjectScoreDAO dao = new SerializedObjectScoreDAO("test");
            Map<String, Integer> scores = new HashMap<>();
            scores.put("first", 2);
            scores.put("second", 1);

            dao.save(scores);

            Map<String, Integer> loadedScores = dao.load();

            assertNotSame(loadedScores, scores);
            assertTrue(loadedScores.get("first") == 2);
            assertTrue(loadedScores.get("second") == 1);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @AfterTest
    public void cleanUp()
    {
        assertTrue(new File(new SerializedObjectScoreDAO("test").getStoreFilename()).delete());
    }
}
