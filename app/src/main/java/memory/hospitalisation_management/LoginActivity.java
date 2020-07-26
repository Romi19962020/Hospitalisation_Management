package memory.hospitalisation_management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static memory.hospitalisation_management.Constantns.SERVER_URL;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    String usernameResult,passwordResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final RadioButton medecin=findViewById(R.id.medecin);
        final RadioButton infirmier=findViewById(R.id.infirmier);
         username = (EditText) findViewById(R.id.username);
         password = (EditText) findViewById(R.id.password);
        final TextView insc = findViewById(R.id.insc);
        final View button = findViewById(R.id.login);
        insc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(register,99);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().length() == 0 && password.getText().length() == 0) {
                    username.setError("entrer votre eusername");
                    password.setError("entrer votre mot de passe");
                    Toast.makeText(LoginActivity.this, " veuillez entrer votre username et mot de passe", Toast.LENGTH_LONG).show();


                } else {
                    if (username.getText().toString().isEmpty()) {
                        username.setError("entrer votre username");
                        Toast.makeText(LoginActivity.this, "veuillez entrer votre username", Toast.LENGTH_SHORT).show();

                    }
                    if (password.getText().toString().isEmpty()) {
                        password.setError("entrer votre mot de passe");
                        Toast.makeText(LoginActivity.this, "veuillez entrer votre mot de passe", Toast.LENGTH_SHORT).show();
                    }

                    if (medecin.isChecked()) {
                        getDoctorFromServer(new Login(username.getText().toString(), password.getText().toString()));
                    } else {
                        Toast.makeText(LoginActivity.this, "votre username ou password sont incorrects", Toast.LENGTH_SHORT).show();
                    }
                    if (infirmier.isChecked()) {
                        getNurseFromServer(new Login(username.getText().toString(), password.getText().toString()));

//                                Toast.makeText(LoginActivity.this," succes infirmier",Toast.LENGTH_SHORT).show();
                    } /*else {
                        Toast.makeText(LoginActivity.this, "votre username ou password sont incorrects", Toast.LENGTH_SHORT).show();
                    }*/
                }
                          /*  else{
                                Toast.makeText(LoginActivity.this,"votre eusername ou mot de passe sont incorrects",Toast.LENGTH_SHORT).show();

                            }*/
                // Toast toast=Toast.makeText(this.onClick(),"eusername ou mdps incorrects",Toast.LENGTH_SHORT);
                // toast.show();
            }


        });
    }
    private void getDoctorFromServer(Login login){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        JsonObject data = gson.toJsonTree(login).getAsJsonObject();
        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        Ion.with(getApplicationContext())
                .load("POST",SERVER_URL+"/Login/Doctor")
                .setJsonObjectBody(data)
                .as(Doctor.class)
                .setCallback(new FutureCallback<Doctor>() {
                    @Override
                    public void onCompleted(Exception e, Doctor result) {
                        // do stuff with the result or error
                        if (result==null){
                            Toast.makeText(LoginActivity.this,"erreur connexion au serveur",Toast.LENGTH_SHORT).show();
                        }else{
                            startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
                            Toast.makeText(LoginActivity.this," connecté avec succé",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    private void getNurseFromServer(Login login){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        JsonObject data = gson.toJsonTree(login).getAsJsonObject();
        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        Ion.with(getApplicationContext())
                .load("POST",SERVER_URL+"/Login/Nurse")
                .setJsonObjectBody(data)
                .as(Nurse.class)
                .setCallback(new FutureCallback<Nurse>() {
                    @Override
                    public void onCompleted(Exception e, Nurse result) {
                        // do stuff with the result or error
                        if (result==null){
                            Toast.makeText(LoginActivity.this,"votre username ou mot de passe sont incorrects",Toast.LENGTH_SHORT).show();

                        }else{
                            startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
                            Toast.makeText(LoginActivity.this," connecté avec succé",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    //  public void register(View view) {

    //startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

    // }

}

