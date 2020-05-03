package memory.hospitalisation_management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText mail = (EditText) findViewById(R.id.mail);
        final EditText password = (EditText) findViewById(R.id.password);
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

                if (mail.getText().length() == 0 && password.getText().length() == 0) {
                    mail.setError("entrer votre email");
                    password.setError("entrer votre mot de passe");
                    Toast.makeText(LoginActivity.this," veuillez entrer votre email et mot de passe",Toast.LENGTH_LONG).show();


                } else {
                    if (mail.getText().length() == 0) {
                        mail.setError("entrer votre email");
                        Toast.makeText(LoginActivity.this,"veuillez entrer votre email",Toast.LENGTH_SHORT).show();

                    } else {
                        if (password.getText().length() == 0) {
                            password.setError("entrer votre mot de passe");
                            Toast.makeText(LoginActivity.this,"veuillez entrer votre mot de passe",Toast.LENGTH_SHORT).show();

                        } else {
                            if (("abc".equals(mail.getText().toString())) && ("def".equals(password.getText().toString()))) {

                                //Intent I = new Intent(login.this, ListFriend.class);
                                Bundle B = new Bundle();
                                B.putString("password", password.getText().toString());
                                B.putString("email", mail.getText().toString());
                                // I.putExtras(B);
                                //startActivity(I);
                                startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
                                Toast.makeText(LoginActivity.this," connecté avec succé",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginActivity.this,"votre email ou mot de passe sont incorrects",Toast.LENGTH_SHORT).show();

                            }
                            // Toast toast=Toast.makeText(this.onClick(),"email ou mdps incorrects",Toast.LENGTH_SHORT);
                            // toast.show();

                        }
                    }
                }
            }
        });
    }

    //  public void register(View view) {

    //startActivity(new Intent(getApplicationContext(), RegisterActivity.class));

    // }

}

