package ru.test.util;

/**
 * Project specific implementation of Pair
 */
public class Result
{
    private String name;
    private Integer score;

    public Result()
    {
    }

    public Result(String name, Integer score)
    {
        this.name = name;
        this.score = score;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getScore()
    {
        return score;
    }

    public void setScore(Integer score)
    {
        this.score = score;
    }
}
