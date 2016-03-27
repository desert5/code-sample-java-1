package ru.test.score;

import ru.test.util.Result;

import java.util.List;

/**
 * Interface that incapsulates interaction with score system
 */
public interface ScoreManager
{
    /**
     * Sets a score for a player
     * @param player
     * @param score
     */
    void setScore(String player, Integer score);

    /**
     * Retrieves player's score
     * @param player
     * @return player's score
     */
    Integer getScore(String player);

    /**
     * Retrieves rank table, sorted in descending order (players with better results first).
     * The less steps player has spent guessing the number the higher his result is.
     * @return rank table
     */
    List<Result> getRanktable();

    /**
     * Signals the manager to store data using underlying store mechanism
     * @throws Exception
     */
    void persist() throws Exception;
}
