package ru.test.score;

import ru.test.util.Result;

import java.io.OutputStream;
import java.util.List;

/**
 * Interface that incapsulates rendering algorithm for rendering a score table
 */
public interface ScoreTableRenderer
{
    /**
     * Renders the table
     * @param scoreTable table to render
     * @param out stream to render the table to
     */
    void render(List<Result> scoreTable, OutputStream out);
}
