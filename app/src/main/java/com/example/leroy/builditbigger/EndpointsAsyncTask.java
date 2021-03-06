package com.example.leroy.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.leroy.builditbigger.backend.myApi.MyApi;
import com.example.leroy.displayajoke.DisplayAJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LeRoy on 11/3/2015.
 */
public class EndpointsAsyncTask extends AsyncTask<Object, Void, List<String> > {private static MyApi myApiService = null;
    public static final String IO_ERROR = "-- Error --";
    private Context context;
    private ProgressBar mProgressBar = null;

    @Override
    protected List<String> doInBackground(Object... params) {
        MyApi.Builder builder = null;
        // set a null context for androidTest runs
        // add a second parameter to run against the local test app server

        if(myApiService == null) {  // Only do this once
            if (isLocalAppServerRunning()) {
                builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
                    // end options for devappserver
            } else {
                // use the deployed appspot server
                builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://builditbigger-1118.appspot.com/_ah/api/");
            }
            try {
                myApiService = builder.build();
            } catch (Exception e) {
                return Arrays.asList(e.getMessage(), IO_ERROR);
            }
        }

        context = (Context)params[0];
        mProgressBar = (ProgressBar)params[1];

        try {
            return myApiService.grabAJoke().execute().getData();
        } catch (IOException e) {
            return Arrays.asList(e.getMessage(), IO_ERROR);
        }
    }

    @Override
    protected void onPostExecute(List<String> result) {
//        Toast.makeText(context, result.get(0), Toast.LENGTH_LONG).show();
        // context will be null on an androidTest run
        if (context != null) {
            if (result.size() >= 2) {
                String joke = result.get(0);
                String source = result.get(1);
                Intent intent = new Intent(context, DisplayAJokeActivity.class);
                intent.putExtra("joke", joke);
                intent.putExtra("source", source);
                context.startActivity(intent);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    private boolean isLocalAppServerRunning() {
        boolean exists = false;
        Socket sock = null;
        try {
            SocketAddress sockaddr = new InetSocketAddress("10.0.2.2", 8080);
            // Create an unbound socket
            sock = new Socket();

            // This method will block no more than timeoutMs.
            // If the timeout occurs, SocketTimeoutException is thrown.
            int timeoutMs = 500;   // 1/2 second
            sock.connect(sockaddr, timeoutMs);
            exists = true;
        }catch(Exception e) {
        } finally {
            if (sock != null) {
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exists;
    }

}
