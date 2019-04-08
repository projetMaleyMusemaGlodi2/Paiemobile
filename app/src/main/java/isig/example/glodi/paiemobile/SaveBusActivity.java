package isig.example.glodi.paiemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import isig.example.glodi.paiemobile.Classes.Dbpaiemobile;
import isig.example.glodi.paiemobile.Classes.MySingleton;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SaveBusActivity extends AppCompatActivity {

    private Button btnsavebus;

    private EditText bus_numeroplaque;
    private EditText bus_marque;
    private EditText bus_nomprop;
    private EditText bus_adresseprop;
    private EditText bus_contactprop;
    private EditText bus_usernameprop;
    private EditText bus_passwordprop;
    private EditText bus_nomchauffeur;
    private EditText bus_adressechauffeur;
    private EditText bus_contactchauffeur;
    private EditText bus_usernamechauffeur;
    private EditText bus_passwordchauffeur;
    private double bus_compte;
    private Integer bus_sync ;
    private String bus_date_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_bus);

        initialise();
        enregistremenmtbus();


    }


    public boolean checkNetworkConnection()
    {

        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo!=null && networkInfo.isConnected());
    }

    void enregistremenmtbus(){

        btnsavebus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
               if(
                       bus_numeroplaque.getText().toString().isEmpty()||
                               bus_marque.getText().toString().isEmpty()||
                       bus_nomprop.getText().toString().isEmpty()||
                       bus_adresseprop.getText().toString().isEmpty()||
                       bus_contactprop.getText().toString().isEmpty()||
                       bus_usernameprop.getText().toString().isEmpty()||
                       bus_passwordprop.getText().toString().isEmpty()||
                       bus_nomchauffeur.getText().toString().isEmpty()||
                       bus_adressechauffeur.getText().toString().isEmpty()||
                       bus_contactchauffeur.getText().toString().isEmpty()||
                       bus_usernamechauffeur.getText().toString().isEmpty()||
                       bus_passwordchauffeur.getText().toString().isEmpty())
               {
                   Toast.makeText(getApplicationContext(),"Completez tous les champs svp !!!",Toast.LENGTH_SHORT).show();
               }
               else{
                   try{
                       if(checkNetworkConnection())
                       {

                           StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_BUS, new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   try {
                                       JSONObject jsonObject = new JSONObject(response);
                                       String Response = jsonObject.getString("response");
                                       if (Response.equals("OK")) {
                                           saveToLocalStorage(
                                                   bus_numeroplaque.getText().toString(),
                                                   bus_marque.getText().toString(),
                                                   bus_nomprop.getText().toString(),
                                                   bus_adresseprop.getText().toString(),
                                                   bus_contactprop.getText().toString(),
                                                   bus_usernameprop.getText().toString(),
                                                   bus_passwordprop.getText().toString(),
                                                   bus_nomchauffeur.getText().toString(),
                                                   bus_adressechauffeur.getText().toString(),
                                                   bus_contactchauffeur.getText().toString(),
                                                   bus_usernamechauffeur.getText().toString(),
                                                   bus_passwordchauffeur.getText().toString(),
                                                   bus_compte,
                                                   bus_date_save,
                                                   Dbpaiemobile.SYNC_STATUS_OK);
                                           Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
                                       } else {
                                           saveToLocalStorage(
                                                   bus_numeroplaque.getText().toString(),
                                                   bus_marque.getText().toString(),
                                                   bus_nomprop.getText().toString(),
                                                   bus_adresseprop.getText().toString(),
                                                   bus_contactprop.getText().toString(),
                                                   bus_usernameprop.getText().toString(),
                                                   bus_passwordprop.getText().toString(),
                                                   bus_nomchauffeur.getText().toString(),
                                                   bus_adressechauffeur.getText().toString(),
                                                   bus_contactchauffeur.getText().toString(),
                                                   bus_usernamechauffeur.getText().toString(),
                                                   bus_passwordchauffeur.getText().toString(),
                                                   bus_compte,
                                                   bus_date_save,
                                                   Dbpaiemobile.SYNC_STATUS_FAILED);
                                           Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
                                       }

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   saveToLocalStorage(
                                           bus_numeroplaque.getText().toString(),
                                           bus_marque.getText().toString(),
                                           bus_nomprop.getText().toString(),
                                           bus_adresseprop.getText().toString(),
                                           bus_contactprop.getText().toString(),
                                           bus_usernameprop.getText().toString(),
                                           bus_passwordprop.getText().toString(),
                                           bus_nomchauffeur.getText().toString(),
                                           bus_adressechauffeur.getText().toString(),
                                           bus_contactchauffeur.getText().toString(),
                                           bus_usernamechauffeur.getText().toString(),
                                           bus_passwordchauffeur.getText().toString(),
                                           bus_compte,
                                           bus_date_save,
                                           Dbpaiemobile.SYNC_STATUS_FAILED);
                                   Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
                               }
                           }){

                               protected Map<String,String> getParams() throws AuthFailureError {
                                   Map<String,String> params=new HashMap<>();
                                   params.put("numeroplaque_bus",bus_numeroplaque.getText().toString());
                                   params.put("marque_bus",bus_marque.getText().toString());
                                   params.put("nom_proprietaire",bus_nomprop.getText().toString());
                                   params.put("adresse_proprietaire",bus_adresseprop.getText().toString());
                                   params.put("contact_proprietaire",bus_contactprop.getText().toString());
                                   params.put("nomuser_proprietaire",bus_usernameprop.getText().toString());
                                   params.put("password_proprietaire",bus_passwordprop.getText().toString());
                                   params.put("nom_chauffeur",bus_nomchauffeur.getText().toString());
                                   params.put("adresse_chauffeur",bus_adressechauffeur.getText().toString());
                                   params.put("contact_chauffeur",bus_contactchauffeur.getText().toString());
                                   params.put("nomuser_chauffeur",bus_usernamechauffeur.getText().toString());
                                   params.put("password_chauffeur",bus_passwordchauffeur.getText().toString());
                                   params.put("comptebus",""+bus_compte);
                                   params.put("date_save_bus",bus_date_save);
                                   return params;
                               }

                           };
                           MySingleton.getInstance(SaveBusActivity.this).addToRequestQue(stringRequest);

                       }
                       else
                       {
                           saveToLocalStorage(
                                   bus_numeroplaque.getText().toString(),
                                   bus_marque.getText().toString(),
                                   bus_nomprop.getText().toString(),
                                   bus_adresseprop.getText().toString(),
                                   bus_contactprop.getText().toString(),
                                   bus_usernameprop.getText().toString(),
                                   bus_passwordprop.getText().toString(),
                                   bus_nomchauffeur.getText().toString(),
                                   bus_adressechauffeur.getText().toString(),
                                   bus_contactchauffeur.getText().toString(),
                                   bus_usernamechauffeur.getText().toString(),
                                   bus_passwordchauffeur.getText().toString(),
                                   bus_compte,
                                   bus_date_save,
                                   Dbpaiemobile.SYNC_STATUS_FAILED);
                           Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
                       }





                   }catch (Exception e){
                       e.printStackTrace();

                       Toast.makeText(getApplicationContext(),"Dened"+ e.getMessage(),Toast.LENGTH_SHORT).show();
                   }
               }


                //Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT);

            }
        });
    }


    void initialise(){


        btnsavebus=(Button) findViewById(R.id.btn_bus_save);
        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance().format(calendar.getTime());

        bus_numeroplaque=(EditText) findViewById(R.id.txtbusnumeroplaque);
        bus_marque=(EditText) findViewById(R.id.txtbusmarque);
        bus_nomprop=(EditText) findViewById(R.id.txtbusnomproprietaire);
        bus_adresseprop=(EditText) findViewById(R.id.txtbusadresseproprietaire);
        bus_contactprop=(EditText) findViewById(R.id.txtbuscontactproprietaire);
        bus_usernameprop=(EditText) findViewById(R.id.txtbususernameproprietaire);
        bus_passwordprop=(EditText) findViewById(R.id.txtbuspasswordproprietaire);
        bus_nomchauffeur=(EditText) findViewById(R.id.txtbusnomchauffeur);
        bus_adressechauffeur=(EditText) findViewById(R.id.txtbusadressechauffeur);
        bus_contactchauffeur=(EditText) findViewById(R.id.txtbuscontactchauffeur);
        bus_usernamechauffeur=(EditText) findViewById(R.id.txtbususernamechauffeur);
        bus_passwordchauffeur=(EditText) findViewById(R.id.txtbuspasswordchauffeur);
        bus_compte=0;
        bus_date_save=""+currentDate.toString();
        bus_sync=Dbpaiemobile.SYNC_STATUS_FAILED;
    }

    private void saveToLocalStorage(String numeroplaque, String marquebus, String nomprop, String adresseprop,String contactprop,
                                    String usernameprop,String passwordprop, String nompchauff, String adressechauff,String contactchauff,
                                    String usernamechauff,String passwordchauff,Double compte_bus,String datesave, int sync_status)
    {
        try{
            DbHelper dbHelper=new DbHelper(this);
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_bus(numeroplaque,marquebus,nomprop,adresseprop,contactprop,usernameprop,
                    passwordprop,nompchauff,adressechauff,contactchauff,usernamechauff,passwordchauff,compte_bus,datesave,sync_status,database);
            dbHelper.close();
            Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Failed"+e.getMessage(),Toast.LENGTH_SHORT);
        }
    }

}
