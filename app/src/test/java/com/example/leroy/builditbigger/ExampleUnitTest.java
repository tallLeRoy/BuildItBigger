package com.example.leroy.builditbigger;

import com.example.JokesForAll;

import org.junit.Test;

import java.lang.Exception;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }


    /* no longer needed, JokesForAll is now on a server
    @Test
    public void javaLibraryProducesJoke() throws Exception {
        String[] jokeWithSource = new JokesForAll().getAJokeWithSource();
        assertNotNull(jokeWithSource);
        if (jokeWithSource.length > 0) {
            assertNotSame(jokeWithSource[0], "");
            if (jokeWithSource.length > 1) {
                assertNotSame(jokeWithSource[1], "");
            } else {
                fail("the string array length was 1");
            }
        } else {
            fail("the string array length was 0");
        }
     }
     */
}