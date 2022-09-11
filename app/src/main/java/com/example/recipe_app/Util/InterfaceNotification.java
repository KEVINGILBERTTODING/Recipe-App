package com.example.recipe_app.Util;

import com.example.recipe_app.Model.NotificationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceNotification {

    // get all notification
    @GET("get_notification.php")
    Call<List<NotificationModel>> getAllNotification(
            @Query("user_id") String user_id
    );


    // count total notification not read
    @GET("count_notification.php")
    Call<List<NotificationModel>> countTotalNotif(
            @Query("user_id") String userId
    );
}
