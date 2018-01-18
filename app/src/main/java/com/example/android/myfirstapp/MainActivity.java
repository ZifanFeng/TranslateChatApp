package com.example.android.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String Extra_Message = "com.example.myfirstapp.Message";
    private EditText mEmail, mPwd;
    private CheckBox mRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmail = (EditText) findViewById(R.id.email);
        mPwd = (EditText) findViewById(R.id.password);
        mRememberMe = (CheckBox) findViewById(R.id.rememberMe);
        mPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id == R.id.login||id == EditorInfo.IME_NULL){
                    attemptLogin();
                    return true;
                }
                else return false;
            }
        });

        Button mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        if(! new PrefManager(this).isUserLogOut()){
            startHomeActivity();
        }
        if(! new PrefManager(this).getEmail().isEmpty()){
            mEmail.setText(new PrefManager(this).getEmail());
        }
    }

    private void attemptLogin(){
        mEmail.setError(null);
        mPwd.setError(null);
        String email = mEmail.getText().toString();
        String pwd = mPwd.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if(!TextUtils.isEmpty(pwd) && !isPasswordValid(pwd)){
            mPwd.setError("Wrong PWD");
            focusView = mPwd;
            cancel = true;
        }
        if(TextUtils.isEmpty(email)){
            mEmail.setError("require email");
            focusView =mEmail;
            cancel = true;
        }else if(!isEmailValid(email)){
            mEmail.setError("Invalid Email!!");
            focusView = mEmail;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            if(mRememberMe.isChecked()){
                new PrefManager(this).saveLoginDetail(email, pwd);
            }
            startHomeActivity();
        }
    }

    private void startHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }
    private boolean isPasswordValid(String pwd){
        return pwd.length()>4;
    }
    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(Extra_Message, message);
        startActivity(intent);
    }



}
