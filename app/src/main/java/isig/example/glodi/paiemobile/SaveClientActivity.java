package isig.example.glodi.paiemobile;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import isig.example.glodi.paiemobile.Classes.Dbpaiemobile;
import isig.example.glodi.paiemobile.Classes.MySingleton;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SaveClientActivity extends AppCompatActivity {

    private android.support.design.widget.FloatingActionButton btnuploadimage;
    private ImageView imageView;
    private Button btnsaveclient;
    private Bitmap bitmap;
    final  int REQUEST_CODE_GALLERY=999;
    Integer REQUEST_CAMERA=1,
            SELECT_FILE=0;


    private EditText nameclient;
    private EditText adresseclient;
    private EditText contactclient;
    private EditText nomuserclient;
    private EditText passwordclient;
    private double compteclient;
    private String dateactuelle;
    private Integer syncclient ;

    private  Bitmap bitmapInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_client);

        initialise();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    DbHelper dbHelper=new DbHelper(getApplicationContext());
                    dbHelper.deleteData(Integer.parseInt(nameclient.getText().toString()));
                    Toast.makeText(getApplicationContext(),"suppression reussie avec succes",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"suppression echouée avec succes",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnuploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
                SelectImage();
            }
        });

        enregistremenmtclient();


    }

    public boolean checkNetworkConnection()
    {

        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo!=null && networkInfo.isConnected());
    }


    void enregistremenmtclient(){

        btnsaveclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
               if(nameclient.getText().toString().isEmpty()||
                       adresseclient.getText().toString().isEmpty()||
                       contactclient.getText().toString().isEmpty()||
                       nomuserclient.getText().toString().isEmpty()||
                       passwordclient.getText().toString().isEmpty()){

                   Toast.makeText(getApplicationContext(),"Completez tous les champs svp !!!",Toast.LENGTH_SHORT).show();
               }else{
                   try{

                       saveToLocalStorage(
                               nameclient.getText().toString(),
                               adresseclient.getText().toString(),
                               contactclient.getText().toString(),
                               dateactuelle,
                               nomuserclient.getText().toString(),
                               passwordclient.getText().toString(),
                               imageViewToByte(imageView),
                               compteclient,
                               Dbpaiemobile.SYNC_STATUS_FAILED);
                       Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();

                   }catch (Exception e){
                       e.printStackTrace();

                       Toast.makeText(getApplicationContext(),"Dened"+ e.getMessage(),Toast.LENGTH_SHORT).show();
                   }
               }


                //Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT);

            }
        });
    }


    public static Bitmap loadBitmapFromView(View v)    {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

    private  String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes=outputStream.toByteArray();

        String encodeImage= Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodeImage;
    }


    void initialise(){

        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance().format(calendar.getTime());
        btnuploadimage=(FloatingActionButton) findViewById(R.id.uploadphotoclient);
        imageView=(ImageView) findViewById(R.id.save_photo_client_ok);
        btnsaveclient=(Button) findViewById(R.id.btnsaveidentiteclient);


        nameclient=(EditText) findViewById(R.id.txtclientnomsave);
        adresseclient=(EditText) findViewById(R.id.txtclientaadressesave);
        contactclient=(EditText) findViewById(R.id.txtclientcontactsave);
        nomuserclient=(EditText) findViewById(R.id.txtclientusernamesave);
        passwordclient=(EditText) findViewById(R.id.txtclientpasswordsave);
        compteclient=0;
        dateactuelle=""+currentDate.toString();
        syncclient=Dbpaiemobile.SYNC_STATUS_FAILED;
    }

    private void saveToLocalStorage(String name, String adresse, String contact, String dateactuelle,
                                    String usernameclient, String passwordclient, byte[] imageclient,
                                    Double compte_client, int sync_status)
    {
        try{
            DbHelper dbHelper=new DbHelper(this);
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_client(name,adresse,contact,dateactuelle,usernameclient,
            passwordclient,imageclient,compte_client,sync_status,database);
            dbHelper.close();
            Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void SelectImage(){


        final CharSequence[] items={"Camera","Gallery","Cancel"};
        AlertDialog.Builder builder=new AlertDialog.Builder(SaveClientActivity.this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(items[i].equals("Camera")){
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);
                }else if(items[i].equals("Gallery")){
                    Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent,SELECT_FILE);
                }else if(items[i].equals("Cancel")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(),
                        "You don't have permission to access to this file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==Activity.RESULT_OK){
            if(requestCode==REQUEST_CAMERA){

                Bundle bundle=data.getExtras();
                //final Bitmap bmp=(Bitmap) bundle.get("data");
                bitmap=(Bitmap) bundle.get("data");
                bitmapInsert=bitmap;
                imageView.setImageBitmap(bitmap);
            }
            else if(requestCode==SELECT_FILE){
                Uri selectImageUri=data.getData();

                    try {
                        bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),selectImageUri);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //InputStream inputStream=getContentResolver().openInputStream(selectImageUri);
                    //bitmap=BitmapFactory.decodeStream(inputStream);
                    //bitmapInsert=bitmap;
                    //imageView.setImageBitmap(bitmap);


            }



        }

    }



}
