package ru.verkhoumov.retrofit;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static ApiInterface apiInterface;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                // Основная часть адреса.
                .baseUrl("http://umorili.herokuapp.com/")
                // Конвертер для преобразования JSON-данных в объекты.
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Объект для выполнения запросов к API.
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getApi() {
        return apiInterface;
    }
}
