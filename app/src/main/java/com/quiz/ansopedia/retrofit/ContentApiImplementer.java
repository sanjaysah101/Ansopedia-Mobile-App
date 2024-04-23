package com.quiz.ansopedia.retrofit;

import com.quiz.ansopedia.R;
import com.quiz.ansopedia.Utility.ApiInterceptor;
import com.quiz.ansopedia.Utility.ApiService;
import com.quiz.ansopedia.api.ApiResponse;
import com.quiz.ansopedia.models.Contents;
import com.quiz.ansopedia.models.LoginModel;
import com.quiz.ansopedia.models.LoginRequestModel;
import com.quiz.ansopedia.models.Notification;
import com.quiz.ansopedia.models.UserDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentApiImplementer {
    private static Retrofit retrofit;
    public static String BASE_URL = "https://ansopedia-backend.vercel.app/api/";
//    private static String BASE_URL = "http://192.168.137.42:8000/api/";

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(new ApiInterceptor())
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static void getLogin(LoginRequestModel loginRequestModel, Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
//        Call<List<LoginModel>> call = apiService.getLogin(loginRequestModel);
        Call<ApiResponse<LoginModel>> call = apiService.getLogin(loginRequestModel);
        call.enqueue(cb);
    }

    public static void getRegister(LoginRequestModel loginRequestModel, Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<LoginModel>> call = apiService.getRegister(loginRequestModel);
        call.enqueue(cb);
    }

    public static void getLogout(Callback<List<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<List<LoginModel>> call = apiService.getLogout();
        call.enqueue(cb);
    }

    public static void getContent(Callback<ApiResponse<List<Contents>>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<List<Contents>>> call = apiService.getContent();
        call.enqueue(cb);
    }

    public static void sendEmailResetPassword(LoginRequestModel loginRequestModel, Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<LoginModel>> call = apiService.sendEmailResetPassword(loginRequestModel);
        call.enqueue(cb);
    }

    public static void sendOTPResetPassword(LoginRequestModel loginRequestModel, Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<LoginModel>> call = apiService.sendOTPResetPassword(loginRequestModel);
        call.enqueue(cb);
    }

    public static void sendNewPasswordResetPassword(LoginRequestModel loginRequestModel, Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<LoginModel>> call = apiService.sendNewPasswordResetPassword(loginRequestModel);
        call.enqueue(cb);
    }

    public static void sendContactMessage(LoginRequestModel loginRequestModel, Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<LoginModel>> call = apiService.sendContactMessage(loginRequestModel);
        call.enqueue(cb);
    }

    public static void uploadImage(MultipartBody.Part image, Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<LoginModel>> call = apiService.uploadImage(image);
        call.enqueue(cb);
    }

    public static void getUserDetail(Callback<ApiResponse<UserDetail>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<UserDetail>> call = apiService.getUserDetail();
        call.enqueue(cb);
    }

    public static void updateUserDetail(UserDetail userDetail, Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<LoginModel>> call = apiService.updateUserDetail(userDetail);
        call.enqueue(cb);
    }

    public static void getNotification(Callback<ApiResponse<List<Notification>>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<List<Notification>>> call = apiService.getNotification();
        call.enqueue(cb);
    }

    public static void getRankers(Callback<ApiResponse<List<UserDetail>>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<List<UserDetail>>> call = apiService.getRankers();
        call.enqueue(cb);
    }

    public static void signInWithGoogle(Callback<ApiResponse<LoginModel>> cb) {
        ApiService apiService = getRetrofit().create(ApiService.class);
        Call<ApiResponse<LoginModel>> call = apiService.signInWithGoogle();
        call.enqueue(cb);
    }
}
