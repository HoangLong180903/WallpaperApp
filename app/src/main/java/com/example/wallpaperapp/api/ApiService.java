package com.example.wallpaperapp.api;

import com.example.wallpaperapp.models.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("curated/")
    Call<Root> getWallpaper(@Header("Authorization") String credentials , @Query("page") int page , @Query("per_page") int per_page);


    @GET("search?")
    Call<Root> getSearch(
            @Header("Authorization") String credentials,
            @Query("query") String queryText
    );
}
