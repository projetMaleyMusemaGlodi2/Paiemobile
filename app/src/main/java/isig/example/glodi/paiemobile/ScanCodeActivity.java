package isig.example.glodi.paiemobile;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.zxing.Result;
import isig.example.glodi.paiemobile.Classes.Dbpaiemobile;
import isig.example.glodi.paiemobile.Classes.ExampleDialog;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import java.text.DateFormat;
import java.util.Calendar;

import static android.Manifest.permission.CAMERA;

//import static android.Manifest.permission_group.CAMERA;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static  final  int REQUEST_CAMERA=1;
    private ZXingScannerView ScannerView;
    DbHelper dbHelper=new DbHelper(this);
    Calendar calendar=Calendar.getInstance();
    String currentDate=DateFormat.getDateInstance().format(calendar.getTime());
    private String paie_date_save=""+currentDate;
    private int paie_sync=Dbpaiemobile.SYNC_STATUS_FAILED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView=new ZXingScannerView(this);
        setContentView(ScannerView);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(CheckPermissionss()){
                Toast.makeText(ScanCodeActivity.this,"Permission is granted",Toast.LENGTH_LONG).show();
            }else{
                requestPermission();
            }
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,new String[] {CAMERA},REQUEST_CAMERA);
    }

    private boolean CheckPermissionss() {
        return  (ContextCompat.checkSelfPermission(ScanCodeActivity.this,CAMERA)==PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int grantResults[]) {

        switch (requestCode){
            case REQUEST_CAMERA:
                if(grantResults.length>0){
                    boolean cameraAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted){
                        Toast.makeText(ScanCodeActivity.this,"Permission granted",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ScanCodeActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlerteMessage("you need to allow access for both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                                                    requestPermissions(new String[]{CAMERA},REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;


        }

        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void displayAlerteMessage(String message, DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(ScanCodeActivity.this)
                .setMessage(message)
                .setPositiveButton("Ok",listener)
                .setNegativeButton("Cancel",null)
                .create()
                .show();

    }

    @Override
    public void handleResult(Result result) {

        //PaieBusClientFragment.paie_montant.setText("");
        if(Integer.parseInt(result.getText())!=0){
            savepaiement(Integer.parseInt(result.getText()));
            onBackPressed();
        }else{
            Toast.makeText(this,"Le client n'existe pas !!!",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(CheckPermissionss()){
                if(ScannerView==null){
                    ScannerView=new ZXingScannerView(this);
                    setContentView(ScannerView);
                }
                ScannerView.setResultHandler(this);
                ScannerView.startCamera();
            }else{
                requestPermission();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScannerView.stopCamera();
    }

    public void savepaiement(int idclient){

        try{

            if(dbHelper.retourPrixbus()<=dbHelper.getCompteClient(idclient)){
                saveToLocalStorage(
                        idclient,
                        (double)dbHelper.retourPrixbus(),
                        paie_date_save,
                        paie_sync);

                updateCompteClient(
                        ""+idclient,
                        (double)dbHelper.retourPrixbus(),
                        dbHelper.getCompteClient(idclient),
                        2
                );
                Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                openDialog();
                Toast.makeText(getApplicationContext(),"Compte Insuffisant",Toast.LENGTH_SHORT).show();
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveToLocalStorage(int clientpaie, Double montantpaie, String datesave, int sync_status)
    {
        try{
            dbHelper=new DbHelper(this);
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
            dbHelper=new DbHelper(this);
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.updateCompteclient(idclient,montant,montantcompte,typeoperation,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }
    private void showMessage(String _s) {
        Toast.makeText(this,_s,Toast.LENGTH_SHORT).show();
    }


    public  void openDialog(){
        ExampleDialog dialog=new ExampleDialog();
        dialog.show(getSupportFragmentManager(),"example dialog");
    }




}
