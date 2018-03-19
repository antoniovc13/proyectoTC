package com.tivit.talmatc.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tivit.talmatc.R;
import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.data.local.model.Archivo;

import java.io.File;

import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 25/09/2017.
 */

public class AttachUtils {

    public static String getFileNameAdjunto(Archivo adj) {
        return adj.getName();
    }

    public static boolean existeAdjunto(Archivo adj) {
        File f = new File(Configuration.PATH_ABSOLUTE_ADJUNTOS, getFileNameAdjunto(adj));
        return f.exists();
    }

    public static Bitmap getAdjuntoImageLocal(Archivo adj) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(Configuration.PATH_ABSOLUTE_PICTURES + "/" + getFileNameAdjunto(adj), options);
        if (options.outHeight > 1024 || options.outWidth > 1024) {
            return ImageUtils.getThumbnail(Configuration.PATH_ABSOLUTE_PICTURES + "/" + getFileNameAdjunto(adj), 1024);
        } else {
            return BitmapFactory.decodeFile(Configuration.PATH_ABSOLUTE_PICTURES + "/" + getFileNameAdjunto(adj));
        }
    }

    public static Bitmap getAdjuntoImageRemoto(Archivo adj, Integer size) {
        if (existeAdjunto(adj) && isImageFile(adj)) {
            //Retornar thumbnail de la imagen
            if (size != null) {
                //return ImageUtil.getThumbnail(Configuration.PATH_ABSOLUTE_ADJUNTOS + "/" + getFileNameAdjunto(adj), size);
            } else {
                //Siempre tener un tamaño maximo para la imagen!!
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(Configuration.PATH_ABSOLUTE_ADJUNTOS + "/" + getFileNameAdjunto(adj), options);
                if (options.outHeight > 1024 || options.outWidth > 1024) {
                    return ImageUtils.getThumbnail(Configuration.PATH_ABSOLUTE_ADJUNTOS + "/" + getFileNameAdjunto(adj), 1024);
                } else {
                    return BitmapFactory.decodeFile(Configuration.PATH_ABSOLUTE_ADJUNTOS + "/" + getFileNameAdjunto(adj));
                }
            }
        }

        return null;
    }

    public static Bitmap getAdjuntoExtensionImage(String path) {
        String ext = path.substring(path.lastIndexOf(".") + 1).toLowerCase().trim();
        if (ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg")) {
            return BitmapFactory.decodeResource(TivitApplication.getAppContext().getResources(), R.mipmap.ic_type_image);
        } else if (ext.equals("xlsx") || ext.contains("xls")) {
            return BitmapFactory.decodeResource(TivitApplication.getAppContext().getResources(), R.mipmap.ic_type_excel);
        } else if (ext.equals("pdf")) {
            return BitmapFactory.decodeResource(TivitApplication.getAppContext().getResources(), R.mipmap.ic_type_pdf);
        } else if (ext.equals("flv") || ext.equals("avi") || ext.equals("3gp") || ext.equals("mp4") || ext.equals("m4p")) {
            return BitmapFactory.decodeResource(TivitApplication.getAppContext().getResources(), R.mipmap.ic_type_video);
        } else {
            return BitmapFactory.decodeResource(TivitApplication.getAppContext().getResources(), R.mipmap.ic_type_unknow);
        }
    }

    public static boolean isImageFile(Archivo adj) {
        try {
            String path = adj.getName();
            String ext = path.substring(path.lastIndexOf(".") + 1).toLowerCase().trim();
            if (ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg")) {
                return true;
            }
        } catch (Exception ex) {
            Timber.e("Error validando si archivo es imagen: %s", ex);
        }
        return false;
    }

    public static File getAdjuntoFile(Archivo adj) {
        return new File(Configuration.PATH_ABSOLUTE_ADJUNTOS, getFileNameAdjunto(adj));
    }
    
}
