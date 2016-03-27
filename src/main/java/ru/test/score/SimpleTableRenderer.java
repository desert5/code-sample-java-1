package ru.test.score;

import ru.test.util.Result;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Implementation of {@link ScoreTableRenderer} interface,
 * that renders table as a simple text in a markdown-like style
 */
public class SimpleTableRenderer implements ScoreTableRenderer
{
    public void render(List<Result> scoreTable, OutputStream out)
    {
        PrintWriter writer = new PrintWriter(out);
        writer.println("-------------");
        writer.println("R E S U L T S");
        writer.println("-------------");
        for (Result entry : scoreTable)
        {
            writer.println(entry.getName() + "  |  " + entry.getScore());
        }
        writer.println("-------------");
        writer.flush();
    }
}
