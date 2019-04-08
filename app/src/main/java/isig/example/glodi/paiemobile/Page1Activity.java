package isig.example.glodi.paiemobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.View;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;


public class Page1Activity extends AppCompatActivity {

    private android.support.v7.widget.CardView  btnagent;
    private android.support.v7.widget.CardView  btnclient;
    private android.support.v7.widget.CardView  btnproprietaire;
    private android.support.v7.widget.CardView  btnchauffeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        btnagent=(CardView) findViewById(R.id.formagent);
        btnclient=(CardView) findViewById(R.id.fromclient);
        btnproprietaire=(CardView) findViewById(R.id.fromproprietaire);
        btnchauffeur=(CardView) findViewById(R.id.fromchauffeur);


        btnagent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Page1Activity.this,LoginAgentActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                Animatoo.animateSpin(Page1Activity.this);
            }
        });

        btnclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Page1Activity.this,LoginClientActivity.class);
                startActivity(intent);
                Animatoo.animateSpin(Page1Activity.this);
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        btnproprietaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Page1Activity.this,LoginProprietaireActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                Animatoo.animateSpin(Page1Activity.this);
            }
        });

        btnchauffeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Page1Activity.this,LoginChauffeurActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                Animatoo.animateSpin(Page1Activity.this);
            }
        });




    }


}
