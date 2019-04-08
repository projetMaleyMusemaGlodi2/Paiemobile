package isig.example.glodi.paiemobile.DbHelpper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import isig.example.glodi.paiemobile.AgentFragment;
import isig.example.glodi.paiemobile.Classes.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DbHelper extends SQLiteOpenHelper {


    clsUsersession usersession=new clsUsersession();


    private static  final int DATABASE_VERSION=1;
    private  static final String CREATE_TABLE_CLIENT="create table "+
            Dbpaiemobile.TABLE_NAME_CLIENT+"(" +
            ""+Dbpaiemobile.CODE_CLIENT+" integer primary key autoincrement,"+
            Dbpaiemobile.NOM_CLIENT+" text,"+
            Dbpaiemobile.ADRESSE_CLIENT+" text,"+
            Dbpaiemobile.CONTACT_CLIENT+" text,"+
            Dbpaiemobile.DATE_SAVE_CLIENT+" text,"+
            Dbpaiemobile.NOM_USER_CLIENT+" text,"+
            Dbpaiemobile.PASSWORD_CLIENT+" text,"+
            Dbpaiemobile.IMAGE_CLIENT+" BLOG,"+
            Dbpaiemobile.COMPTE_CLIENT+" Integer,"+
            Dbpaiemobile.SYNC_STATUT_CLIENT+" Integer);";
    private  static final String DROP_TABLE_CLIENT="drop table if exists "+Dbpaiemobile.TABLE_NAME_CLIENT;


    private  static final String CREATE_TABLE_BUS="create table "+
            Dbpaiemobile.TABLE_NAME_BUS+"(" +
            ""+Dbpaiemobile.CODE_BUS+" integer primary key autoincrement,"+
            Dbpaiemobile.NUMERO_PLAQUE_BUS+" text,"+
            Dbpaiemobile.MARQUE_BUS+" text,"+
            Dbpaiemobile.NOM_PROPRIETAIRE+" text,"+
            Dbpaiemobile.ADRESSE_PROPRIETAIRE+" text,"+
            Dbpaiemobile.CONTACT_PROPRIETAIRE+" text,"+
            Dbpaiemobile.NOMUSER_PROPRIETAIRE+" text,"+
            Dbpaiemobile.PASSWORD_PROPRIETAIRE+" text,"+
            Dbpaiemobile.NOM_CHAUFFEUR+" text,"+
            Dbpaiemobile.ADRESSE_CHAUFFEUR+" text,"+
            Dbpaiemobile.CONTACT_CHAUFFEUR+" text,"+
            Dbpaiemobile.NOMUSER_CHAUFFEUR+" text,"+
            Dbpaiemobile.PASSWORD_CHAUFFEUR+" text,"+
            Dbpaiemobile.COMPTE_BUS+" Integer,"+
            Dbpaiemobile.DATE_SAVE_BUS+" text,"+
            Dbpaiemobile.SYNC_STATUT_BUS+" Integer);";
    private  static final String DROP_TABLE_BUS="drop table if exists "+Dbpaiemobile.TABLE_NAME_BUS;

    private  static final String CREATE_TABLE_AGENT="create table "+
            Dbpaiemobile.TABLE_NAME_AGENT+"(" +
            ""+Dbpaiemobile.CODE_AGENT+" integer primary key autoincrement,"+
            Dbpaiemobile.NOM_AGENT+" text,"+
            Dbpaiemobile.ADRESSE_AGENT+" text,"+
            Dbpaiemobile.CONTACT_AGENT+" text,"+
            Dbpaiemobile.NOM_USER_AGENT+" text,"+
            Dbpaiemobile.PASSWORD_AGENT+" text,"+
            Dbpaiemobile.DATE_SAVE_AGENT+" text,"+
            Dbpaiemobile.NIVEAU_AGENT+" text,"+
            Dbpaiemobile.SYNC_STATUT_AGENT+" Integer);";
    private  static final String DROP_TABLE_AGENT="drop table if exists "+Dbpaiemobile.TABLE_NAME_AGENT;

    private  static final String CREATE_TABLE_PAIE="create table "+
            Dbpaiemobile.TABLE_NAME_PAIEMENTBUSCLIENT+"(" +
            ""+Dbpaiemobile.CODE_PAIE+" integer primary key autoincrement,"+
            Dbpaiemobile.REFERENCE_CLIENT+" integer,"+
            Dbpaiemobile.MONTANT_PAIE+" NUMERIC,"+
            Dbpaiemobile.DATE_PAIE+" text,"+
            Dbpaiemobile.SYNC_STATUT_PAIECLIENT+" Integer);";
    private  static final String DROP_TABLE_PAIE="drop table if exists "+Dbpaiemobile.TABLE_NAME_PAIEMENTBUSCLIENT;

    private  static final String CREATE_TABLE_CHARGECLIENT="create table "+
            Dbpaiemobile.TABLE_NAME_CHARGECOMPTECLIENT+"(" +
            ""+Dbpaiemobile.CODE_CHARGECLIENT+" integer primary key autoincrement,"+
            Dbpaiemobile.REFERENCE_CLIENT_COMPTE+" integer,"+
            Dbpaiemobile.MONTANT_CHARGE_CLIENT+" NUMERIC,"+
            Dbpaiemobile.DATE_CHARGE_CLIENT+" text,"+
            Dbpaiemobile.SYNC_STATUT_CHARGECLIENT+" Integer);";
    private  static final String DROP_TABLE_CHARGECLIENT="drop table if exists "+Dbpaiemobile.TABLE_NAME_PAIEMENTBUSCLIENT;

    private  static final String CREATE_TABLE_CHARGE="create table "+
            Dbpaiemobile.TABLE_NAME_CHARGECOMPTEBUS+"(" +
            ""+Dbpaiemobile.CODE_CHARGE+" integer primary key autoincrement,"+
            Dbpaiemobile.REFERENCE_BUS+" integer,"+
            Dbpaiemobile.MONTANT_CHARGE+" NUMERIC,"+
            Dbpaiemobile.DATE_CHARGE+" text,"+
            Dbpaiemobile.SYNC_STATUT_CHARGE+" Integer);";
    private  static final String DROP_TABLE_CHARGE="drop table if exists "+Dbpaiemobile.TABLE_NAME_CHARGECOMPTEBUS;

    private  static final String CREATE_TABLE_PRIX="create table "+
            Dbpaiemobile.TABLE_NAME_PARAMETREPRIX+"(" +
            ""+Dbpaiemobile.CODE_PARAMPRIX+" integer primary key autoincrement,"+
            Dbpaiemobile.MONTANT_PRIX+" NUMERIC,"+
            Dbpaiemobile.SYNC_STATUT_PRIX+" Integer);";
    private  static final String DROP_TABLE_PRIX="drop table if exists "+Dbpaiemobile.TABLE_NAME_PARAMETREPRIX;




    public DbHelper(Context context)
    {
        super(context,Dbpaiemobile.DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_BUS);
        db.execSQL(CREATE_TABLE_AGENT);
        db.execSQL(CREATE_TABLE_PAIE);
        db.execSQL(CREATE_TABLE_CHARGE);
        db.execSQL(CREATE_TABLE_PRIX);
        db.execSQL(CREATE_TABLE_CHARGECLIENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CLIENT);
        db.execSQL(DROP_TABLE_BUS);
        db.execSQL(DROP_TABLE_AGENT);
        db.execSQL(DROP_TABLE_PAIE);
        db.execSQL(DROP_TABLE_CHARGE);
        db.execSQL(DROP_TABLE_PRIX);
        db.execSQL(DROP_TABLE_CHARGECLIENT);
        onCreate(db);
    }





    //=============================================================================================================================================
    //POUR L'ENREGISTREMENT DES CLIENTS

    public void saveTolocalDatabase_client(String name, String adresse, String contact, String dateactuelle,
                                    String usernameclient,String passwordclient,byte[] imageclient,
                                    Double compte_client, int sync_status, SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbpaiemobile.NOM_CLIENT,name);
        contentValues.put(Dbpaiemobile.ADRESSE_CLIENT,adresse);
        contentValues.put(Dbpaiemobile.CONTACT_CLIENT,contact);
        contentValues.put(Dbpaiemobile.DATE_SAVE_CLIENT,dateactuelle);
        contentValues.put(Dbpaiemobile.NOM_USER_CLIENT,usernameclient);
        contentValues.put(Dbpaiemobile.PASSWORD_CLIENT,passwordclient);
        contentValues.put(Dbpaiemobile.IMAGE_CLIENT,imageclient);
        contentValues.put(Dbpaiemobile.COMPTE_CLIENT,compte_client);
        contentValues.put(Dbpaiemobile.SYNC_STATUT_CLIENT,sync_status);
        database.insert(Dbpaiemobile.TABLE_NAME_CLIENT,null,contentValues);
    }
    public void update_client(int id,String name, String adresse, String contact,
                              String usernameclient,String passwordclient,
                              Double compte_client,byte[] imageclient)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE "+Dbpaiemobile.TABLE_NAME_CLIENT+" SET "+Dbpaiemobile.NOM_CLIENT+" = ?, "+Dbpaiemobile.ADRESSE_CLIENT+
                " = ? , "+Dbpaiemobile.CONTACT_CLIENT+" = ? , "+Dbpaiemobile.NOM_USER_CLIENT+" = ? , "+Dbpaiemobile.PASSWORD_CLIENT+
                " = ? , "+Dbpaiemobile.COMPTE_CLIENT+" = ? , "+Dbpaiemobile.IMAGE_CLIENT+" = ? WHERE "+Dbpaiemobile.CODE_CLIENT+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindString(1,name);
        statement.bindString(2,adresse);
        statement.bindString(3,contact);
        statement.bindString(4,usernameclient);
        statement.bindString(5,passwordclient);
        statement.bindDouble(6,compte_client);
        statement.bindBlob(7,imageclient);
        statement.bindDouble(8,(double)id);
        statement.execute();
        database.close();

    }

    public void deleteData(int id){
        SQLiteDatabase database=getWritableDatabase();
        String sql="DELETE FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" WHERE "+Dbpaiemobile.CODE_CLIENT+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);
        statement.execute();
        database.close();

    }

    public Cursor readFromLocalDatabase_client(SQLiteDatabase database)
    {
        String[] projection={
                Dbpaiemobile.CODE_CLIENT,
                Dbpaiemobile.NOM_CLIENT,
                Dbpaiemobile.ADRESSE_CLIENT,
                Dbpaiemobile.CONTACT_CLIENT,
                Dbpaiemobile.DATE_SAVE_CLIENT,
                Dbpaiemobile.NOM_USER_CLIENT,
                Dbpaiemobile.PASSWORD_CLIENT,
                Dbpaiemobile.IMAGE_CLIENT,
                Dbpaiemobile.COMPTE_CLIENT,
                Dbpaiemobile.SYNC_STATUT_CLIENT
        };
        return (database.query(Dbpaiemobile.TABLE_NAME_CLIENT,projection,null,null,null,null,null));
    }

    public void updateLocalDatabase_client(int codeclient,int sync_status)    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE "+Dbpaiemobile.TABLE_NAME_CLIENT+" SET "+Dbpaiemobile.SYNC_STATUT_CLIENT+" = ? WHERE "+Dbpaiemobile.CODE_CLIENT+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindDouble(1,(double)sync_status);
        statement.bindDouble(2,(double)codeclient);
        statement.execute();
        database.close();

    }

 //====================================================================================================================================
    //POUR L'ENREGISTREMENT DES BUS

    public void saveTolocalDatabase_bus(String numeroplaque, String marquebus, String nomprop,
                                    String adresseprop,String contactprop,
                                    String usernameprop,String passwordprop,
                                    String nompchauff, String adressechauff,String contactchauff,
                                    String usernamechauff,String passwordchauff,
                                    Double compte_bus,String datesave,
                                    int sync_status, SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbpaiemobile.NUMERO_PLAQUE_BUS,numeroplaque);
        contentValues.put(Dbpaiemobile.MARQUE_BUS,marquebus);
        contentValues.put(Dbpaiemobile.NOM_PROPRIETAIRE,nomprop);
        contentValues.put(Dbpaiemobile.ADRESSE_PROPRIETAIRE,adresseprop);
        contentValues.put(Dbpaiemobile.CONTACT_PROPRIETAIRE,contactprop);
        contentValues.put(Dbpaiemobile.NOMUSER_PROPRIETAIRE,usernameprop);
        contentValues.put(Dbpaiemobile.PASSWORD_PROPRIETAIRE,passwordprop);
        contentValues.put(Dbpaiemobile.NOM_CHAUFFEUR,nompchauff);
        contentValues.put(Dbpaiemobile.ADRESSE_CHAUFFEUR,adressechauff);
        contentValues.put(Dbpaiemobile.CONTACT_CHAUFFEUR,contactchauff);
        contentValues.put(Dbpaiemobile.NOMUSER_CHAUFFEUR,usernamechauff);
        contentValues.put(Dbpaiemobile.PASSWORD_CHAUFFEUR,passwordchauff);
        contentValues.put(Dbpaiemobile.COMPTE_BUS,compte_bus);
        contentValues.put(Dbpaiemobile.DATE_SAVE_BUS,datesave);
        contentValues.put(Dbpaiemobile.SYNC_STATUT_BUS,sync_status);
        database.insert(Dbpaiemobile.TABLE_NAME_BUS,null,contentValues);
    }
    public Cursor readFromLocalDatabase_bus(SQLiteDatabase database)
    {
        String[] projection={
                Dbpaiemobile.CODE_BUS,
                Dbpaiemobile.NUMERO_PLAQUE_BUS,
                Dbpaiemobile.MARQUE_BUS,
                Dbpaiemobile.NOM_PROPRIETAIRE,
                Dbpaiemobile.ADRESSE_PROPRIETAIRE,
                Dbpaiemobile.CONTACT_PROPRIETAIRE,
                Dbpaiemobile.NOMUSER_PROPRIETAIRE,
                Dbpaiemobile.PASSWORD_PROPRIETAIRE,
                Dbpaiemobile.NOM_CHAUFFEUR,
                Dbpaiemobile.ADRESSE_CHAUFFEUR,
                Dbpaiemobile.CONTACT_CHAUFFEUR,
                Dbpaiemobile.NOMUSER_CHAUFFEUR,
                Dbpaiemobile.PASSWORD_CHAUFFEUR,
                Dbpaiemobile.COMPTE_BUS,
                Dbpaiemobile.DATE_SAVE_BUS,
                Dbpaiemobile.SYNC_STATUT_BUS
        };
        return (database.query(Dbpaiemobile.TABLE_NAME_BUS,projection,null,null,null,null,null));
    }

    public void updateLocalDatabase_bus(int codebus,int sync_status)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE "+Dbpaiemobile.TABLE_NAME_BUS+" SET "+Dbpaiemobile.SYNC_STATUT_BUS+" = ? WHERE "+Dbpaiemobile.CODE_BUS+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindDouble(1,(double)sync_status);
        statement.bindDouble(2,(double)codebus);
        statement.execute();
        database.close();
    }


