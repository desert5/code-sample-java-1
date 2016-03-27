package ru.test.game;

import ru.test.dao.SerializedObjectScoreDAO;
import ru.test.score.ScoreManagerImpl;
import ru.test.score.SimpleTableRenderer;

/**
 * Class that incapsulates game instantiation process
 */
public class GameFactory
{
    /**
     * Creates a Guess Game instance. Expected arguments:
     *
     * For {@link GameType#GUESSGAME}:
     * 0: String unique id to distinguish between variations of a game or to store scores of each game level
     * 1: Integer minimal number in range to guess
     * 2: Integer maximum number in range to guess
     */
    public static Game createGame(GameType type, Object... args) throws Exception
    {
        switch (type)
        {
            case GUESSGAME:
            {
                GuessNumberGame game = new GuessNumberGame((Integer) args[1], (Integer) args[2], System.in);
                game.setTableRenderer(new SimpleTableRenderer());
                game.setScoreManager(new ScoreManagerImpl(new SerializedObjectScoreDAO((String) args[0])));
                return game;
            }
            default: {
                throw new Exception("Unknown game");
            }
        }
    }
}
