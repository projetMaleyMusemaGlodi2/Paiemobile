package isig.example.glodi.paiemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import isig.example.glodi.paiemobile.Classes.DialogLogin;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;

public class LoginProprietaireActivity extends AppCompatActivity {

    private TextView btnpropsave;
    private Button btnprop;

    private EditText edtusername_proprietaire;
    private EditText edtpassword_proprietaire;
    DbHelper db=new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_proprietaire);


        btnpropsave=(TextView) findViewById(R.id.labelloginprop);
        btnprop=(Button) findViewById(R.id.btnloginprop);

        edtusername_proprietaire=(EditText) findViewById(R.id.txtusernameprop);
        edtpassword_proprietaire=(EditText) findViewById(R.id.txtpasswordprop);

        btnpropsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginProprietaireActivity.this,SaveBusActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeRight(LoginProprietaireActivity.this);
            }
        });

        btnprop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(db.testeloginbus(edtusername_proprietaire.getText().toString(),edtpassword_proprietaire.getText().toString())==1){
                    Intent intent=new Intent(LoginProprietaireActivity.this,CompteBusViewActivity.class);
                    startActivity(intent);
                    Animatoo.animateSwipeRight(LoginProprietaireActivity.this);
                }else{
                    openDialog();
                }
            }
        });



    }

    public  void openDialog(){
        DialogLogin dialog=new DialogLogin();
        dialog.show(getSupportFragmentManager(),"dialog login");
    }}
