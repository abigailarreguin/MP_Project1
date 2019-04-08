package project1.mobile.cs.fsu.edu.project1;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.file.StandardWatchEventKinds;
import java.util.Objects;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class StartActivity extends AppCompatActivity {

    private EditText userUsername;
    private EditText userName;
    private EditText userPassword;
    private EditText userConfirmPassword;
    private EditText userPhone;
    private CheckBox userAgrees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentManager fm = getSupportFragmentManager();
        LoginFragment fragment = new LoginFragment();
        fm.beginTransaction().add(R.id.myFragment, fragment)
                .commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ContentValues getCurrentContentValues() {
        userUsername = findViewById(R.id.userUsername);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        userConfirmPassword = findViewById(R.id.userConfirm);
        userPhone = findViewById(R.id.userPhone);
        userAgrees = findViewById(R.id.userAgree);
        String message = null;

        ContentValues values = new ContentValues();

        String name = userName.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            // THEN STORE IN VALUES
            // values.put(UserContract.UsersEntry.NAME, name.trim());
        } else {
            if (message == null)
                message = "You must enter a name.";
            else {
                message = message + "\nYou must enter a name.";
            }
        }

        String username = userUsername.getText().toString().trim();
        if (!TextUtils.isEmpty(username)) {
            // THEN STORE IN VALUES
            // values.put(UserContract.UsersEntry.USERNAME, username.trim());
        } else {
            if (message == null)
                message = "You must enter a username";
            else {
                message = message + "\nYou must enter a username.";
            }
        }

        String password = userPassword.getText().toString().trim();
        String confirmPassword = userConfirmPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(password)) {
            // THEN STORE IN VALUES
        } else {
            if (message == null)
                message = "You must enter a password";
            else {
                message = message + "\nYou must enter a password.";
            }
        }
        if ((!TextUtils.isEmpty(password)) && (Objects.equals(password, confirmPassword))) {
            // THEN STORE IN VALUES
        } else if (!TextUtils.isEmpty(password)) {
            if (message == null)
                message = "Your passwords are not the same.";
            else {
                message = message + "\nYour passwords are not the same.";
            }
        }


        String phone = userPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phone)) {
            // THEN STORE IN VALUE
        } else {
            if (message == null)
                message = "You must enter a phone number";
            else {
                message = message + "\nYou must enter a phone number.";
            }
        }


        if (!(userAgrees.isChecked())) {
            if (message == null)
                message = "You must agree to terms.";
            else {
                message = message + "\nYou must agree to terms.";
            }
        }

        if (message != null) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(message);
            alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return (ContentValues) null;
        } else {
            return values;
        }

    }

}
