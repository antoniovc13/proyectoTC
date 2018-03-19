package com.tivit.talmatc.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexzander Guillermo on 22/09/2017.
 */

public class ImageUtils {

    public final static int MAX_IMAGE_SIZE = 1024;

    public static File createCameraImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Configuration.PATH_ABSOLUTE_PICTURES);

        if (!storageDir.exists()){
            if (!storageDir.mkdirs()){
                return null;
            }
        }

        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        );

        return image;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = MAX_IMAGE_SIZE;
        int resizedHeight = MAX_IMAGE_SIZE;

        if (originalHeight > originalWidth) {
            resizedHeight = MAX_IMAGE_SIZE;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = MAX_IMAGE_SIZE;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = MAX_IMAGE_SIZE;
            resizedWidth = MAX_IMAGE_SIZE;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

//    public static Image getBase64EncodedJpeg(Bitmap bitmap) {
//        Image image = new Image();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
//        byte[] imageBytes = byteArrayOutputStream.toByteArray();
//        image.encodeContent(imageBytes);
//        return image;
//    }

    public static File savebitmap(Bitmap bitmap)  {
        OutputStream outStream = null;

        File file = null;

        if(bitmap != null){
            try {
                file = createCameraImageFile();
                // make a new bitmap from your file
                outStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
                outStream.flush();
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.e("file", "" + file);
        return file;
    }

    public static Bitmap getThumbnail(String imagePath, int size) {
        return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imagePath),
                size, size);
    }

}
