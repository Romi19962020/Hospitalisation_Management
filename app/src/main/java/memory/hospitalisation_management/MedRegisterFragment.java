package memory.hospitalisation_management;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedRegisterFragment extends Fragment {


    public MedRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_medregister, container, false);
        final EditText nom= view.findViewById(R.id.nom);
        final EditText prenom= view.findViewById(R.id.prenom);
        final EditText grade= view.findViewById(R.id.grade);
        final EditText mailI= view.findViewById(R.id.mailI);
        final EditText userI= view.findViewById(R.id.userI);
        final EditText passI= view.findViewById(R.id.passI);
        final EditText num= view.findViewById(R.id.num);
        final View button = view.findViewById(R.id.insc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nom.getText().length()==0 && prenom.getText().length()==0 && grade.getText().length() == 0 && mailI.getText().length() == 0 && userI.getText().length()==0 && passI.getText().length() == 0&& num.getText().length()==0) {
                    nom.setError("entrer votre nom");
                    prenom.setError("entrer votre prenom");
                    grade.setError("entrer votre grade");
                    mailI.setError("entrer votre email");
                    userI.setError("entrer votre nom d'utilisateur");
                    passI.setError("entrer votre mot de passe");
                    num.setError("entrer votre numéro");
                    Toast.makeText(getActivity(),"veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();

                }  else {
                    if (nom.getText().length() == 0) {
                        nom.setError("entrer votre nom");
                        Toast.makeText(getActivity(),"veuillez entrer votre nom",Toast.LENGTH_SHORT).show();


                    } else {
                        if (prenom.getText().length() == 0) {
                            prenom.setError("entrer votre prenom");
                            Toast.makeText(getActivity(),"veuillez entrer votre prenom",Toast.LENGTH_SHORT).show();

                        } else {
                            if (grade.getText().length() == 0) {
                                grade.setError("entrer votre grade");
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
                                                Toast.makeText(getActivity(),"veuillez entrer votre mot de passe",Toast.LENGTH_SHORT).show();

                                            } else {
                                                if (passI.length() < 6) {
                                                    passI.setError("votre mot de passe doit contenir au moins 6 caractères");
                                                    Toast.makeText(getActivity(),"veuillez entrer un mot de passe contenant au moins 6 caractères",Toast.LENGTH_SHORT).show();

                                                } else {
                                                    startActivity(new Intent(getContext(), MainActivity.class));
                                                    Toast.makeText(getActivity(),"votre compte a été créer avec succés",Toast.LENGTH_SHORT).show();

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
                }}
        });
        return view;
    }


}