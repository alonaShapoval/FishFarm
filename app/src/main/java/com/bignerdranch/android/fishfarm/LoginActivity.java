package com.bignerdranch.android.fishfarm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText mEditTextEmail;
    @BindView(R.id.password)
    EditText mEditTextPassword;
    @BindView(R.id.submit_btn)
    Button mButtonSubmit;
    static String email = "", password = "";
    String sessionId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        if (sessionId != null && sessionId.equals("0")) {
            email = "";
            password = "";
        }

        if (!email.equals("") && !password.equals("")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if (!isEmailCorrect()) {
                    mEditTextEmail.setError("Не правильні дані");
                } else if (!isPasswordCorrect()) {
                    mEditTextPassword.setError("Не правильні дані");
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        mEditTextEmail.setOnKeyListener(new View.OnKeyListener() {
                                            public boolean onKey(View v, int keyCode, KeyEvent event) {
                                                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                                                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                                    // сохраняем текст, введенный до нажатия Enter в переменную


                                                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                    imm.hideSoftInputFromWindow(mEditTextEmail.getWindowToken(), 0);

                                                    return true;
                                                }
                                                return false;
                                            }
                                        }
        );
        mEditTextPassword.setOnKeyListener(new View.OnKeyListener() {
                                               public boolean onKey(View v, int keyCode, KeyEvent event) {
                                                   if (event.getAction() == KeyEvent.ACTION_DOWN &&
                                                           (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                                       // сохраняем текст, введенный до нажатия Enter в переменную


                                                       InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                       imm.hideSoftInputFromWindow(mEditTextPassword.getWindowToken(), 0);

                                                       return true;
                                                   }
                                                   return false;
                                               }
                                           }
        );
    }

    public boolean isEmailCorrect() {
        if (email.equals("")) {
            mEditTextEmail.setError("Це поле не може бути пустим");
            return false;
        }

        if (email.equals("Shapoval")) {
            return true;
        }
        return false;
    }

    public boolean isPasswordCorrect() {
        if (password.equals("")) {
            mEditTextPassword.setError("Це поле не може бути пустим");
            return false;
        }

        if (password.equals("111111")) {
            return true;
        }
        return false;
    }
}
