package com.example.leroy.builditbigger;

import com.example.JokesForAll;
import org.junit.Test;
import java.lang.Exception;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }


    /* no longer needed, JokesForAll is now on a server
     */
    @Test
    public void javaLibraryProducesJoke() throws Exception {
        String[] jokeWithSourceArray = new JokesForAll().getAJokeWithSource();
        List<String> jokeWithSource = Arrays.asList(jokeWithSourceArray);
        if (jokeWithSource == null) {
            fail("EndpointsAsyncTask result was null");
        } else {
            if (jokeWithSource.size() > 0) {
                String joke = jokeWithSource.get(0);
                if (joke == null) {
                    fail("EndpointsAsyncTask returned a null instead of the joke");
                } else {
                    if (joke.equals("")) {
                        fail("EndpointsAsyncTask returned an empty string instead of a joke");
                    }
                    if (jokeWithSource.size() > 1) {
                        String source = jokeWithSource.get(1);
                        if (source == null) {
                            fail("EndpointsAsyncTask returned a null instead of the source");
                        } else {
                            if(source.equals("")) {
                                fail("EndpointsAsyncTask returned an empty string instead of a source");
                            }
                            if (source.equals(EndpointsAsyncTask.IO_ERROR)) {
                                // fail with the error returned from EndpointsAsyncTask
                                fail("EndpointsAsyncTask failed -- " + jokeWithSource.get(0));
                            }
                        }
                    } else {
                        fail("EndpointsAsyncTask returned a list with only one string");
                    }
                }
            } else {
                fail("EndpointsAsyncTask returned an empty list");
            }
        }
    }
}