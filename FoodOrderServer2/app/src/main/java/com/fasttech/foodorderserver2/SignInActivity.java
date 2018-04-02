package com.fasttech.foodorderserver2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasttech.foodorderserver2.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;

public class SignInActivity extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;
    SpotsDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        btnSignIn = (Button)findViewById(R.id.btnLogin2);

        //Init Firebase
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser(edtPhone.getText().toString(),edtPassword.getText().toString());
            }
        });

    }

    private void signInUser(String phone, String password) {
        alertDialog = new SpotsDialog(SignInActivity.this, R.style.Custom);
        alertDialog.setMessage("Please Wait...");
        alertDialog.show();

        final String localPhone = phone;
        final String localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(localPhone).exists()){
                    alertDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);

                    if(Boolean.parseBoolean(user.getIsStaff())){ //IsStaff == true
                        if(user.getPassword().equals(localPassword)){
                            Intent intent = new Intent(SignInActivity.this,HomeActivity.class);
                            CurrentUser.cUser = user;
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(SignInActivity.this,"Wrong Password!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignInActivity.this,"Please Login with Staff account",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    alertDialog.dismiss();
                    Toast.makeText(SignInActivity.this,"User does not exist in the Database",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });

    }
}
