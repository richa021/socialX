package com.example.socialx;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp_fragment extends Fragment {

    EditText mName, mEmail, mPassword, mContact;
    Button mRegister;
    TextView mSignIn;
    FirebaseAuth fAuth;

    public SignUp_fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SignUp_fragment newInstance(String param1, String param2) {
        SignUp_fragment fragment = new SignUp_fragment();;
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
        View view = inflater.inflate(R.layout.fragment_sign_up_fragment, container, false);
        mContact = view.findViewById(R.id.editNumberSignUp);
        mEmail = view.findViewById(R.id.editEmailSignUp);
        mName = view.findViewById(R.id.editNameSignUp);
        mPassword = view.findViewById(R.id.editPassSignUp);
        mRegister = view.findViewById(R.id.signUpBtn);
        mSignIn = view.findViewById(R.id.signInText);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getContext(),NewsActivity.class));
        }

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }

                if(password.length()<8){
                    mPassword.setError("Password must be 6 or more character long");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "User created", Toast.LENGTH_SHORT).show();
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