//==============================================================================================================================================
    //POUR L'ENREGISTREMENT DES AGENTS


    public void saveTolocalDatabase_agent(String nomagent, String adresseagent,String contactagent,
                                    String usernameagent,String passwordagent,String datesaveagent,String niveau,int sync_status, SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbpaiemobile.NOM_AGENT,nomagent);
        contentValues.put(Dbpaiemobile.ADRESSE_AGENT,adresseagent);
        contentValues.put(Dbpaiemobile.CONTACT_AGENT,contactagent);
        contentValues.put(Dbpaiemobile.NOM_USER_AGENT,usernameagent);
        contentValues.put(Dbpaiemobile.PASSWORD_AGENT,passwordagent);
        contentValues.put(Dbpaiemobile.DATE_SAVE_AGENT,datesaveagent);
        contentValues.put(Dbpaiemobile.NIVEAU_AGENT,niveau);
        contentValues.put(Dbpaiemobile.SYNC_STATUT_AGENT,sync_status);
        database.insert(Dbpaiemobile.TABLE_NAME_AGENT,null,contentValues);
    }
    public Cursor readFromLocalDatabase_agent(SQLiteDatabase database)
    {
        String[] projection={   Dbpaiemobile.CODE_AGENT,
                                Dbpaiemobile.NOM_AGENT,
                                Dbpaiemobile.ADRESSE_AGENT,
                                Dbpaiemobile.CONTACT_AGENT,
                                Dbpaiemobile.NOM_USER_AGENT,
                                Dbpaiemobile.PASSWORD_AGENT,
                                Dbpaiemobile.NIVEAU_AGENT,
                                Dbpaiemobile.DATE_SAVE_AGENT,
                                Dbpaiemobile.SYNC_STATUT_AGENT};
        return (database.query(Dbpaiemobile.TABLE_NAME_AGENT,projection,"",null,null,null,null));
    }

    public void updateLocalDatabase_agent(int codeagent,int sync_status)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE "+Dbpaiemobile.TABLE_NAME_AGENT+" SET "+Dbpaiemobile.SYNC_STATUT_AGENT+" = ? WHERE "+Dbpaiemobile.CODE_AGENT+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindDouble(1,(double)sync_status);
        statement.bindDouble(2,(double)codeagent);
        statement.execute();
        database.close();

    }

