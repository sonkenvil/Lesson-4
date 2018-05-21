package com.framgia.nguyenson.lesson4;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LoadImageAsyncTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {
    private Context mContext;
    private CallBack mCallBack;
    public LoadImageAsyncTask(Context context, CallBack callBack) {
        this.mContext = context;
        mCallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Bitmap> doInBackground(String... strings) {
        Uri uri;
        Cursor cursor;
        Bitmap bmp;
        int column_index_data;
        ArrayList<Bitmap> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = mContext.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            File f = new File(absolutePathOfImage);
            bmp = decodeFile(f,300,800);
            listOfAllImages.add(bmp);
        }
        return listOfAllImages;
    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> strings) {
         mCallBack.onData(strings);
    }

    interface CallBack{
        void onData(ArrayList<Bitmap> list);
    }

    private  Bitmap decodeFile(File f,int WIDTH,int HIGHT){
        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //The new size we want to scale to
            final int REQUIRED_WIDTH=WIDTH;
            final int REQUIRED_HIGHT=HIGHT;
            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_WIDTH && o.outHeight/scale/2>=REQUIRED_HIGHT)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }
}
