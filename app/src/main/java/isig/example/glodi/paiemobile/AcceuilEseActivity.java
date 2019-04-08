package isig.example.glodi.paiemobile;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import isig.example.glodi.paiemobile.Classes.Dbpaiemobile;
import isig.example.glodi.paiemobile.Classes.MySingleton;
import isig.example.glodi.paiemobile.Classes.clsclient;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AcceuilEseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil_ese);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    if(checkNetworkConnection()){
                        DbHelper dbHelper=new DbHelper(AcceuilEseActivity.this);
                        //SQLiteDatabase database = dbHelper.getWritableDatabase();
                        //database.enableWriteAheadLogging();
                        //Cursor cursor = database.rawQuery("PRAGMA journal_mode = WAL;", null);
                        //cursor.close();

                        dbHelper.saveToUpServer_Agent(AcceuilEseActivity.this);
                        dbHelper.saveToUpServer_Bus(AcceuilEseActivity.this);
                        dbHelper.saveToUpServer_PaieBus(AcceuilEseActivity.this);
                        dbHelper.saveToUpServer_ChargeCompteClient(AcceuilEseActivity.this);
                        dbHelper.saveToUpServer_Prix(AcceuilEseActivity.this);
                        dbHelper.saveToUpServer_ChargeCompteBus(AcceuilEseActivity.this);
                        try{
                            dbHelper.saveToUpServer_Client(AcceuilEseActivity.this);

                            //saveToUpServer_Client();

                        }catch (Exception ex){

                        }finally {
                            Toast.makeText(getApplicationContext(),"Succefully",Toast.LENGTH_SHORT).show();
                            //if(getCurProcessName(getApplicationContext()).equals("replace your application package name")){
                               // dbHelper=new DbHelper(AcceuilEseActivity.this);
                            //}
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Vous n'etes pas connecte svp",Toast.LENGTH_SHORT).show();
                        //if(getCurProcessName(getApplicationContext()).equals("replace your application package name")){
                        //    DbHelper dbHelper=new DbHelper(AcceuilEseActivity.this);
                        //}
                    }

                }catch (Exception ex){

                }

                //if(getCurProcessName(getApplicationContext()).equals("replace your application package name")){
                   // DbHelper dbHelper=new DbHelper(AcceuilEseActivity.this);
                    //SQLiteDatabase database = dbHelper.getWritableDatabase();
                    //database.enableWriteAheadLogging();
               // }
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      //  .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void saveToUpServer_Client(){

        try{

            final DbHelper dbHelper=new DbHelper(getApplicationContext());
            final SQLiteDatabase database=dbHelper.getWritableDatabase();
            database.beginTransaction();
            Cursor cursor=dbHelper.getAllClientNonSync(database);

            try{
                while(cursor.moveToNext())
                {

                    int sync_status=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.SYNC_STATUT_CLIENT));
                    if(sync_status==Dbpaiemobile.SYNC_STATUS_FAILED){
                        final int codeclient=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_CLIENT));
                        final String nomaclient=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOM_CLIENT));
                        final String adresseclient=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.ADRESSE_CLIENT));
                        final String contactclientt=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CONTACT_CLIENT));
                        final String datesaveclient=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.DATE_SAVE_CLIENT));
                        final String usernameclient=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOM_USER_CLIENT));
                        final String passwordclient=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.PASSWORD_CLIENT));
                        final byte[] imageclient=cursor.getBlob(cursor.getColumnIndex(Dbpaiemobile.IMAGE_CLIENT));
                        final Double compteclient=cursor.getDouble(cursor.getColumnIndex(Dbpaiemobile.COMPTE_CLIENT));

                        final Bitmap bitmapclient=BitmapFactory.decodeByteArray(imageclient,0,imageclient.length);
                        //dbHelper.updateLocalDatabase_client(codeclient,Dbpaiemobile.SYNC_STATUS_OK);

                        StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_CLIENT, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject=new JSONObject(response);
                                    String Response=jsonObject.getString("response");
                                    if(Response.equals("OK")){
                                        //final SQLiteDatabase database=dbHelper.getWritableDatabase();
                                        dbHelper.updateLocalDatabase_client(codeclient,Dbpaiemobile.SYNC_STATUS_OK);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })
                        {
                            protected Map<String,String> getParams() throws AuthFailureError {
                                Map<String,String> params=new HashMap<>();
                                params.put("matricule_client",""+codeclient);
                                params.put("nom_client",nomaclient);
                                params.put("adresse_client",adresseclient);
                                params.put("contact_client",contactclientt);
                                params.put("date_save_client",datesaveclient);
                                params.put("nom_user_client",usernameclient);
                                params.put("password_client",passwordclient);
                                params.put("nom_client_image",nomaclient);
                                params.put("image",imageToString(bitmapclient));
                                params.put("compte_client",""+compteclient);
                                return params;
                            }

                        }
                                ;
                        MySingleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
                        //dbHelper.updateLocalDatabase_client(codeclient,Dbpaiemobile.SYNC_STATUS_OK);

                    }
                    //if(cursor.isLast()){
                    // cursor.close();
                    // dbHelper.close();
                    //}
                    //cursor.moveToNext();
                }
                database.setTransactionSuccessful();
                cursor.close();
                dbHelper.close();

            }catch (Exception e){
                //e.printStackTrace();
            }
            //finally {
            //database.endTransaction();
            // database.close();
            //}

        }catch (Exception ex){

        }
    }

    private  String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes=outputStream.toByteArray();

        String encodeImage= Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodeImage;
    }






    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "";
    }
    public boolean checkNetworkConnection()
    {

        ConnectivityManager connectivityManager=(ConnectivityManager) AcceuilEseActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo!=null && networkInfo.isConnected());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acceuil_ese, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_paiement) {

            ListClientFragment listClientFragment=new ListClientFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout,listClientFragment).commit();
            Animatoo.animateSwipeRight(AcceuilEseActivity.this);
        } else if (id == R.id.nav_chargecompteclient) {

            chargecptclientFragment chargecompteclient=new chargecptclientFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout,chargecompteclient).commit();
            Animatoo.animateSwipeRight(AcceuilEseActivity.this);

        } else if (id == R.id.nav_chargecomptebus) {

            ComptebusFragment comptebusFragment=new ComptebusFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout,comptebusFragment).commit();
            Animatoo.animateSwipeRight(AcceuilEseActivity.this);

        } else if (id == R.id.nav_utilisateur) {

            UtilisateurFragment utilisateurFragment=new UtilisateurFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout,utilisateurFragment).commit();
            Animatoo.animateSwipeRight(AcceuilEseActivity.this);

        } else if (id == R.id.nav_prixbus) {

            ParametrePrixFragment parametrePrixFragment=new ParametrePrixFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout,parametrePrixFragment).commit();
            Animatoo.animateSwipeRight(AcceuilEseActivity.this);

        } else if (id == R.id.nav_scanner) {

            PaieBusClientFragment paieBusClientFragment=new PaieBusClientFragment();
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout,paieBusClientFragment).commit();
            Animatoo.animateSwipeRight(AcceuilEseActivity.this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
