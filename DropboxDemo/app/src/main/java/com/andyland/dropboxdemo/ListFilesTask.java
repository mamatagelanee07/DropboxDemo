package com.andyland.dropboxdemo;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.SearchResult;

/**
 * Async task to list items in a folder
 */
class ListFilesTask extends AsyncTask<Void, Void, SearchResult> {

    private DbxClientV2 mDbxClient;
    private IDropBoxListener dropBoxListener;
    private Exception mException;

    public ListFilesTask(DbxClientV2 dbxClient, IDropBoxListener dropBoxListener) {
        this.mDbxClient = dbxClient;
        this.dropBoxListener = dropBoxListener;
    }

    @Override
    protected void onPostExecute(SearchResult result) {
        super.onPostExecute(result);

        if (mException != null) {
            dropBoxListener.onError(mException);
        } else {
            dropBoxListener.onDataLoaded(result);
        }
    }

    @Override
    protected SearchResult doInBackground(Void... params) {
        try {
            return mDbxClient.files().search("", "*.txt");
        } catch (DbxException e) {
            mException = e;
        }
        return null;
    }
}
