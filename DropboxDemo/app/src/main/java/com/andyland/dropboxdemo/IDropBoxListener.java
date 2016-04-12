package com.andyland.dropboxdemo;

import com.dropbox.core.v2.files.SearchResult;

/**
 * Created by mansi.vora on 4/6/2016.
 */
public interface IDropBoxListener {
    void onDataLoaded(SearchResult result);
    void onError(Exception e);
}
