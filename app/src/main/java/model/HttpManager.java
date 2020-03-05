package model;

import java.io.File;

import constan.Constan;
import model.api.APIService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private APIService apiService;

    private static volatile HttpManager instance;
    public static HttpManager getInstance(){
        if(instance == null){
            if(instance == null){
                instance = new HttpManager();
            }
        }
        return instance;
    }

    private Retrofit getRetrofit(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .baseUrl(baseUrl)
                .build();
        return  retrofit;
    }

    private OkHttpClient okHttpClient(){

        File file = new File(Constan.PATH_CACHE);
        Cache cache = new Cache(file, 100 * 1024 * 1024);
        OkHttpClient build = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return build;
    }


    public APIService getApiService(){
        if(apiService == null){
            synchronized (APIService.class){
                if(apiService == null){
                    apiService = getRetrofit(Constan.BASE_SHOP_URL).create(APIService.class);
                }
            }
        }
        return apiService;
    }
}
