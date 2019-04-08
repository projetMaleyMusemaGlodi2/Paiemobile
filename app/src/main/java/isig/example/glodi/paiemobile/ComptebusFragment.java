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
public class ComptebusFragment extends Fragment {


    private Button btnsavechargebus;
    private EditText charge_montant;
    private String charge_date_save;
    int charge_sync;
    int charge_client_id;
    DbHelper dbHelper;

    public ComptebusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_comptebus, container, false);
        dbHelper=new DbHelper(getActivity());
        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance().format(calendar.getTime());

        btnsavechargebus=view.findViewById(R.id.btn_save_chargebus);
        charge_montant=view.findViewById(R.id.edtmontantchargebus);
        charge_date_save=""+currentDate;
        charge_sync=Dbpaiemobile.SYNC_STATUS_FAILED;


        ArrayList<String> listBus=dbHelper.getAllBus();
        final AutoCompleteTextView sp=view.findViewById(R.id.edtbuscharge);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.fragment_spinner_layoutclient,R.id.txt1,listBus);
        sp.setAdapter(adapter);


        btnsavechargebus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
               if(dbHelper.getCodeBus(sp.getText().toString())!=0){

                   if(charge_montant.getText().toString().isEmpty() || Double.parseDouble(charge_montant.getText().toString())<=0){
                       Toast.makeText(getContext(),"le montant est invalide svp !!!",Toast.LENGTH_SHORT).show();
                   }else{
                       try{

                           if(checkNetworkConnection())
                           {

                               StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_CHARGE_COMPTE_BUS, new Response.Listener<String>() {
                                   @Override
                                   public void onResponse(String response) {
                                       try {
                                           JSONObject jsonObject = new JSONObject(response);
                                           String Response = jsonObject.getString("response");
                                           if (Response.equals("OK")) {
                                               saveToLocalStorage(
                                                       dbHelper.getCodeBus(sp.getText().toString()),
                                                       Double.parseDouble(charge_montant.getText().toString()),
                                                       charge_date_save,
                                                       Dbpaiemobile.SYNC_STATUS_OK);

                                               updateCompteBus(
                                                       ""+dbHelper.getCodeBus(sp.getText().toString()),
                                                       Double.parseDouble(charge_montant.getText().toString()),
                                                       dbHelper.getCompteBus(dbHelper.getCodeBus(sp.getText().toString())),
                                                       1
                                               );
                                               Toast.makeText(getContext(),"Enregistrement reussi !!!",Toast.LENGTH_LONG).show();

                                           } else {
                                               saveToLocalStorage(
                                                       dbHelper.getCodeBus(sp.getText().toString()),
                                                       Double.parseDouble(charge_montant.getText().toString()),
                                                       charge_date_save,
                                                       Dbpaiemobile.SYNC_STATUS_FAILED);

                                               updateCompteBus(
                                                       ""+dbHelper.getCodeBus(sp.getText().toString()),
                                                       Double.parseDouble(charge_montant.getText().toString()),
                                                       dbHelper.getCompteBus(dbHelper.getCodeBus(sp.getText().toString())),
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
                                               dbHelper.getCodeBus(sp.getText().toString()),
                                               Double.parseDouble(charge_montant.getText().toString()),
                                               charge_date_save,
                                               Dbpaiemobile.SYNC_STATUS_FAILED);

                                       updateCompteBus(
                                               ""+dbHelper.getCodeBus(sp.getText().toString()),
                                               Double.parseDouble(charge_montant.getText().toString()),
                                               dbHelper.getCompteBus(dbHelper.getCodeBus(sp.getText().toString())),
                                               1
                                       );
                                       Toast.makeText(getContext(),"Enregistrement reussi !!!",Toast.LENGTH_LONG).show();

                                   }
                               }){

                                   protected Map<String,String> getParams() throws AuthFailureError {
                                       Map<String,String> params=new HashMap<>();
                                       params.put("reference_bus",""+dbHelper.getCodeBus(sp.getText().toString()));
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
                                       dbHelper.getCodeBus(sp.getText().toString()),
                                       Double.parseDouble(charge_montant.getText().toString()),
                                       charge_date_save,
                                       Dbpaiemobile.SYNC_STATUS_FAILED);

                               updateCompteBus(
                                       ""+dbHelper.getCodeBus(sp.getText().toString()),
                                       Double.parseDouble(charge_montant.getText().toString()),
                                       dbHelper.getCompteBus(dbHelper.getCodeBus(sp.getText().toString())),
                                       1
                               );
                               Toast.makeText(getContext(),"Enregistrement reussi !!!",Toast.LENGTH_LONG).show();

                           }


                       }catch (Exception e){
                           e.printStackTrace();
                       }
                   }

               } else {
                   Toast.makeText(getContext(),"Le client n'existe pas svp !!!",Toast.LENGTH_SHORT).show();
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

    private void saveToLocalStorage(int buscharge, Double montantcharge, String datesave, int sync_status)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_charge(buscharge,montantcharge,datesave,sync_status,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    private void updateCompteBus(String idbus,Double montant,Double montantcompte,int typeoperation)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.updateComptebus(idbus,montant,montantcompte,typeoperation,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }



}
