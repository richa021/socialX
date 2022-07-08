package com.example.socialx;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login_fragment extends Fragment {
    EditText mEmailText, mPassText;
    Button mloginButton;
    TextView mSignUpText;
    FirebaseAuth fAuth;

    public login_fragment() {
        // Required empty public constructor
    }

    public static login_fragment newInstance(String param1, String param2) {
        login_fragment fragment = new login_fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_fragment, container, false);
        mEmailText = view.findViewById(R.id.editEmailSignIN);
        mPassText = view.findViewById(R.id.editPassSignIn);
        mloginButton = view.findViewById(R.id.signInBtn);
        fAuth = FirebaseAuth.getInstance();
        mSignUpText = view.findViewById(R.id.signUpText);

        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailText.getText().toString().trim();
                String password = mPassText.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmailText.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassText.setError("Password is Required");
                    return;
                }

                if(password.length()<8){
                    mPassText.setError("Password must be 6 or more character long");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(),NewsActivity.class));
                        }
                        else{
                            Toast.makeText(getContext(), "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        return view;
    }
}