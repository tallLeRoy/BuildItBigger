/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.LeRoy.builditbigger.backend;

import com.example.JokesForAll;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.LeRoy.example.com",
                ownerName = "backend.builditbigger.LeRoy.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {
    private static JokesForAll mJokesForAll = null;

    /**
     * A simple endpoint method that takes a name and says Hi back
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }
     */

    @ApiMethod(name = "grabAJoke")
    public MyBean grabAJoke() {
        if (mJokesForAll == null) {
            mJokesForAll = new JokesForAll();
        }
        String[] joke = mJokesForAll.getAJokeWithSource();

        MyBean response = new MyBean();
        response.setData(joke);

        return response;
    }

}