//=================================================================================================================================
    //POUR LES PAIEMENTS

    public void saveTolocalDatabase_paie(int referenceclient,Double montant,String datesavepaie,int sync_status, SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbpaiemobile.REFERENCE_CLIENT,referenceclient);
        contentValues.put(Dbpaiemobile.MONTANT_PAIE,montant);
        contentValues.put(Dbpaiemobile.DATE_PAIE,datesavepaie);
        contentValues.put(Dbpaiemobile.SYNC_STATUT_PAIECLIENT,sync_status);
        database.insert(Dbpaiemobile.TABLE_NAME_PAIEMENTBUSCLIENT,null,contentValues);
    }
    public Cursor readFromLocalDatabase_paie(SQLiteDatabase database)
    {
        String[] projection={
                Dbpaiemobile.CODE_PAIE,
                Dbpaiemobile.REFERENCE_CLIENT,
                Dbpaiemobile.MONTANT_PAIE,
                Dbpaiemobile.DATE_PAIE,
                Dbpaiemobile.SYNC_STATUT_PAIECLIENT
        };
        return (database.query(Dbpaiemobile.TABLE_NAME_PAIEMENTBUSCLIENT,projection,null,null,null,null,null));
    }

    public void updateLocalDatabase_paie(int codepaie,int sync_status)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE "+Dbpaiemobile.TABLE_NAME_PAIEMENTBUSCLIENT+" SET "+Dbpaiemobile.SYNC_STATUT_PAIECLIENT+" = ? WHERE "+Dbpaiemobile.CODE_PAIE+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindDouble(1,(double)sync_status);
        statement.bindDouble(2,(double)codepaie);
        statement.execute();
        database.close();
    }


    //=================================================================================================================================
    //POUR LES CHARGEMENTS DES COMPTES DES BUS

    public void saveTolocalDatabase_chargeclient(int referenceclient,Double montant,String datesavecharge,int sync_status, SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbpaiemobile.REFERENCE_CLIENT_COMPTE,referenceclient);
        contentValues.put(Dbpaiemobile.MONTANT_CHARGE_CLIENT,montant);
        contentValues.put(Dbpaiemobile.DATE_CHARGE_CLIENT,datesavecharge);
        contentValues.put(Dbpaiemobile.SYNC_STATUT_CHARGECLIENT,sync_status);
        database.insert(Dbpaiemobile.TABLE_NAME_CHARGECOMPTECLIENT,null,contentValues);
    }
    public Cursor readFromLocalDatabase_chargeclient(SQLiteDatabase database)
    {
        String[] projection={
                Dbpaiemobile.CODE_CHARGECLIENT,
                Dbpaiemobile.REFERENCE_CLIENT_COMPTE,
                Dbpaiemobile.MONTANT_CHARGE_CLIENT,
                Dbpaiemobile.DATE_CHARGE_CLIENT,
                Dbpaiemobile.SYNC_STATUT_CHARGECLIENT
        };
        return (database.query(Dbpaiemobile.TABLE_NAME_CHARGECOMPTECLIENT,projection,null,null,null,null,null));
    }

    public void updateLocalDatabase_chargeclient(int codechargeclient,int sync_status)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE "+Dbpaiemobile.TABLE_NAME_CHARGECOMPTECLIENT+" SET "+Dbpaiemobile.SYNC_STATUT_CHARGECLIENT+" = ? WHERE "+Dbpaiemobile.CODE_CHARGECLIENT+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindDouble(1,(double)sync_status);
        statement.bindDouble(2,(double)codechargeclient);
        statement.execute();
        database.close();
    }





