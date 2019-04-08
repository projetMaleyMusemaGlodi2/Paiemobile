package isig.example.glodi.paiemobile;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import isig.example.glodi.paiemobile.Classes.*;
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
public class PaieBusClientFragment extends Fragment {




    private Button btnsavepaiebus;
    private  ImageView btn_scan;
    public static EditText paie_montant;
    private String paie_date_save;
    int paie_sync;
    int paie_client_id;
    DbHelper dbHelper;
    public static TextView txtresult;
    public PaieBusClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_paie_bus_client, container, false);

        final Activity activity=getActivity();

        dbHelper=new DbHelper(getActivity());
        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance().format(calendar.getTime());

        btn_scan=view.findViewById(R.id.btn_scanner);
        txtresult=view.findViewById(R.id.txtresultScanner);

        btnsavepaiebus=view.findViewById(R.id.btn_save_paiebus);
        paie_montant=view.findViewById(R.id.edtmontantpaiebus);
        paie_date_save=""+currentDate;
        paie_sync=Dbpaiemobile.SYNC_STATUS_FAILED;


        ArrayList<String> listClient=dbHelper.getAllCLient();
        final AutoCompleteTextView sp=view.findViewById(R.id.spinnerclientpaiebus);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.fragment_spinner_layoutclient,R.id.txt1,listClient);
        sp.setAdapter(adapter);


        btnsavepaiebus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
               if(dbHelper.getCodeCLient(sp.getText().toString())!=0){
                   savepaiement(dbHelper.getCodeCLient(sp.getText().toString()));
                   //Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT);
               }else{
                   Toast.makeText(getContext(),"Le client n'existe pas !!!",Toast.LENGTH_LONG).show();
               }


            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ScanCodeActivity.class));



                //IntentIntegrator integrator=new IntentIntegrator(activity);
                //integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                //integrator.setPrompt("Scan");
                //integrator.setCameraId(0);
                //integrator.setBeepEnabled(false);
                //integrator.setBarecodeImageEnabled(false);
                //integrator.initiateScan ();

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){

            if(result.getContents()==null){
                Toast.makeText(getContext(),"you cancelled scanning",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getContext(),result.getContents(),Toast.LENGTH_LONG).show();
            }


        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    public void savepaiement(final int idclient){

        try{

            if(dbHelper.retourPrixbus()<=dbHelper.getCompteClient(idclient)){

                if(checkNetworkConnection())
                {

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_PAIEBUS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if (Response.equals("OK")) {
                                    saveToLocalStorage(
                                            idclient,
                                            (double)dbHelper.retourPrixbus(),
                                            paie_date_save,
                                            Dbpaiemobile.SYNC_STATUS_OK);

                                    updateCompteClient(
                                            ""+idclient,
                                            (double)dbHelper.retourPrixbus(),
                                            dbHelper.getCompteClient(idclient),
                                            2
                                    );
                                    Toast.makeText(getActivity(),"Successfully",Toast.LENGTH_SHORT).show();
                                } else {
                                    saveToLocalStorage(
                                            idclient,
                                            (double)dbHelper.retourPrixbus(),
                                            paie_date_save,
                                            Dbpaiemobile.SYNC_STATUS_FAILED);

                                    updateCompteClient(
                                            ""+idclient,
                                            (double)dbHelper.retourPrixbus(),
                                            dbHelper.getCompteClient(idclient),
                                            2
                                    );
                                    Toast.makeText(getActivity(),"Successfully",Toast.LENGTH_SHORT).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            saveToLocalStorage(
                                    idclient,
                                    (double)dbHelper.retourPrixbus(),
                                    paie_date_save,
                                    Dbpaiemobile.SYNC_STATUS_FAILED);

                            updateCompteClient(
                                    ""+idclient,
                                    (double)dbHelper.retourPrixbus(),
                                    dbHelper.getCompteClient(idclient),
                                    2
                            );
                            Toast.makeText(getActivity(),"Successfully",Toast.LENGTH_SHORT).show();

                        }
                    }){

                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("reference_client",""+idclient);
                            params.put("montant_paie",""+(double)dbHelper.retourPrixbus());
                            params.put("date_paie",paie_date_save);
                            return params;
                        }

                    };
                    MySingleton.getInstance(getActivity()).addToRequestQue(stringRequest);

                }
                else
                {
                    saveToLocalStorage(
                            idclient,
                            (double)dbHelper.retourPrixbus(),
                            paie_date_save,
                            Dbpaiemobile.SYNC_STATUS_FAILED);

                    updateCompteClient(
                            ""+idclient,
                            (double)dbHelper.retourPrixbus(),
                            dbHelper.getCompteClient(idclient),
                            2
                    );
                    Toast.makeText(getActivity(),"Successfully",Toast.LENGTH_SHORT).show();
                }

            }
            else{
                openDialog();
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveToLocalStorage(int clientpaie, Double montantpaie, String datesave, int sync_status)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_paie(clientpaie,montantpaie,datesave,sync_status,database);
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
    private void showMessage(String _s) {
        Toast.makeText(getContext(),_s,Toast.LENGTH_SHORT).show();
    }

    public  void openDialog(){
        ExampleDialog dialog=new ExampleDialog();
        dialog.show(getFragmentManager(),"example dialog");
    }

}
