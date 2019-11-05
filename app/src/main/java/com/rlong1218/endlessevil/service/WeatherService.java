package com.rlong1218.endlessevil.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.rlong1218.endlessevil.BuildConfig;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

  static WeatherService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @GET("/data/2.5/weather")
  Single<WeatherData> get(@Query("id") int cityId, @Query("APPID") String apiKey);

  class InstanceHolder {

    private static final WeatherService INSTANCE;

    static {
      // TODO Skip creation of interceptor and client for production.
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      Retrofit retrofit = new Retrofit.Builder()
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create(gson))
          .baseUrl(BuildConfig.BASE_URL)
          .client(client) // TODO Leave this out for production.
          .build();
      INSTANCE = retrofit.create(WeatherService.class);    }
  }

  class WeatherData {

    @Expose
    private Weather weather;

    public Weather getWeather() {
      return weather;
    }

    public void setWeather(Weather weather) {
      this.weather = weather;
    }
  }

  class Weather {

    @Expose
    private String icon;

    public String getIcon() {
      return icon;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }

  }

}