//==================================================================================================================================================================================
     // POUR LE CHARGEMENT DES COMPTES DES BUS


    public void saveTolocalDatabase_charge(int referencebus,Double montantcharge,String datesavecharge,int sync_status, SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbpaiemobile.REFERENCE_BUS,referencebus);
        contentValues.put(Dbpaiemobile.MONTANT_CHARGE,montantcharge);
        contentValues.put(Dbpaiemobile.DATE_CHARGE,datesavecharge);
        contentValues.put(Dbpaiemobile.SYNC_STATUT_CHARGE,sync_status);
        database.insert(Dbpaiemobile.TABLE_NAME_CHARGECOMPTEBUS,null,contentValues);
    }
    public Cursor readFromLocalDatabase_charge(SQLiteDatabase database)
    {
        String[] projection={
                Dbpaiemobile.CODE_CHARGE,
                Dbpaiemobile.REFERENCE_BUS,
                Dbpaiemobile.MONTANT_CHARGE,
                Dbpaiemobile.DATE_CHARGE,
                Dbpaiemobile.SYNC_STATUT_CHARGE
        };
        return (database.query(Dbpaiemobile.TABLE_NAME_CHARGECOMPTEBUS,projection,null,null,null,null,null));
    }

    public void updateLocalDatabase_charge(int codecomptebus,int sync_status)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE "+Dbpaiemobile.TABLE_NAME_CHARGECOMPTEBUS+" SET "+Dbpaiemobile.SYNC_STATUT_CHARGE+" = ? WHERE "+Dbpaiemobile.CODE_CHARGE+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindDouble(1,(double)sync_status);
        statement.bindDouble(2,(double)codecomptebus);
        statement.execute();
        database.close();
    }

