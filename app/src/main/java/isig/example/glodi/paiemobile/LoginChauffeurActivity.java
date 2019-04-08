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

public class LoginChauffeurActivity extends AppCompatActivity {

    private Button btnchauffeur;

    private TextView btnpropsave;
    private Button btnprop;

    private EditText edtusername_proprietaire;
    private EditText edtpassword_proprietaire;
    DbHelper db=new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_chauffeur);

        btnchauffeur=(Button) findViewById(R.id.btnloginchauffeur);

        edtusername_proprietaire=(EditText) findViewById(R.id.txtusernamechauffeur);
        edtpassword_proprietaire=(EditText) findViewById(R.id.txtpasswordchauffeur);

        btnchauffeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.testeloginchauffeur(edtusername_proprietaire.getText().toString(),edtpassword_proprietaire.getText().toString())==1){
                    Intent intent=new Intent(LoginChauffeurActivity.this,CompteBusViewActivity.class);
                    startActivity(intent);
                    Animatoo.animateSwipeRight(LoginChauffeurActivity.this);
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
