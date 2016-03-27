package ru.test.dao;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a ScoreDAO interface that uses files
 * to store score data. It serializes data structure into file
 * using ObjectOutputStream and then recovers it using ObjectInputStream.
 */
public class SerializedObjectScoreDAO implements ScoreDAO
{
    public static final String FILENAME = "results";

    public SerializedObjectScoreDAO(String filePostfix)
    {
        storeFilename = FILENAME + "_" + filePostfix.replaceAll("[^a-zA-Z0-9]","");
    }

    /**
     * Function to store score data using object serialization into file
     *
     * @param scores data structure that needs to be stored
     * @throws Exception
     */
    @Override
    public void save(Map<String, Integer> scores) throws Exception
    {
        try (
            FileOutputStream fos = new FileOutputStream(new File(storeFilename));
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        )
        {
            oos.writeObject(scores);
        }
        catch (IOException e)
        {
            logger.debug("Can not save to save file", e);
        }
    }

    /**
     * Function to recover data from the file using object deserialization
     *
     * @return score data structure that was recovered
     * @throws Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Integer> load() throws Exception
    {
        try (
                FileInputStream fis = new FileInputStream(new File(storeFilename));
                ObjectInputStream ois = new ObjectInputStream(fis)
        )
        {
            return (Map<String, Integer>) ois.readObject();
        }
        catch (IOException e)
        {
            logger.debug("Can not load save file", e);
            return new HashMap<>();
        }
    }

    public String getStoreFilename()
    {
        return storeFilename;
    }

    /**
     * File postfix is used when dealing with multiple games,
     * that allows to store and recover score data for each game independently
     */
    private final String storeFilename;
    private final Logger logger = Logger.getLogger(getClass());
}
