package isig.example.glodi.paiemobile;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class chargecptclientFragment extends Fragment {


    private Button btnsavechargeclient;
    private EditText charge_montant;
    private String charge_date_save;
    int charge_sync;
    int charge_client_id;
    DbHelper dbHelper;
    public chargecptclientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chargecptclient, container, false);
        dbHelper=new DbHelper(getActivity());
        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance().format(calendar.getTime());

        btnsavechargeclient=view.findViewById(R.id.btn_save_chargeclient);
        charge_montant=view.findViewById(R.id.edtmontantchargeclient);
        charge_date_save=""+currentDate;
        charge_sync=Dbpaiemobile.SYNC_STATUS_FAILED;


        ArrayList<String> listClient=dbHelper.getAllCLient();
        final AutoCompleteTextView sp=view.findViewById(R.id.spinnerclientchargeclient);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.fragment_spinner_layoutclient,R.id.txt1,listClient);
        sp.setAdapter(adapter);


        btnsavechargeclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
               if(dbHelper.getCodeCLient(sp.getText().toString())!=0  ){
                   if(charge_montant.getText().toString().isEmpty() || Double.parseDouble(charge_montant.getText().toString())<=0){
                       Toast.makeText(getContext(),"le montant est invalide svp !!!",Toast.LENGTH_LONG).show();
                   }else{

                       try{

                           if(checkNetworkConnection())
                           {

                               StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_CHARGE_COMPTE_CLIENT, new Response.Listener<String>() {
                                   @Override
                                   public void onResponse(String response) {
                                       try {
                                           JSONObject jsonObject = new JSONObject(response);
                                           String Response = jsonObject.getString("response");
                                           if (Response.equals("OK")) {
                                               saveToLocalStorage(
                                                       dbHelper.getCodeCLient(sp.getText().toString()),
                                                       Double.parseDouble(charge_montant.getText().toString()),
                                                       charge_date_save,
                                                       Dbpaiemobile.SYNC_STATUS_OK);

                                               updateCompteClient(
                                                       ""+dbHelper.getCodeCLient(sp.getText().toString()),
                                                       Double.parseDouble(charge_montant.getText().toString()),
                                                       dbHelper.getCompteClient(dbHelper.getCodeCLient(sp.getText().toString())),
                                                       1
                                               );
                                               Toast.makeText(getContext(),"Enregistrement reussi !!!",Toast.LENGTH_LONG).show();

                                           } else {
                                               saveToLocalStorage(
                                                       dbHelper.getCodeCLient(sp.getText().toString()),
                                                       Double.parseDouble(charge_montant.getText().toString()),
                                                       charge_date_save,
                                                       Dbpaiemobile.SYNC_STATUS_FAILED);

                                               updateCompteClient(
                                                       ""+dbHelper.getCodeCLient(sp.getText().toString()),
                                                       Double.parseDouble(charge_montant.getText().toString()),
                                                       dbHelper.getCompteClient(dbHelper.getCodeCLient(sp.getText().toString())),
                                                       1
                                               );
                                               Toast.makeText(getContext(),"Enregistrement reussi !!!",Toast.LENGTH_LONG).show();

                                           }

                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               }, new Response.ErrorListener() {
                                   @Override
                                   public void onErrorResponse(VolleyError error) {
                                       saveToLocalStorage(
                                               dbHelper.getCodeCLient(sp.getText().toString()),
                                               Double.parseDouble(charge_montant.getText().toString()),
                                               charge_date_save,
                                               Dbpaiemobile.SYNC_STATUS_FAILED);

                                       updateCompteClient(
                                               ""+dbHelper.getCodeCLient(sp.getText().toString()),
                                               Double.parseDouble(charge_montant.getText().toString()),
                                               dbHelper.getCompteClient(dbHelper.getCodeCLient(sp.getText().toString())),
                                               1
                                       );
                                       Toast.makeText(getContext(),"Enregistrement reussi !!!",Toast.LENGTH_LONG).show();

                                   }
                               }){

                                   protected Map<String,String> getParams() throws AuthFailureError {
                                       Map<String,String> params=new HashMap<>();
                                       params.put("reference_client",""+dbHelper.getCodeCLient(sp.getText().toString()));
                                       params.put("montant_charge",""+Double.parseDouble(charge_montant.getText().toString()));
                                       params.put("date_charge",charge_date_save);
                                       return params;
                                   }

                               };
                               MySingleton.getInstance(getActivity()).addToRequestQue(stringRequest);

                           }
                           else
                           {
                               saveToLocalStorage(
                                       dbHelper.getCodeCLient(sp.getText().toString()),
                                       Double.parseDouble(charge_montant.getText().toString()),
                                       charge_date_save,
                                       Dbpaiemobile.SYNC_STATUS_FAILED);

                               updateCompteClient(
                                       ""+dbHelper.getCodeCLient(sp.getText().toString()),
                                       Double.parseDouble(charge_montant.getText().toString()),
                                       dbHelper.getCompteClient(dbHelper.getCodeCLient(sp.getText().toString())),
                                       1
                               );
                               Toast.makeText(getContext(),"Enregistrement reussi !!!",Toast.LENGTH_LONG).show();

                           }

                       }catch (Exception e){
                           e.printStackTrace();
                       }

                   }

               }else{
                   Toast.makeText(getContext(),"Le client n'existe pas !!!",Toast.LENGTH_LONG).show();
               }

                //Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT);

            }
        });


        return  view;
    }

    public boolean checkNetworkConnection()
    {

        ConnectivityManager connectivityManager=(ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo!=null && networkInfo.isConnected());
    }
    private void saveToLocalStorage(int clientcharge, Double montantcharge, String datesave, int sync_status)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_chargeclient(clientcharge,montantcharge,datesave,sync_status,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    private void updateCompteClient(String idclient,Double montant,Double montantcompte,int typeoperation)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.updateCompteclient(idclient,montant,montantcompte,typeoperation,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }


}
