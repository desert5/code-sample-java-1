package ru.test;

import org.apache.log4j.Logger;
import ru.test.game.Game;
import ru.test.game.GameFactory;
import ru.test.game.GameType;

import java.util.Arrays;
import java.util.List;

/**
 * Main class, implementing generic game algorithm and level definitions
 */
public class Launcher
{
    public static void main(String[] args)
    {
        try
        {
            List<Game> games = Arrays.asList(
                    GameFactory.createGame(GameType.GUESSGAME, "level1", 0, 9),
                    GameFactory.createGame(GameType.GUESSGAME, "level2", 0, 99),
                    GameFactory.createGame(GameType.GUESSGAME, "level3", 0, 999)
            );

            for (Game game : games)
            {
                game.start();
                while (true)
                {
                    game.update();
                    if (game.isFinished())
                    {
                        break;
                    }
                }
                game.finish();
            }
        }
        catch (Throwable e)
        {
            logger.error("Generic exception", e);
        }
    }

    private final static Logger logger = Logger.getLogger(Launcher.class);
}
