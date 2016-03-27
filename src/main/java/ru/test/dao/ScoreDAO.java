package ru.test.dao;

import java.util.Map;

/**
 * Interface to access and save score data using method
 * determined by implementation
 */
public interface ScoreDAO
{
    /**
     * Saves core data
     *
     * @param scores score data structure
     * @throws Exception
     */
    void save(Map<String, Integer> scores) throws Exception;

    /**
     * Recovers score data from underlying source
     *
     * @return score data structure
     * @throws Exception
     */
    Map<String, Integer> load() throws Exception;
}
