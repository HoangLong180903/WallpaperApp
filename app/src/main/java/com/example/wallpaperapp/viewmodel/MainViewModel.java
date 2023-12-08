package com.example.wallpaperapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wallpaperapp.api.ApiClient;
import com.example.wallpaperapp.api.ApiService;
import com.example.wallpaperapp.models.Photos;
import com.example.wallpaperapp.models.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    String API_KEY = "EAqWtVcuVQw0yw2PWuwCH5SO2TnSehh48sYgo6jplL424fFkrJ4ufDKr";
    private final MutableLiveData<List<Photos>> photosLiveData = new MutableLiveData<>();
    int currentPage = 1, perPage = 40;
    private final ApiService apiService;

    public MainViewModel() {
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
    }

    public LiveData<List<Photos>> getListWallpaper() {
        apiService.getWallpaper(API_KEY, currentPage, perPage).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    photosLiveData.setValue(response.body().getPhotos());
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                photosLiveData.setValue(null);
            }
        });

        return photosLiveData;
    }
    public LiveData<List<Photos>> getSearchWallpaper(String text) {
        apiService.getSearch(API_KEY, text).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    photosLiveData.setValue(response.body().getPhotos());
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
        return photosLiveData;
    }

    public LiveData<List<Photos>> getPhotosLiveData() {
        return photosLiveData;
    }

    public void getNextPage() {
        currentPage++;
        apiService.getWallpaper(API_KEY, currentPage, perPage).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    photosLiveData.setValue(response.body().getPhotos());
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                photosLiveData.setValue(null);
            }
        });
    }
}
