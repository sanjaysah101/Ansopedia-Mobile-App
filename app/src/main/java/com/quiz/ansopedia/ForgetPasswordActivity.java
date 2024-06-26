package com.quiz.ansopedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.quiz.ansopedia.Utility.Constants;
import com.quiz.ansopedia.Utility.Utility;
import com.quiz.ansopedia.api.ApiResponse;
import com.quiz.ansopedia.models.LoginModel;
import com.quiz.ansopedia.models.LoginRequestModel;
import com.quiz.ansopedia.retrofit.ContentApiImplementer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextInputLayout t1;
    TextInputEditText userEmail;
    MaterialButton sendOTPBtn;
    RelativeLayout svMain;
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        t1 = findViewById(R.id.t1);
        svMain = findViewById(R.id.svMain);
        userEmail = findViewById(R.id.userEmail);
        sendOTPBtn = findViewById(R.id.sendOTPBtn);
        ImageView ivBack = findViewById(R.id.ivBack);
        sendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userEmail.getText().toString();
                Utility.hideSoftKeyboard(ForgetPasswordActivity.this);
                t1.setErrorEnabled(false);
                if (isValidateCredentials()) {
                    sendEmail();
                } else {
                    try{
                        if (userName.isEmpty()) {
                            t1.setErrorEnabled(true);
                            t1.setError("* Please Enter Email");
                        }else if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
                            t1.setErrorEnabled(true);
                            t1.setError("* Please Enter Valid Email");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }
                }
            }
        });

        svMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Utility.hideSoftKeyboard(ForgetPasswordActivity.this);}
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void sendEmail() {
        Utility.showProgress(ForgetPasswordActivity.this);
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setEmail(userEmail.getText().toString().trim());
        if (Utility.isNetConnected(this)) {
            try {
                ContentApiImplementer.sendEmailResetPassword(loginRequestModel, new Callback<ApiResponse<LoginModel>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<LoginModel>> call, Response<ApiResponse<LoginModel>> response) {
                        Utility.dismissProgress(ForgetPasswordActivity.this);
                        if (response.isSuccessful()) {
//                            LoginModel loginModel = response.body().get(0);
                            if (response.body().getStatus().toLowerCase().contains("success")) {
                                startActivity(new Intent(ForgetPasswordActivity.this, EnterOtpActivity.class)
                                        .putExtra("email", userEmail.getText().toString().trim()));
                            } else {
                                Utility.showAlertDialog(ForgetPasswordActivity.this, "Error", "Something went wrong, Please Try Again");
                            }
                        }
                        if(response.errorBody() != null){
                            try {
                                JSONObject Error = new JSONObject(response.errorBody().string());
                                Utility.showAlertDialog(ForgetPasswordActivity.this, Error.getString("status").toString().trim() , Error.getString("message").toString().trim());
                            } catch (Exception e) {
                                Utility.dismissProgress(ForgetPasswordActivity.this);
                                e.printStackTrace();
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<LoginModel>> call, Throwable t) {
                        Utility.dismissProgress(ForgetPasswordActivity.this);
                        t.printStackTrace();
                        Utility.showAlertDialog(ForgetPasswordActivity.this, "Error", "Something went wrong, Please Try Again");
                    }
                });
//                ContentApiImplementer.sendEmailResetPassword(loginRequestModel,new Callback<List<LoginModel>>() {
//                    @Override
//                    public void onResponse(Call<List<LoginModel>> call, Response<List<LoginModel>> response) {
//                        Utility.dismissProgress(ForgetPasswordActivity.this);
//                        if (response.code() == 200) {
//                            LoginModel loginModel = response.body().get(0);
//                            if (loginModel.getStatus().toLowerCase().contains("success")) {
//                                startActivity(new Intent(ForgetPasswordActivity.this, EnterOtpActivity.class)
//                                        .putExtra("email", userEmail.getText().toString().trim()));
//                            } else {
//                                Utility.showAlertDialog(ForgetPasswordActivity.this, "Error", "Something went wrong, Please Try Again");
//                            }
//                        } else if(response.code() == 429){
//                            Utility.showAlertDialog(ForgetPasswordActivity.this, "Error", "Too many request, please try after 15 minutes..!!");
//                        }else {
//                            Utility.showAlertDialog(ForgetPasswordActivity.this, "Error", "Something went wrong, Please Try Again");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<LoginModel>> call, Throwable t) {
//                        Utility.dismissProgress(ForgetPasswordActivity.this);
//                        t.printStackTrace();
//                        Utility.showAlertDialog(ForgetPasswordActivity.this, "Error", "Something went wrong, Please Try Again");
//                    }
//                });
            } catch (Exception e) {
                Utility.dismissProgress(this);
                e.printStackTrace();
                FirebaseCrashlytics.getInstance().recordException(e);
            }
        } else {
            Utility.dismissProgress(this);
            Utility.showAlertDialog(this, "Error", "Please Connect to Internet");
        }
    }


    private boolean isValidateCredentials() {
        String userName = userEmail.getText().toString();
        if (!userName.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
            return true;
        } else {
            return false;
        }
    }
}