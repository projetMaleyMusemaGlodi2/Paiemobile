package isig.example.glodi.paiemobile;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import isig.example.glodi.paiemobile.Classes.clsUsersession;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;

public class ClientActivity extends AppCompatActivity {

    TextView client_name;
    TextView client_adresse;
    TextView client_contact;
    TextView client_datesave;
    TextView client_compte;
    TextView profilclient;

    clsUsersession client=new clsUsersession();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        client_name=(TextView) findViewById(R.id.compte_client_name);
        client_adresse=(TextView) findViewById(R.id.compte_client_adresse);
        client_contact=(TextView) findViewById(R.id.compte_client_contact);
        client_datesave=(TextView) findViewById(R.id.compte_client_datesave);
        client_compte=(TextView) findViewById(R.id.compte_client_compte);

        profilclient=(TextView) findViewById(R.id.compte_labelprofilclient);
        profilclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nom_client=client.getName();
                String adresse_client=client.getAdresse();
                String contact_client=client.getContact();
                String datesave_client=client.getDatesave();
                String compte_client=client.getCompte().toString();


                //client_name.setText(nom_client);
                //client_adresse.setText(adresse_client);
                //client_contact.setText(contact_client);
                //client_datesave.setText(datesave_client);
                //client_compte.setText(compte_client);

            }
        });


    }
}
