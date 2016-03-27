package ru.test.score;

import org.apache.log4j.Logger;
import ru.test.util.Result;
import ru.test.dao.ScoreDAO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Score manager implementation using {@link Map} to store score information
 */
public class ScoreManagerImpl implements ScoreManager
{
    /**
     * Constructor that builds an instance and loads score data through score DAO,
     * supplied through dependency injection.
     * @param scoreDAO dependency instance of {@link ScoreDAO}
     * @throws Exception
     */
    public ScoreManagerImpl(ScoreDAO scoreDAO) throws Exception
    {
        this.scoreDAO = scoreDAO;
        scores = scoreDAO.load();
    }

    @Override
    public void setScore(String player, Integer score)
    {
        scores.put(player, score);
    }

    @Override
    public Integer getScore(String player)
    {
        return scores.get(player);
    }

    @Override
    public List<Result> getRanktable()
    {
        if (scores.size() > 0)
        {
            List<Result> list = scores.entrySet().stream().map(entry ->
                    new Result(entry.getKey(), entry.getValue())).collect(Collectors.toList());

            // Minus one reverses the sort order
            Collections.sort(list, (Result o1, Result o2) -> o1.getScore().compareTo(o2.getScore()));

            return list;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void persist() throws Exception
    {
        scoreDAO.save(scores);
    }

    private ScoreDAO scoreDAO;
    private Map<String, Integer> scores;
    private final Logger logger = Logger.getLogger(getClass());
}
