package com.yundong.milk.util;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by LMW on 2016/6/7.
 * Retrofit二次封装
 */
public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;

    private RetrofitUtils() {
    }

    /**
     * 1
     *
     * @return
     */
    public static RetrofitUtils getInstance() {
        if (null == retrofitUtils) {
            synchronized (RetrofitUtils.class) {
                if (null == retrofitUtils)
                    retrofitUtils = new RetrofitUtils();
            }
        }
        return retrofitUtils;
    }

    private static Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private OkHttpClient getOkHttpInstance() {
        if (null == okHttpClient) {
            synchronized (OkHttpClient.class) {
                if (null == okHttpClient) {
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * 2
     *
     * @return
     */
    public Retrofit getRetrofit(String baseURL) {
        if (null == retrofit) {
            synchronized (Retrofit.class) {
                if (null == retrofit) {
                    //构建Retrofit
                    retrofit = new Retrofit.Builder().
                            //增加返回值为Gson的支持(以实体类返回)
                                    addConverterFactory(GsonConverterFactory.create(new Gson())).
                            //增加返回值为Oservable<T>的支持
                                    addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                            //增加返回值为String的支持
                                    addConverterFactory(ScalarsConverterFactory.create()).
                                    client(getOkHttpInstance()).
                                    baseUrl(baseURL).
                                    build();
                }
            }
        }
        return retrofit;
    }

    /**
     * 2
     *
     * @return
     */
    public Retrofit getStringRetrofit(String baseURL) {
        //构建Retrofit
        return new Retrofit.Builder().
                client(getOkHttpInstance()).
                baseUrl(baseURL).
                //增加返回值为String的支持
                        addConverterFactory(ScalarsConverterFactory.create()).
                        build();
    }

    /**
     * 3
     * 得到service对象
     *
     * @param baseURL
     * @param <T>
     * @return
     */
    public <T> T retrofitCtreate(String baseURL, Class<T> clzz) {
        return getRetrofit(baseURL).create(clzz);
    }
    /**
     * 3
     * 得到service对象
     *
     * @param baseURL
     * @param <T>
     * @return
     */
    public <T> T retrofitCtreateString(String baseURL, Class<T> clzz) {
        return getStringRetrofit(baseURL).create(clzz);
    }

}
