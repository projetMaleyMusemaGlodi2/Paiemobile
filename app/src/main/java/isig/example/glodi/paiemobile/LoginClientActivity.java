package isig.example.glodi.paiemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import isig.example.glodi.paiemobile.Classes.Dbpaiemobile;
import isig.example.glodi.paiemobile.Classes.DialogLogin;
import isig.example.glodi.paiemobile.Classes.ExampleDialog;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;

public class LoginClientActivity extends AppCompatActivity {

    private EditText edtusername_client;
    private EditText edtpassword_client;
    private TextView btnclientsave;
    private Button btnclient;
    DbHelper db=new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);


        btnclientsave=(TextView) findViewById(R.id.labelloginclient);
        btnclient=(Button) findViewById(R.id.btnloginclient);
        edtusername_client=(EditText) findViewById(R.id.txtusernameclient);
        edtpassword_client=(EditText) findViewById(R.id.txtpasswordclient);

        btnclientsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginClientActivity.this,SaveClientActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeRight(LoginClientActivity.this);

            }
        });

        btnclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(db.testeloginclient(edtusername_client.getText().toString(),edtpassword_client.getText().toString())==1){
                    Intent intent=new Intent(LoginClientActivity.this,CompteClientViewActivity.class);
                    startActivity(intent);
                    Animatoo.animateSwipeRight(LoginClientActivity.this);
                }else{
                    openDialog();
                }
            }
        });

    }

    public  void openDialog(){
        DialogLogin dialog=new DialogLogin();
        dialog.show(getSupportFragmentManager(),"dialog login");
    }

}
