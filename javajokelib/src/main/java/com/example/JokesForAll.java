package com.example;

import java.util.Random;

public class JokesForAll {
    // do not translate the following strings
    private static final String JOKE_SOURCE_URL_OLF_IT = "http://onelinefun.com/it/";
    // end no translation section
    
    // translate the following strings for internationalization
    private static String[][] jokes = {
        {"Failure is not an option; it comes bundled with the software.",JOKE_SOURCE_URL_OLF_IT},
        {"I changed my password to \"incorrect\". So whenever I forget what it is the computer will say \"Your password is incorrect\".",JOKE_SOURCE_URL_OLF_IT},
        {"I think my neighbor is stalking me as she's been googling my name on her computer. I saw it through my telescope last night.",JOKE_SOURCE_URL_OLF_IT},
        {"I named my hard drive \"dat ass\" so once a month my computer asks if I want to 'back dat ass up'.",JOKE_SOURCE_URL_OLF_IT},
        {"A clean house is the sign of a broken computer.",JOKE_SOURCE_URL_OLF_IT},
        {"A TV can insult your intelligence, but nothing rubs it in like a computer.",JOKE_SOURCE_URL_OLF_IT},
        {"What was Forrest Gump's email password? \"1forrest1\"",JOKE_SOURCE_URL_OLF_IT},
        {"A computer once beat me at chess, but it was no match for me at kick boxing.",JOKE_SOURCE_URL_OLF_IT},
        {"My New Years resolution is 1080p.",JOKE_SOURCE_URL_OLF_IT},
        {"I love the F5 key. It\'s just so refreshing.",JOKE_SOURCE_URL_OLF_IT}
    };
    // end translation section


    private static Random randomGenerator = null;

    public JokesForAll() {
        if (randomGenerator == null) {
            randomGenerator = new Random(System.currentTimeMillis());
        }
    }

    public String[] getAJokeWithSource() {
       return jokes[randomGenerator.nextInt(jokes.length)];
    }

    public String getAJoke() { return getAJokeWithSource()[0]; }
}