//============================================================================================================================================
    // POUR LE PARAMETRE DES PRIX


    public void saveTolocalDatabase_prix(Double montant,int sync_status, SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(Dbpaiemobile.MONTANT_PRIX,montant);
        contentValues.put(Dbpaiemobile.SYNC_STATUT_PRIX,sync_status);
        database.insert(Dbpaiemobile.TABLE_NAME_PARAMETREPRIX,null,contentValues);
    }
    public Cursor readFromLocalDatabase_prix(SQLiteDatabase database)
    {
        String[] projection={
                Dbpaiemobile.CODE_PARAMPRIX,
                Dbpaiemobile.MONTANT_PRIX,
                Dbpaiemobile.SYNC_STATUT_PRIX
        };
        return (database.query(Dbpaiemobile.TABLE_NAME_PARAMETREPRIX,projection,null,null,null,null,null));
    }

    public void updateLocalDatabase_prix(int codeprix,int sync_status)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE "+Dbpaiemobile.TABLE_NAME_PARAMETREPRIX+" SET "+Dbpaiemobile.SYNC_STATUT_PRIX+" = ? WHERE "+Dbpaiemobile.CODE_PARAMPRIX+" = ? ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindDouble(1,(double)sync_status);
        statement.bindDouble(2,(double)codeprix);
        statement.execute();
        database.close();
    }

//===========================================================================================================================================
    // CHAERGEMENT SPINNER

    public ArrayList<String> getAllCLient(){
        ArrayList<String> list=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM " + Dbpaiemobile.TABLE_NAME_CLIENT;
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    String nomclient=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOM_CLIENT));
                    list.add(nomclient);
                }
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return  list;
    }

    public int getCodeCLient(String name){

        int idclient=0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT "+Dbpaiemobile.CODE_CLIENT+" FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" WHERE "+Dbpaiemobile.NOM_CLIENT+"= '"+name+"' ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    idclient=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_CLIENT));
                }
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  idclient;
    }

    public ArrayList<String> getAllBus(){
        ArrayList<String> list=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM " + Dbpaiemobile.TABLE_NAME_BUS;
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    String nomclient=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NUMERO_PLAQUE_BUS));
                    list.add(nomclient);
                }
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return  list;
    }

    public Cursor getAllClientNonSync(SQLiteDatabase database){

        String[] projection={
                Dbpaiemobile.CODE_CLIENT,
                Dbpaiemobile.NOM_CLIENT,
                Dbpaiemobile.ADRESSE_CLIENT,
                Dbpaiemobile.CONTACT_CLIENT,
                Dbpaiemobile.DATE_SAVE_CLIENT,
                Dbpaiemobile.NOM_USER_CLIENT,
                Dbpaiemobile.PASSWORD_CLIENT,
                Dbpaiemobile.IMAGE_CLIENT,
                Dbpaiemobile.COMPTE_CLIENT,
                Dbpaiemobile.SYNC_STATUT_CLIENT};

        String where = Dbpaiemobile.SYNC_STATUT_CLIENT + "=?";
        String[] whereArgs = new String[]{""+Dbpaiemobile.SYNC_STATUS_FAILED};
        return (database.query(Dbpaiemobile.TABLE_NAME_CLIENT,projection,where,whereArgs,null,null,null));

    }

    public int getCodeBus(String name){

        int idbus=0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT "+Dbpaiemobile.CODE_BUS+" FROM "+Dbpaiemobile.TABLE_NAME_BUS+" WHERE "+Dbpaiemobile.NUMERO_PLAQUE_BUS+"= '"+name+"' ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    idbus=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_BUS));
                }
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  idbus;
    }
//=======================================================================================================================================
    // MODIFICATION DES COMPTES

    public Double getCompteClient(int id){

        Double Compteclient=0.0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT "+Dbpaiemobile.COMPTE_CLIENT+" FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" WHERE "+Dbpaiemobile.CODE_CLIENT+"= "+id+" ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    Compteclient=cursor.getDouble(cursor.getColumnIndex(Dbpaiemobile.COMPTE_CLIENT));
                }
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  Compteclient;
    }

    public void updateCompteclient(String idclient,Double montant,Double montantcompte,int typeoperation,SQLiteDatabase database)
    {
        if(typeoperation==1){
            ContentValues contentValues=new ContentValues();
            contentValues.put(Dbpaiemobile.COMPTE_CLIENT,(montantcompte+montant));
            String selection=Dbpaiemobile.CODE_CLIENT+" LIKE ?";
            String[] selection_args={idclient};
            database.update(Dbpaiemobile.TABLE_NAME_CLIENT,contentValues,selection,selection_args);
        }else if(typeoperation==2){
            ContentValues contentValues=new ContentValues();
            contentValues.put(Dbpaiemobile.COMPTE_CLIENT,(montantcompte-montant));
            String selection=Dbpaiemobile.CODE_CLIENT+" LIKE ?";
            String[] selection_args={idclient};
            database.update(Dbpaiemobile.TABLE_NAME_CLIENT,contentValues,selection,selection_args);
        }
    }



    public Double getCompteBus(int id){

        Double Comptebus=0.0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT "+Dbpaiemobile.COMPTE_BUS+" FROM "+Dbpaiemobile.TABLE_NAME_BUS+" WHERE "+Dbpaiemobile.CODE_BUS+"= "+id+" ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    Comptebus=cursor.getDouble(cursor.getColumnIndex(Dbpaiemobile.COMPTE_BUS));
                }
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  Comptebus;
    }

    public void updateComptebus(String idbus,Double montant,Double montantcompte,int typeoperation,SQLiteDatabase database)
    {
        if(typeoperation==1){
            ContentValues contentValues=new ContentValues();
            contentValues.put(Dbpaiemobile.COMPTE_BUS,(montantcompte+montant));
            String selection=Dbpaiemobile.CODE_BUS+" LIKE ?";
            String[] selection_args={idbus};
            database.update(Dbpaiemobile.TABLE_NAME_BUS,contentValues,selection,selection_args);
        }else if(typeoperation==2){
            ContentValues contentValues=new ContentValues();
            contentValues.put(Dbpaiemobile.COMPTE_BUS,(montantcompte-montant));
            String selection=Dbpaiemobile.CODE_BUS+" LIKE ?";
            String[] selection_args={idbus};
            database.update(Dbpaiemobile.TABLE_NAME_BUS,contentValues,selection,selection_args);
        }
    }
