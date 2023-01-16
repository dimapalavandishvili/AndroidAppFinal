package com.example.finaluri;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInFragment extends Fragment {

    private View objectSignInFragment;
    private FirebaseAuth objectFirebaseAuth;

    private EditText userEmailET,userPasswordET;
    private Button signInBtn;
    SignInFragment(){

}

private void initializeVariables(){
        try
        {
            objectFirebaseAuth = FirebaseAuth.getInstance();
            userEmailET = objectSignInFragment.findViewById(R.id.user_email);

            userPasswordET = objectSignInFragment.findViewById(R.id.user_password);
            signInBtn = objectSignInFragment.findViewById(R.id.signinButton);
            signInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signInUser();

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

}

private void signInUser(){
        try
        {
            if(!userEmailET.getText().toString().isEmpty() && !userPasswordET.getText().toString().isEmpty())
            {
                if(objectFirebaseAuth!=null)
                {
                     objectFirebaseAuth.signInWithEmailAndPassword(userEmailET.getText().toString(),userPasswordET.getText().toString())
                             .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                 @Override
                                 public void onSuccess(AuthResult authResult) {
                                     Toast.makeText(getContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();

                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(getContext(), "Email or Password is incorrect, Try again", Toast.LENGTH_SHORT).show();

                                 }
                             });
                }
            }
            else
            {
                Toast.makeText(getContext(), "ველების შევსება სავალდებულოა", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objectSignInFragment = inflater.inflate(R.layout.fragment_sign_in, container, false);
        initializeVariables();
        return objectSignInFragment;

    }
}