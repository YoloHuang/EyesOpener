package com.example.rj.openeyesvideo.di.module;

import com.example.rj.openeyesvideo.APP.Constants;
import com.example.rj.openeyesvideo.model.http.Api;
import com.example.rj.openeyesvideo.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rj on 2017/12/21.
 */
@Module
public class HttpModule {

    //retrofitHelper的构造方法中有个API，所以这里要provide api
    @Provides
    @Singleton
    Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient okHttpClient){
        return builder
                .baseUrl(Api.HOST)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(OkHttpClient.Builder builder){
        File cacheFile=new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile,1024*1024*50);
        Interceptor cacheInterceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                if(!SystemUtil.isNetworkConnected()){
                    request=request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response=chain.proceed(request);
                if(SystemUtil.isNetworkConnected()){
                    int maxAge=0;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                }else {
                    int maxStale=60*60*24*3;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(){
        return  new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkhttpClientBuilder(){
        return new OkHttpClient.Builder();
    }

}
