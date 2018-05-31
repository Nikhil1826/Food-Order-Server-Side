package com.fasttech.foodorderserver2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.fasttech.foodorderserver2.Model.Request;
import com.fasttech.foodorderserver2.Model.User;
import com.fasttech.foodorderserver2.Remote.APIService;
import com.fasttech.foodorderserver2.Remote.FCMRetrofitClient;
import com.fasttech.foodorderserver2.Remote.IGeoCoordinates;
import com.fasttech.foodorderserver2.Remote.RetrofitClient;

/**
 * Created by dell on 2/10/2018.
 */

public class CurrentUser {
    public static User cUser;
    public static Request cRequest;

    public static final String UPDATE="Update";
    public static final String DELETE="Delete";

    public static final int pick_image_request=71;

    public static final String baseUrl="https://maps.googleapis.com";
    private static final String fcmUrl="https://fcm.googleapis.com/";

    public static String convertCodeToStatus(String code){
        if(code.equals("0"))
            return "Placed";
        else if(code.equals("1"))
            return "On The Way";
        else
            return "Delievered";

    }

    public static APIService getFCMClient(){
        return FCMRetrofitClient.getClient(fcmUrl).create(APIService.class);
    }

    public static IGeoCoordinates getGeoCodeServices(){
        return RetrofitClient.getClient(baseUrl).create(IGeoCoordinates.class);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth,int newHeight){
        Bitmap scaledBitmap=Bitmap.createBitmap(newWidth,newHeight,Bitmap.Config.ARGB_8888);
        float scaleX=newWidth/(float)bitmap.getWidth();
        float scaleY=newHeight/(float)bitmap.getHeight();
        float pivotX=0,pivotY=0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX,scaleY,pivotX,pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0,new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;


    }

}
