package com.cears.serviceapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by VIKAS SAHU on 02/01/17.
 */

public class ImageUtil {
    public static Bitmap convert(String imagebyte) throws IllegalArgumentException {
        imagebyte = URLDecoder.decode(imagebyte);
        byte[] decodedBytes = Base64.decode(
                imagebyte.substring(imagebyte.indexOf(",") + 1),
                Base64.DEFAULT
        );
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 20, outputStream);
        return URLEncoder.encode(Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT));
    }
    public static Bitmap scaleBitmap(
            Bitmap bitmap, int wantedWidth, int wantedHeight) {
        Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) wantedWidth / bitmap.getWidth(), (float) wantedHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
    }
}