//=====================================================================================================================================
    // LES LOGIN

    public int testeloginclient(String name,String password){

        int teste=0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" WHERE "+Dbpaiemobile.NOM_USER_CLIENT+"= '"+name+"' AND "+Dbpaiemobile.PASSWORD_CLIENT+"= '"+password+"' ";
            //String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()==1){
                teste=1;
                while(cursor.moveToNext()){
                    usersession.getInstance().setId(cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CODE_CLIENT)));
                }
            }
            else{
                teste=0;
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  teste;
    }

    public int testeloginagent(String name,String password){

        int teste=0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_AGENT+" WHERE "+Dbpaiemobile.NOM_USER_AGENT+"= '"+name+"' AND "+Dbpaiemobile.PASSWORD_AGENT+"= '"+password+"' ";
            //String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()==1){
                teste=1;
                while(cursor.moveToNext()){
                    usersession.getInstance().setNiveauAgent(cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NIVEAU_AGENT)));
                }
            }
            else{
                teste=0;
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  teste;
    }

//===================================================================================================================================================
    // TESTE CHARGEMENT CLIENT

    public ArrayList<clsclient> testechargementclient(){

        int teste=0;
        ArrayList<clsclient> list=new ArrayList<clsclient>();
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" ";
            //String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()>=1){
                teste=1;
                list.clear();
                while(cursor.moveToNext()){
                    //usersession.getInstance().setId(cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CODE_CLIENT)));
                    String id=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CODE_CLIENT));
                    String nom=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOM_CLIENT));
                    String adresse=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.ADRESSE_CLIENT));
                    String contact=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CONTACT_CLIENT));
                    String datesave=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.DATE_SAVE_CLIENT));
                    byte[] image=cursor.getBlob(cursor.getColumnIndex(Dbpaiemobile.IMAGE_CLIENT));
                    String compte=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.COMPTE_CLIENT));
                    //String compte=cursor.getString(5);
                    list.add(new clsclient(Integer.parseInt(id),nom,adresse,contact,datesave,image,Integer.parseInt(compte)));

                }
            }
            else{
                teste=0;
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  list;
    }

    clsUsersessionbus usersessionbus=new clsUsersessionbus();
    public int testeloginbus(String name,String password){

        int teste=0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_BUS+" WHERE "+Dbpaiemobile.NOMUSER_PROPRIETAIRE+" = '"+name+"' AND "+Dbpaiemobile.PASSWORD_PROPRIETAIRE+" = '"+password+"' ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()==1){
                teste=1;
                while(cursor.moveToNext()){
                    usersessionbus.getInstance().setIdbus(cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CODE_BUS)));
                }
            }
            else{
                teste=0;
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  teste;
    }

    public int testeloginchauffeur(String name,String password){

        int teste=0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_BUS+" WHERE "+Dbpaiemobile.NOMUSER_CHAUFFEUR+"= '"+name+"' AND "+Dbpaiemobile.PASSWORD_CHAUFFEUR+"= '"+password+"' ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()==1){
                teste=1;
                while(cursor.moveToNext()){
                    usersessionbus.getInstance().setIdbus(cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CODE_BUS)));

                }
            }
            else{
                teste=0;
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  teste;
    }

public  static DbHelper glos=null;

//public  static  DbHelper getInstance(){
   // if(glos==null)
       // glos=new DbHelper(t);
   // return  glos;
//}
//=======================================================================================================================================================
// RETOUR PRIX BUS

    public int retourPrixbus(){

        int prix=0;
        int teste=0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_PARAMETREPRIX+" ";
            //String selectQuery="SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" ";
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.getCount()==1){
                teste=1;
                    while(cursor.moveToNext()){
                    prix=Integer.parseInt(cursor.getString(cursor.getColumnIndex(Dbpaiemobile.MONTANT_PRIX)));
                }
            }
            else{
                teste=0;
            }
            db.setTransactionSuccessful();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return  prix;
    }

//===========================================================================================================================================
// GET DATA



    public Cursor getData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }


    public ArrayList<clsclient> getDataClient(){

        SQLiteDatabase database=getReadableDatabase();
        database.beginTransaction();
        ArrayList<clsclient> list=new ArrayList<clsclient>();
        String sql=("SELECT * FROM "+Dbpaiemobile.TABLE_NAME_CLIENT+" ");
        Cursor cursor=database.rawQuery(sql,null);
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

        return list;
    }

//=========================================================================================================================================================
//====================================================================================================================================================
//================================================================================================================================================================

    // PARTIE SYNCHRONISATION


    public boolean checkNetworkConnection(Context context)
    {
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo!=null && networkInfo.isConnected());
    }


