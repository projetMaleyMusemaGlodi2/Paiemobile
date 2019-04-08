package isig.example.glodi.paiemobile;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import isig.example.glodi.paiemobile.Classes.*;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;

import java.util.ArrayList;

public class CompteBusViewActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<clsBus> list;
    compte_bus_adapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_bus_view);

        gridView=(GridView) findViewById(R.id.gridViewcomptebus);
        list=new ArrayList<>();
        adapter=new compte_bus_adapter(this,R.layout.activity_proprietaire,list);
        gridView.setAdapter(adapter);
        final DbHelper dbHelper=new DbHelper(this);
        Cursor cursor=dbHelper.getData("SELECT * FROM "+Dbpaiemobile.TABLE_NAME_BUS +" WHERE "+Dbpaiemobile.CODE_BUS+" = "+Integer.parseInt(clsUsersessionbus.getInstance().getIdbus())+"");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String numplaque=cursor.getString(1);
            String marque=cursor.getString(2);
            String proprietaire=cursor.getString(3);
            String chauffeur=cursor.getString(8);
            String compte=cursor.getString(13);
            //String nomlabel=cursor.getString(1);
            list.add(new clsBus(id,numplaque,marque,proprietaire,chauffeur,(double)Integer.parseInt(compte)));
        }
        adapter.notifyDataSetChanged();


    }
}
