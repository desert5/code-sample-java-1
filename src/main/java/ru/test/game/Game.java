package ru.test.game;

/**
 * Interface that incapsulates game logic
 */
public interface Game
{
    /**
     * Starts the game
     */
    void start();

    /**
     * Updates game state
     */
    void update();

    /**
     * Finishes the game
     * @throws Exception
     */
    void finish() throws Exception;

    /**
     * Checks whether this game is started
     * @return true if started
     */
    boolean isStarted();

    /**
     * Checks whether this game is finished
     * @return true if finished
     */
    boolean isFinished();
}
