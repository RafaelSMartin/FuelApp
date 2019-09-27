package com.rsmartin.fuelapp.presentation.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.presentation.internal.android.SharedPref;
import com.rsmartin.fuelapp.presentation.ui.AbstractFragment;
import com.rsmartin.fuelapp.presentation.ui.splash.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterFragment extends AbstractFragment {

    public static final String TAG = "RegisterFragment";
    private Context context;

//    private OnFragmentInteractionListener mListener;

    @BindView(R.id.register)
    Button register;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.surname)
    EditText surname;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.password)
    EditText password;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        initAdView(view);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameCompleted = name.getText().toString().trim() + " " + surname.getText().toString().trim();
                singUpNewUsers(getActivity(), email.getText().toString().trim(), password.getText().toString().trim(), nameCompleted);
            }
        });


        return view;
    }

    private void initAdView(View view) {
        AdView mAdView = view.findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("8D10FA36EE0D8F85F9C7B1331F2F81D0")
                .addTestDevice("FA02415696106289FBE38417A007FF69")
                .build();
        mAdView.loadAd(adRequest);
    }

//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }


//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }


    public void singUpNewUsers(Context context, String email, String password, String nameCompleted) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nameCompleted)
                .build();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateProfile(user, profileUpdates);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Fallo en el registro",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void updateProfile(FirebaseUser user, UserProfileChangeRequest profileUpdates) {
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                            saveUserInfo();
                            startActivity(new Intent(context, SplashActivity.class));
                        }
                    }
                });
    }

    public void saveUserInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photoUrl = user.getPhotoUrl() == null ? "" : user.getPhotoUrl().toString();

            // Check if user's email is verified.
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();

            SharedPref.getInstance().saveStringPreferences(IExtras.USER_NAME, name);
            SharedPref.getInstance().saveStringPreferences(IExtras.USER_EMAIL, email);
            SharedPref.getInstance().saveStringPreferences(IExtras.USER_PHOTO_URL, photoUrl);
            SharedPref.getInstance().saveBooleanPreferences(IExtras.USER_EMAIL_VERIFIED, emailVerified);
            SharedPref.getInstance().saveStringPreferences(IExtras.USER_UID, uid);

            Log.e(TAG, "Name: " + name + "\n" +
                    "email: " + email + "\n" +
                    "photoUrl: " + photoUrl + "\n" +
                    "email verified: " + emailVerified + "\n" +
                    "uid: " + uid);
        }
    }

}
