package isig.example.glodi.paiemobile;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import isig.example.glodi.paiemobile.Classes.ClinetAdapter;
import isig.example.glodi.paiemobile.Classes.Dbpaiemobile;
import isig.example.glodi.paiemobile.Classes.clsclient;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListClientFragment extends Fragment {

    GridView gridView;
    ArrayList<clsclient> list;
    ClinetAdapter adapter=null;
    final  int REQUEST_CODE_GALLERY=999;
    Integer REQUEST_CAMERA=1,SELECT_FILE=0;
    ImageView imageView;

    //ImageView imageView;

    public ListClientFragment() {
        // Required empty public constructor
    }

    ArrayList<clsclient> listeclient=new ArrayList<clsclient>();
    DbHelper databaseHelper=new DbHelper(getContext());


    private  void loaddata(){
        list=new ArrayList<clsclient>();
        list= databaseHelper.testechargementclient();
        adapter=new ClinetAdapter(getContext(),R.layout.fragment_client_item,list);
        gridView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list_client, container, false);
        // Inflate the layout for this fragment
        //btnsaveprix=view.findViewById(R.id.button_save_prix);

        //imageView=view.findViewById(R.id.button_save_prix);


        gridView=view.findViewById(R.id.gridView);
        list=new ArrayList<clsclient>();

        //loaddata();
        adapter=new ClinetAdapter(getContext(),R.layout.fragment_client_item,list);
        gridView.setAdapter(adapter);
        final DbHelper dbHelper=new DbHelper(getContext());
        Cursor cursor=dbHelper.getData("SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" ");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String adresse=cursor.getString(2);
            String contact=cursor.getString(3);
            String datesave=cursor.getString(4);
            byte[] image=cursor.getBlob(7);
            String compte=cursor.getString(8);
            //String compte=cursor.getString(5);
            list.add(new clsclient(id,name,adresse,contact,datesave,image,Integer.parseInt(compte)));
        }
        adapter.notifyDataSetChanged();


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence[] items={"Update","Delete"};
                AlertDialog.Builder dialog=new AlertDialog.Builder(getContext());
                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if(item==0)
                        {
                            //Update
                            //Toast.makeText(getContext(),"Update..",Toast.LENGTH_SHORT).show();
                            DbHelper dbHelper1=new DbHelper(getContext());
                            Cursor c=dbHelper.getData("SELECT "+Dbpaiemobile.CODE_CLIENT+" FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+"");
                            ArrayList<Integer> arrID=new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }

                            showDialogUpdate(getActivity(),arrID.get(position));
                        }
                        else
                            {
                            //Delete
                            DbHelper dbHelper1=new DbHelper(getContext());
                            Cursor c=dbHelper.getData("SELECT "+Dbpaiemobile.CODE_CLIENT+" FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+"");
                            ArrayList<Integer> arrID=new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                            //Toast.makeText(getContext(),"Delete..",Toast.LENGTH_SHORT).show();
                          }
                    }
                });
                dialog.show();
                return true;
            }
        });

        return view;
    }


    void chargementClient(){
        final DbHelper dbHelper=new DbHelper(getContext());
        Cursor cursor=dbHelper.getData("SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT +"");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String adresse=cursor.getString(2);
            String contact=cursor.getString(3);
            String datesave=cursor.getString(4);
            byte[] image=cursor.getBlob(7);
            String compte=cursor.getString(8);
            //String compte=cursor.getString(5);
            list.add(new clsclient(id,name,adresse,contact,datesave,image,Integer.parseInt(compte)));
        }
        adapter.notifyDataSetChanged();
    }


    private  void showDialogDelete(final int idClient){

        final  AlertDialog.Builder dialogDelete=new AlertDialog.Builder(getContext());
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    DbHelper dbHelper=new DbHelper(getContext());
                    dbHelper.deleteData(idClient);
                    Toast.makeText(getContext(),"suppression reussie avec succes",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getContext(),"suppression echouée avec succes",Toast.LENGTH_SHORT).show();
                }
                chargementClient();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogDelete.show();

    }

 private void showDialogUpdate(Activity activity, final int position){
     Dialog dialog=new Dialog(activity);
     dialog.setContentView(R.layout.fragment_updateclient);
     dialog.setTitle("Update");
     imageView=dialog.findViewById(R.id.update_client_photo);
     final EditText nom=dialog.findViewById(R.id.update_client_nom);
     final EditText adrsse=dialog.findViewById(R.id.update_client_adresse);
     final EditText contact=dialog.findViewById(R.id.update_client_contact);
     final EditText username=dialog.findViewById(R.id.update_client_username);
     final EditText password=dialog.findViewById(R.id.update_client_password);
     final EditText compte=dialog.findViewById(R.id.update_client_compte);
     Button btnupdate=dialog.findViewById(R.id.update_client_btn);

     int width=(int) (activity.getResources().getDisplayMetrics().widthPixels * 0.98);
     int height=(int) (activity.getResources().getDisplayMetrics().heightPixels * 0.9);
     dialog.getWindow().setLayout(width,height);
     dialog.show();

     imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             SelectImage();
         }
     });

     btnupdate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(nom.getText().toString().isEmpty()||adrsse.getText().toString().isEmpty()
                     ||contact.getText().toString().isEmpty()||
                     username.getText().toString().isEmpty()||
                     password.getText().toString().isEmpty()||
                     compte.getText().toString().isEmpty()){
                 Toast.makeText(getContext(),"Completez tous les champs svp !!!",Toast.LENGTH_SHORT).show();
             }else{
                 try{
                     DbHelper dbHelper=new DbHelper(getContext());
                     dbHelper.update_client(position,nom.getText().toString(),
                             adrsse.getText().toString(),contact.getText().toString(),
                             username.getText().toString(),password.getText().toString(),
                             Double.parseDouble(compte.getText().toString()) ,imageViewToByte(imageView));
                     Toast.makeText(getContext(),"modification reussie avec succes",Toast.LENGTH_SHORT).show();

                 }catch (Exception e){
                     Toast.makeText(getContext(),"la modification echouée",Toast.LENGTH_SHORT).show();
                 }
                 chargementClient();
             }

         }
     });

 }


    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

    private void SelectImage(){


        final CharSequence[] items={"Camera","Gallery","Cancel"};
        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(getContext());
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
                Toast.makeText(getContext(),
                        "You don't have permission to access to this file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bmp);
            } else if (requestCode == SELECT_FILE) {
                Uri selectImageUri = data.getData();
                try {
                    //InputStream in = getActivity().getContentResolver().openInputStream(data.getData());
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(selectImageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }
    }




}