//=================SYNCHRONISATION AGENT=================================================================================================

    public void saveToUpServer_Agent(Context context){

        final DbHelper dbHelper=new DbHelper(context);
        final SQLiteDatabase database=dbHelper.getWritableDatabase();

        Cursor cursor=dbHelper.readFromLocalDatabase_agent(database);
        if(cursor.getCount()>=1){
            while(cursor.moveToNext())
            {
                int sync_status=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.SYNC_STATUT_AGENT));
                if(sync_status==Dbpaiemobile.SYNC_STATUS_FAILED){
                    final int codeagent=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_AGENT));
                    final String nomagent=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOM_AGENT));
                    final String adresseagent=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.ADRESSE_AGENT));
                    final String contactagent=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CONTACT_AGENT));
                    final String datesaveagent=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.DATE_SAVE_AGENT));
                    final String usernameagent=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOM_USER_AGENT));
                    final String passwordagent=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.PASSWORD_AGENT));
                    final String niveauagent=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NIVEAU_AGENT));
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String Response=jsonObject.getString("response");
                                if(Response.equals("OK")){
                                    dbHelper.updateLocalDatabase_agent(codeagent,Dbpaiemobile.SYNC_STATUS_OK);
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
                            params.put("nom_agent",nomagent);
                            params.put("adresse_agent",adresseagent);
                            params.put("contact_agent",contactagent);
                            params.put("date_save_agent",datesaveagent);
                            params.put("nom_user_agent",usernameagent);
                            params.put("password_agent",passwordagent);
                            params.put("niveau",niveauagent);
                            return params;
                        }

                    }
                            ;
                    MySingleton.getInstance(context).addToRequestQue(stringRequest);

                }

                dbHelper.close();
            }

        }

    }

//=================SYNCHRONISATION BUS================================================================================================

    public void saveToUpServer_Bus(Context context){

        final DbHelper dbHelper=new DbHelper(context);
        final SQLiteDatabase database=dbHelper.getWritableDatabase();

        Cursor cursor=dbHelper.readFromLocalDatabase_bus(database);
        if(cursor.getCount()>=1){
            while(cursor.moveToNext())
            {
                int sync_status=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.SYNC_STATUT_BUS));
                if(sync_status==Dbpaiemobile.SYNC_STATUS_FAILED){
                    final int codebus=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_BUS));
                    final String numeropalque=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NUMERO_PLAQUE_BUS));
                    final String marque=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.MARQUE_BUS));
                    final String nomproprietaire=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOM_PROPRIETAIRE));
                    final String adresseproprietaire=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.ADRESSE_PROPRIETAIRE));
                    final String contactproprietaire=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CONTACT_PROPRIETAIRE));
                    final String usernameproprietaire=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOMUSER_PROPRIETAIRE));
                    final String passwordproprietaire=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.PASSWORD_PROPRIETAIRE));
                    final String nomchauffeur=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOM_CHAUFFEUR));
                    final String adressechauffeur=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.ADRESSE_CHAUFFEUR));
                    final String contactchauffeur=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.CONTACT_CHAUFFEUR));
                    final String usernamechauffeur=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.NOMUSER_CHAUFFEUR));
                    final String passwordchauffeur=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.PASSWORD_CHAUFFEUR));
                    final Double comptebus=cursor.getDouble(cursor.getColumnIndex(Dbpaiemobile.COMPTE_BUS));
                    final String datesavebus=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.DATE_SAVE_BUS));
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_BUS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String Response=jsonObject.getString("response");
                                if(Response.equals("OK")){
                                    dbHelper.updateLocalDatabase_bus(codebus,Dbpaiemobile.SYNC_STATUS_OK);
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
                            params.put("numeroplaque_bus",numeropalque);
                            params.put("marque_bus",marque);
                            params.put("nom_proprietaire",nomproprietaire);
                            params.put("adresse_proprietaire",adresseproprietaire);
                            params.put("contact_proprietaire",contactproprietaire);
                            params.put("nomuser_proprietaire",usernameproprietaire);
                            params.put("password_proprietaire",passwordproprietaire);
                            params.put("nom_chauffeur",nomchauffeur);
                            params.put("adresse_chauffeur",adressechauffeur);
                            params.put("contact_chauffeur",contactchauffeur);
                            params.put("nomuser_chauffeur",usernamechauffeur);
                            params.put("password_chauffeur",passwordchauffeur);
                            params.put("comptebus",""+comptebus);
                            params.put("date_save_bus",datesavebus);
                            return params;
                        }

                    }
                            ;
                    MySingleton.getInstance(context).addToRequestQue(stringRequest);

                }

                dbHelper.close();
            }

        }

    }


//=================SYNCHRONISATION PAIEMENT=================================================================================================

    public void saveToUpServer_PaieBus(Context context){

        final DbHelper dbHelper=new DbHelper(context);
        final SQLiteDatabase database=dbHelper.getWritableDatabase();

        Cursor cursor=dbHelper.readFromLocalDatabase_paie(database);
        if(cursor.getCount()>=1){
            while(cursor.moveToNext())
            {
                int sync_status=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.SYNC_STATUT_PAIECLIENT));
                if(sync_status==Dbpaiemobile.SYNC_STATUS_FAILED){
                    final int codepaie=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_PAIE));
                    final int referenceclient=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.REFERENCE_CLIENT));
                    final Double montant_paie=cursor.getDouble(cursor.getColumnIndex(Dbpaiemobile.MONTANT_PAIE));
                    final String date_paie=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.DATE_PAIE));
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_PAIEBUS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String Response=jsonObject.getString("response");
                                if(Response.equals("OK")){
                                    dbHelper.updateLocalDatabase_paie(codepaie,Dbpaiemobile.SYNC_STATUS_OK);
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
                            params.put("reference_client",""+referenceclient);
                            params.put("montant_paie",""+montant_paie);
                            params.put("date_paie",date_paie);
                            return params;
                        }

                    }
                            ;
                    MySingleton.getInstance(context).addToRequestQue(stringRequest);

                }

                dbHelper.close();
            }

        }

    }

