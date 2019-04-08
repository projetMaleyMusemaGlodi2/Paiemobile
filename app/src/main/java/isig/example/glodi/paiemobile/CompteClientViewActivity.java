package isig.example.glodi.paiemobile;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import isig.example.glodi.paiemobile.Classes.ClinetAdapter;
import isig.example.glodi.paiemobile.Classes.Dbpaiemobile;
import isig.example.glodi.paiemobile.Classes.clsclient;
import isig.example.glodi.paiemobile.Classes.profil_client_adapter;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;
import isig.example.glodi.paiemobile.Classes.clsUsersession;

import java.util.ArrayList;

public class CompteClientViewActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<clsclient> list;
    profil_client_adapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_client_view);

        gridView=(GridView) findViewById(R.id.gridViewcompteclient);
        list=new ArrayList<>();
        adapter=new profil_client_adapter(this,R.layout.activity_client,list);
        gridView.setAdapter(adapter);
        final DbHelper dbHelper=new DbHelper(this);
        Cursor cursor=dbHelper.getData("SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT +" WHERE "+Dbpaiemobile.CODE_CLIENT+" = "+clsUsersession.getInstance().getId()+"");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String adresse=cursor.getString(2);
            String contact=cursor.getString(3);
            String datesave=cursor.getString(4);
            byte[] image=cursor.getBlob(7);
            String compte=cursor.getString(8);
            //String nomlabel=cursor.getString(1);
            list.add(new clsclient(id,name,adresse,contact,datesave,image,Integer.parseInt(compte)));
        }
        adapter.notifyDataSetChanged();


    }
}
