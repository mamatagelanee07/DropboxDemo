package com.andyland.dropboxdemo;

import android.app.Activity;

import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxClientV2;

/**
 * Created by mamata.gelanee on 4/12/2016.
 */
public class DropBoxUtil {
    private Activity mActivity;
    private static DropBoxUtil dropBoxUtil = null;
    private IDropBoxListener iDropBoxListener = null;

    public DropBoxUtil(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public static DropBoxUtil getInstance(Activity context) {
        if (dropBoxUtil == null)
            dropBoxUtil = new DropBoxUtil(context);
        return dropBoxUtil;
    }

    public void addDropBoxListener(IDropBoxListener iDropBoxListener) {
        this.iDropBoxListener = iDropBoxListener;
    }

    public void loginUser() {
        Auth.startOAuth2Authentication(mActivity, mActivity.getString(R.string.dropbox_api_key));
    }

    public void getFileList(String token) {
        DropboxClientFactory.init(token);
        new ListFilesTask(DropboxClientFactory.getClient(), iDropBoxListener).execute();
    }
}
