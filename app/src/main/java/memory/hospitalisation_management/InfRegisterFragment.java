package memory.hospitalisation_management;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.text.DateFormat;

import static memory.hospitalisation_management.Constantns.SERVER_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfRegisterFragment extends Fragment {

EditText nom,prenom,ageI,mailI,userI,passI,num;
    String valueAge;
    int intVal;

    public InfRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_infregister, container, false);
        nom= view.findViewById(R.id.nom);
        prenom= view.findViewById(R.id.prenom);
        ageI=view.findViewById(R.id.ageI);
        valueAge=ageI.getText().toString();
        intVal=0;
        if(!"".equals(valueAge)){
            intVal=Integer.parseInt(valueAge);
        }
        mailI= view.findViewById(R.id.mailI);
        userI= view.findViewById(R.id.userI);
        passI= view.findViewById(R.id.passI);
        num= view.findViewById(R.id.num);
        final View button = view.findViewById(R.id.insc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nom.getText().length() == 0 && prenom.getText().length() == 0 && mailI.getText().length() == 0 && userI.getText().length() == 0 && passI.getText().length() == 0 && num.getText().length() == 0) {
                    nom.setError("entrer votre nom");
                    prenom.setError("entrer votre prenom");
                    mailI.setError("entrer votre email");
                    userI.setError("entrer votre nom d'utilisateur");
                    passI.setError("entrer votre mot de passe");
                    num.setError("entrer votre numéro");
                    Toast.makeText(getActivity(),"veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();

                } else {
                    if (nom.getText().length() == 0) {
                        nom.setError("entrer votre nom");
                         Toast.makeText(getActivity(),"veuillez entrer votre nom",Toast.LENGTH_SHORT).show();


                    } else {
                        if (prenom.getText().length() == 0) {
                            prenom.setError("entrer votre prenom");
                             Toast.makeText(getActivity(),"veuillez entrer votre prenom",Toast.LENGTH_SHORT).show();

                        } else {
                            if (mailI.getText().length() == 0) {
                                mailI.setError("entrer votre email");
                                Toast.makeText(getActivity(),"veuillez entrer votre email",Toast.LENGTH_SHORT).show();

                            } else {
                                if (userI.getText().length() == 0) {
                                    userI.setError("entrer votre nom d'utilisateur");
                                     Toast.makeText(getActivity(),"veuillez entrer votre prenom",Toast.LENGTH_SHORT).show();

                                } else {
                                    if (passI.getText().length() == 0) {
                                        passI.setError("entrer votre mot de passe");
                                        Toast.makeText(getActivity(),"veuillez entrer votre mot de passe",Toast.LENGTH_SHORT).show();

                                    } else {
                                        if (num.getText().length() == 0) {
                                            num.setError("entrer votre numèro de téléphone");
                                            Toast.makeText(getActivity(),"veuillez entrer votre numéro de téléphone",Toast.LENGTH_SHORT).show();

                                        } else {
                                            if (passI.length() < 6) {
                                                passI.setError("votre mot de passe doit contenir au moins 6 caractères");
                                                Toast.makeText(getActivity(),"veuillez entrer un mot de passe contenant au moins 6 caractères",Toast.LENGTH_SHORT).show();

                                            } else {
                                                AddNurse();

                                            }
                                            // Toast toast=Toast.makeText(this.onClick(),"email ou mdps incorrects",Toast.LENGTH_SHORT);
                                            // toast.show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
return view;
    }
    public void AddNurse() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);


        final Nurse nurse = new Nurse((long) getId(), nom.getText().toString(), prenom.getText().toString(), userI.getText().toString(), passI.getText().toString(), intVal, num.getText().toString(), mailI.getText().toString());
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        JsonObject data = gson.toJsonTree(nurse).getAsJsonObject();
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait please ...");
        progressDialog.setCancelable(false);
        Ion.with(getActivity())
                .load("POST", SERVER_URL + "/Nurse")
                .setJsonObjectBody(data)
                .as(Boolean.class)
                .setCallback(new FutureCallback<Boolean>() {
                    @Override
                    public void onCompleted(Exception e, Boolean result) {
                        if (result == null) {
                            Toast.makeText(getActivity(), "error" +e.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            startActivity(new Intent(getContext(), MainActivity.class));
                            Toast.makeText(getActivity(),"votre compte a été créer avec succés",Toast.LENGTH_SHORT).show();

                            //startActivity(new Intent(getActivity(), LoginActivity.class));
                            // do stuff with the result or error
                        }


                    }
                });


    }


}
