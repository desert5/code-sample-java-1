package ru.test.util;

import ru.test.dao.ScoreDAO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Desert on 21.02.2016.
 */
public class DummyScoreDAO implements ScoreDAO
{
    @Override
    public void save(Map<String, Integer> scores) throws Exception
    {

    }

    @Override
    public Map<String, Integer> load() throws Exception
    {
        return new HashMap<>();
    }
}
