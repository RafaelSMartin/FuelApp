package com.rsmartin.fuelapp.presentation.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.presentation.ui.AbstractActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AbstractActivity implements LoginPresenter.View {

    private String TAG = "LoginActivity";

    private FirebaseAuth mAuth;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.password)
    EditText pass;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.create)
    Button create;

    @BindView(R.id.verification)
    Button verification;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getApplicationComponent().inject(this);
        loginPresenter.setView(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

        email.setText("rafaels.martin.dev@gmail.com");
        pass.setText("12345678");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.signIn(getApplicationContext(),
                        email.getText().toString().trim(), pass.getText().toString().trim(), mAuth);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.singUpNewUsers(getApplicationContext(),
                        email.getText().toString().trim(), pass.getText().toString().trim(), mAuth);
            }
        });

        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.sendUserVerification(getApplicationContext(), mAuth);
            }
        });
    }


    @Override
    public void goToSplash() {
        navigator.navigateToSplash(getApplicationContext());
    }
}
