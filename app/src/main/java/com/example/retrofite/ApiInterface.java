package com.example.retrofite;

import com.example.model.Headlines;
import com.example.model.SourcesSttus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<Headlines>getHeadlines(
      @Query("country")String country,
              @Query("apikey")String apikey
    );
    @GET("everything")
    Call<Headlines>searchQuery(
            @Query("q")String searchText,
            @Query("apikey")String apikey
    );
    @GET("sources")
    Call<SourcesSttus>getSources(
            @Query("apikey")String apikey
    );
    @GET("top-headlines")
    Call<Headlines>getSports(
            @Query("category")String category,
            @Query("country")String country,
            @Query("apikey")String apikey
    );
    @GET("top-headlines")
    Call<Headlines>getPolitics(
            @Query("category")String category,
            @Query("country")String country,
            @Query("apikey")String apikey
    );
    @GET("top-headlines")
    Call<Headlines>getTechnology(
            @Query("category")String category,
            @Query("country")String country,
            @Query("apikey")String apikey
    );
    @GET("top-headlines")
    Call<Headlines>getHealth(
            @Query("category")String category,
            @Query("country")String country,
            @Query("apikey")String apikey
    );





}
