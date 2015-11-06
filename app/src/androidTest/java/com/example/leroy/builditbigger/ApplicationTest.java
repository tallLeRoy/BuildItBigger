package com.example.leroy.builditbigger;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private Application application;

    public ApplicationTest() {
        super(Application.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        createApplication();
        application = getApplication();
    }

    public void testAsyncTask() throws Exception {
        EndpointsAsyncTask eat = new EndpointsAsyncTask();
        eat.execute((Context)null, "local dev app server");
        List<String> jokeWithSource = eat.get();
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