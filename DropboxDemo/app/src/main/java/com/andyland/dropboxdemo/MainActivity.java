package com.andyland.dropboxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.files.SearchResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DropBoxUtil dropBoxUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropBoxUtil = DropBoxUtil.getInstance(MainActivity.this);
        dropBoxUtil.addDropBoxListener(dropBoxListener);

        dropBoxUtil.loginUser();
    }

    IDropBoxListener dropBoxListener = new IDropBoxListener() {
        @Override
        public void onDataLoaded(SearchResult result) {
            Log.e(TAG, "Result is :" + result.toStringMultiline());
        }

        @Override
        public void onError(Exception e) {
            Log.e(TAG, "Error is :" + e.getMessage());
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        String token = Auth.getOAuth2Token();
        Log.e(TAG, "Token is :" + token);

        if (token != null) {
            dropBoxUtil.getFileList(token);
        }
/*
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        SearchResult searchResult = dbxClientV2.files().search("", "*.txt");
                        List<SearchMatch> searchMatchList = searchResult.getMatches();
                        for (SearchMatch searchMatch : searchMatchList) {
                            Metadata metadata = searchMatch.getMetadata();

                            File path = Environment.getExternalStorageDirectory();
                            File file = new File(path, metadata.getName());

                            // Download the file.
                            OutputStream outputStream = new FileOutputStream(file);
                            dbxClientV2.files().download(metadata.getPathLower())
                                    .download(outputStream);

                        }

                    } catch (DbxException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        }*/
    }
}
