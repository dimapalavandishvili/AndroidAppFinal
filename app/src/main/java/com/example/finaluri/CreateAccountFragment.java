package com.example.finaluri;

import android.os.Bundle;

import androidx.annotation.NonNull;
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


public class CreateAccountFragment extends Fragment {
    View objectSignupFragment;
    private EditText userEmailET,userPasswordET;
    private Button signUpButton;
    private FirebaseAuth objectFirebaseAuth;

public CreateAccountFragment(){

}

    public void createUser() {
        try
        {
            if(!userEmailET.getText().toString().isEmpty() && !userPasswordET.getText().toString().isEmpty()){
                objectFirebaseAuth.createUserWithEmailAndPassword(userEmailET.getText().toString(),userPasswordET.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getContext(), "User Created Succesfully", Toast.LENGTH_SHORT).show();
                                if(objectFirebaseAuth.getCurrentUser()!= null){
                                    objectFirebaseAuth.signOut();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }else {
                Toast.makeText(getContext(), "ველების შევსება სავალდებულოა", Toast.LENGTH_SHORT).show();

            }


        }


        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

private void attachToXML(){
    try
    {
     userEmailET = objectSignupFragment.findViewById(R.id.user_email);
     userPasswordET = objectSignupFragment.findViewById(R.id.user_password);

     objectFirebaseAuth = FirebaseAuth.getInstance();
     signUpButton = objectSignupFragment.findViewById(R.id.signupButton);

     signUpButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             createUser();
         }
     });
    }
    catch (Exception e)
    {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objectSignupFragment = inflater.inflate(R.layout.fragment_create_account, container, false);
        attachToXML();
        return objectSignupFragment;
    }
}