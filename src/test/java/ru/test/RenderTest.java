package ru.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.test.score.SimpleTableRenderer;
import ru.test.util.Result;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Desert on 21.02.2016.
 */
@Test
public class RenderTest extends Assert
{
    public void SimpleRendererTest()
    {
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            List<Result> list = new ArrayList<>();
            Collections.addAll(list,
                    new Result("awd", 2),
                    new Result("itt", 3)
            );

            new SimpleTableRenderer().render(list, out);

            String renderedText = out.toString(StandardCharsets.UTF_8.name());

            assertTrue(renderedText.contains("awd"));
            assertTrue(renderedText.contains("itt"));
            assertTrue(renderedText.contains("2"));
            assertTrue(renderedText.contains("3"));
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}
