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

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParametrePrixFragment extends Fragment {


    DbHelper dbHelper;
    private Button btnsaveprix;
    private EditText prix_montant;
    int prix_sync;

    public ParametrePrixFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_parametre_prix, container, false);
        btnsaveprix=view.findViewById(R.id.button_save_prix);
        prix_montant=view.findViewById(R.id.edtPrixBus);
        prix_sync=Dbpaiemobile.SYNC_STATUS_FAILED;

        btnsaveprix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
               if(prix_montant.getText().toString().isEmpty() || Double.parseDouble(prix_montant.getText().toString())<=0){
                   Toast.makeText(getContext(),"le montant est inavalide svp !!!",Toast.LENGTH_SHORT).show();
               }else{
                   try{

                       if(checkNetworkConnection())
                       {

                           StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_PRIX, new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   try {
                                       JSONObject jsonObject = new JSONObject(response);
                                       String Response = jsonObject.getString("response");
                                       if (Response.equals("OK")) {
                                           saveToLocalStorage(
                                                   Double.parseDouble(prix_montant.getText().toString()),
                                                   Dbpaiemobile.SYNC_STATUS_OK);
                                           Toast.makeText(getActivity(),"Successfully",Toast.LENGTH_SHORT);
                                       } else {
                                           saveToLocalStorage(
                                                   Double.parseDouble(prix_montant.getText().toString()),
                                                   Dbpaiemobile.SYNC_STATUS_FAILED);
                                           Toast.makeText(getActivity(),"Successfully",Toast.LENGTH_SHORT);

                                       }

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   saveToLocalStorage(
                                           Double.parseDouble(prix_montant.getText().toString()),
                                           Dbpaiemobile.SYNC_STATUS_FAILED);
                                   Toast.makeText(getActivity(),"Successfully",Toast.LENGTH_SHORT);

                               }
                           }){

                               protected Map<String,String> getParams() throws AuthFailureError {
                                   Map<String,String> params=new HashMap<>();
                                   params.put("montant_prix",""+Double.parseDouble(prix_montant.getText().toString()));
                                   return params;
                               }

                           };
                           MySingleton.getInstance(getActivity()).addToRequestQue(stringRequest);

                       }
                       else
                       {
                           saveToLocalStorage(
                                   Double.parseDouble(prix_montant.getText().toString()),
                                   Dbpaiemobile.SYNC_STATUS_FAILED);
                           Toast.makeText(getActivity(),"Successfully",Toast.LENGTH_SHORT);
                       }


                   }catch (Exception e){
                       e.printStackTrace();
                   }
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


    private void saveToLocalStorage(Double montant, int sync_status)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_prix(montant,sync_status,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }



}
