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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class UtilisateurFragment extends Fragment {

    DbHelper dbHelper;
    private Button btnsaveagent;
    private EditText agent_nomagent;
    private EditText agent_adresseagent;
    private EditText agent_contactagent;
    private String agent_date_save;
    private EditText agent_usernameagent;
    private EditText agent_passwordagent;
    private Integer agent_sync ;
    private EditText agent_niveau;

    public UtilisateurFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance().format(calendar.getTime());
        //btn_save_agent
        View view=inflater.inflate(R.layout.fragment_utilisateur, container, false);
        btnsaveagent=view.findViewById(R.id.btn_save_agent);
        agent_nomagent=view.findViewById(R.id.txtagentnomsave);
        agent_adresseagent=view.findViewById(R.id.txtagentadresse);
        agent_contactagent=view.findViewById(R.id.txtagentcontactsave);
        agent_usernameagent=view.findViewById(R.id.txtagentusernamesave);
        agent_passwordagent=view.findViewById(R.id.txtagentpasswordsave);
        agent_date_save=""+currentDate.toString();
        agent_niveau=view.findViewById(R.id.txtagentniveausave);
        agent_sync=Dbpaiemobile.SYNC_STATUS_FAILED;

        btnsaveagent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
                saveToupServer();

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


    void saveToupServer(){

        if(agent_nomagent.getText().toString().isEmpty()||
                agent_adresseagent.getText().toString().isEmpty()||
                agent_contactagent.getText().toString().isEmpty()||
                agent_usernameagent.getText().toString().isEmpty()||
                agent_passwordagent.getText().toString().isEmpty()){
            Toast.makeText(getContext(),"Completez tous les champs svp !!!",Toast.LENGTH_LONG).show();
        } else{
            try{

                if(checkNetworkConnection())
                {

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if (Response.equals("OK")) {
                                    saveToLocalStorage(
                                            agent_nomagent.getText().toString(),
                                            agent_adresseagent.getText().toString(),
                                            agent_contactagent.getText().toString(),
                                            agent_usernameagent.getText().toString(),
                                            agent_passwordagent.getText().toString(),
                                            agent_date_save,
                                            agent_niveau.getText().toString(),
                                            Dbpaiemobile.SYNC_STATUS_OK);
                                    Toast.makeText(getContext(),"Successfully",Toast.LENGTH_SHORT).show();
                                } else {
                                    saveToLocalStorage(
                                            agent_nomagent.getText().toString(),
                                            agent_adresseagent.getText().toString(),
                                            agent_contactagent.getText().toString(),
                                            agent_usernameagent.getText().toString(),
                                            agent_passwordagent.getText().toString(),
                                            agent_date_save,
                                            agent_niveau.getText().toString(),
                                            Dbpaiemobile.SYNC_STATUS_FAILED);
                                    Toast.makeText(getContext(),"Successfully",Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            saveToLocalStorage(
                                    agent_nomagent.getText().toString(),
                                    agent_adresseagent.getText().toString(),
                                    agent_contactagent.getText().toString(),
                                    agent_usernameagent.getText().toString(),
                                    agent_passwordagent.getText().toString(),
                                    agent_date_save,
                                    agent_niveau.getText().toString(),
                                    Dbpaiemobile.SYNC_STATUS_FAILED);
                            Toast.makeText(getContext(),"Successfully",Toast.LENGTH_SHORT).show();
                        }
                    }){

                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("nom_agent",agent_nomagent.getText().toString());
                            params.put("adresse_agent",agent_adresseagent.getText().toString());
                            params.put("contact_agent",agent_contactagent.getText().toString());
                            params.put("date_save_agent",agent_date_save);
                            params.put("nom_user_agent",agent_usernameagent.getText().toString());
                            params.put("password_agent",agent_passwordagent.getText().toString());
                            params.put("niveau",agent_niveau.getText().toString());
                            return params;
                        }

                    };
                    MySingleton.getInstance(getActivity()).addToRequestQue(stringRequest);

                }
                else
                {
                    saveToLocalStorage(
                            agent_nomagent.getText().toString(),
                            agent_adresseagent.getText().toString(),
                            agent_contactagent.getText().toString(),
                            agent_usernameagent.getText().toString(),
                            agent_passwordagent.getText().toString(),
                            agent_date_save,
                            agent_niveau.getText().toString(),
                            Dbpaiemobile.SYNC_STATUS_FAILED);
                    Toast.makeText(getContext(),"Successfully",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }




    private void saveToLocalStorage(String nomagent, String adresseagent, String contactagent,
                                    String usernameagent,String passwordagent, String datesave,String niveau, int sync_status)
    {
        try{
            dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_agent(nomagent,adresseagent,contactagent,usernameagent,passwordagent,datesave, niveau,sync_status,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }


}
