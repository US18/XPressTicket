package com.example.uplabdhisingh.xpressticket.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class HistoryContract
{
    public static final String CONTENT_AUTHORITY = "com.example.uplabdhisingh.xpressticket";
    public static final String SCHEME = "content://";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);
    public static final String PATH = "pnr_history";


    public static final class PNR_Entries implements BaseColumns
    {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        public static final String TABLE_NAME = "pnr_history";
        public static final String COLUMN_PNR = "pnr_entered";
    }
}
