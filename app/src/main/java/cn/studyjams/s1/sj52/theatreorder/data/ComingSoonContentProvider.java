package cn.studyjams.s1.sj52.theatreorder.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by 1 on 2017/8/21.
 */

public class ComingSoonContentProvider extends ContentProvider {

    ComingSoonSQLiteOpenerHelper comingSoonSQLiteOpenerHelper;
    public static final Uri URI = Uri.parse("content://sally.theatreOrder");

    @Override
    public boolean onCreate() {
        comingSoonSQLiteOpenerHelper = new ComingSoonSQLiteOpenerHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor =  comingSoonSQLiteOpenerHelper.getWritableDatabase().query(ComingSoonSQLiteOpenerHelper.TABLE_COMING_SOON,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        comingSoonSQLiteOpenerHelper.getWritableDatabase().update(ComingSoonSQLiteOpenerHelper.TABLE_COMING_SOON,values,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return comingSoonSQLiteOpenerHelper.getWritableDatabase().update(ComingSoonSQLiteOpenerHelper.TABLE_COMING_SOON,values,selection,selectionArgs);
    }
}