//=================SYNCHRONISATION CHARGEMENT COMPTE CIENT=================================================================================================

    public void saveToUpServer_ChargeCompteClient(Context context){

        final DbHelper dbHelper=new DbHelper(context);
        final SQLiteDatabase database=dbHelper.getWritableDatabase();

        Cursor cursor=dbHelper.readFromLocalDatabase_chargeclient(database);
        if(cursor.getCount()>=1){
            while(cursor.moveToNext())
            {
                int sync_status=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.SYNC_STATUT_CHARGECLIENT));
                if(sync_status==Dbpaiemobile.SYNC_STATUS_FAILED){
                    final int codecharge=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_CHARGECLIENT));
                    final int referenceclient=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.REFERENCE_CLIENT_COMPTE));
                    final Double montant_charge=cursor.getDouble(cursor.getColumnIndex(Dbpaiemobile.MONTANT_CHARGE_CLIENT));
                    final String date_charge=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.DATE_CHARGE_CLIENT));
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_CHARGE_COMPTE_CLIENT, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String Response=jsonObject.getString("response");
                                if(Response.equals("OK")){
                                    dbHelper.updateLocalDatabase_chargeclient(codecharge,Dbpaiemobile.SYNC_STATUS_OK);
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
                            params.put("reference_client",""+referenceclient);
                            params.put("montant_charge",""+montant_charge);
                            params.put("date_charge",date_charge);
                            return params;
                        }

                    }
                            ;
                    MySingleton.getInstance(context).addToRequestQue(stringRequest);

                }

                dbHelper.close();
            }

        }

    }


    //=================SYNCHRONISATION CHARGEMENT COMPTE BUS=================================================================================================

    public void saveToUpServer_ChargeCompteBus(Context context){

        final DbHelper dbHelper=new DbHelper(context);
        final SQLiteDatabase database=dbHelper.getWritableDatabase();

        Cursor cursor=dbHelper.readFromLocalDatabase_charge(database);
        if(cursor.getCount()>=1){
            while(cursor.moveToNext())
            {
                int sync_status=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.SYNC_STATUT_CHARGE));
                if(sync_status==Dbpaiemobile.SYNC_STATUS_FAILED){
                    final int codecharge=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_CHARGE));
                    final int referencebus=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.REFERENCE_BUS));
                    final Double montant_charge=cursor.getDouble(cursor.getColumnIndex(Dbpaiemobile.MONTANT_CHARGE));
                    final String date_charge=cursor.getString(cursor.getColumnIndex(Dbpaiemobile.DATE_CHARGE));
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_CHARGE_COMPTE_BUS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String Response=jsonObject.getString("response");
                                if(Response.equals("OK")){
                                    dbHelper.updateLocalDatabase_charge(codecharge,Dbpaiemobile.SYNC_STATUS_OK);
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
                            params.put("reference_bus",""+referencebus);
                            params.put("montant_charge",""+montant_charge);
                            params.put("date_charge",date_charge);
                            return params;
                        }

                    }
                            ;
                    MySingleton.getInstance(context).addToRequestQue(stringRequest);

                }

                dbHelper.close();
            }

        }

    }

    //=================SYNCHRONISATION PARAMETRE PRIX=================================================================================================

    public void saveToUpServer_Prix(Context context){

        final DbHelper dbHelper=new DbHelper(context);
        final SQLiteDatabase database=dbHelper.getWritableDatabase();

        Cursor cursor=dbHelper.readFromLocalDatabase_prix(database);
        if(cursor.getCount()>=1){
            while(cursor.moveToNext())
            {
                int sync_status=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.SYNC_STATUT_PRIX));
                if(sync_status==Dbpaiemobile.SYNC_STATUS_FAILED){
                    final int codeprix=cursor.getInt(cursor.getColumnIndex(Dbpaiemobile.CODE_PARAMPRIX));
                    final Double montant_prix=cursor.getDouble(cursor.getColumnIndex(Dbpaiemobile.MONTANT_PRIX));
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_PRIX, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String Response=jsonObject.getString("response");
                                if(Response.equals("OK")){
                                    dbHelper.updateLocalDatabase_prix(codeprix,Dbpaiemobile.SYNC_STATUS_OK);
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
                            params.put("montant_prix",""+montant_prix);
                            return params;
                        }

                    }
                            ;
                    MySingleton.getInstance(context).addToRequestQue(stringRequest);

                }

                dbHelper.close();
            }

        }

    }

 /*Dbpaiemobile.CODE_CLIENT,
                Dbpaiemobile.NOM_CLIENT,
                Dbpaiemobile.ADRESSE_CLIENT,
                Dbpaiemobile.CONTACT_CLIENT,
                Dbpaiemobile.DATE_SAVE_CLIENT,
                Dbpaiemobile.NOM_USER_CLIENT,
                Dbpaiemobile.PASSWORD_CLIENT,
                Dbpaiemobile.IMAGE_CLIENT,
                Dbpaiemobile.COMPTE_CLIENT,
                Dbpaiemobile.SYNC_STATUT_CLIENT byte[] image=cursor.getBlob(7);*/
//=================SYNCHRONISATION CLIENT=================================================================================================

    private  String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes=outputStream.toByteArray();

        String encodeImage= Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodeImage;
    }


    public void saveToUpServer_Client(Context context){

        final DbHelper dbHelper=new DbHelper(context);
        final SQLiteDatabase database=dbHelper.getWritableDatabase();

        Cursor cursor=dbHelper.getAllClientNonSync(database);
        if(cursor.getCount()>=1){
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

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Dbpaiemobile.SERVER_URL_CLIENT, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String Response=jsonObject.getString("response");
                                if(Response.equals("OK")){
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
                    MySingleton.getInstance(context).addToRequestQue(stringRequest);

                }

                dbHelper.close();
            }

        }

    }





}
