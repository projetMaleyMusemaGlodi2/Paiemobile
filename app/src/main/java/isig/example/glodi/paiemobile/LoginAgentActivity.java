package isig.example.glodi.paiemobile;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import isig.example.glodi.paiemobile.Classes.DialogLogin;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;

public class LoginAgentActivity extends AppCompatActivity {

    private EditText edtusername_agent;
    private EditText edtpassword_agent;
    private Button btnagent;
    private ImageView imgprofil;

    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_agent);
        dbHelper=new DbHelper(this);

        btnagent=(Button) findViewById(R.id.btnloginagent);
        edtusername_agent=(EditText) findViewById(R.id.txtusernameagent);
        edtpassword_agent=(EditText) findViewById(R.id.txtpasswordagent);
        imgprofil=(ImageView) findViewById(R.id.imgprofilloginagent);

        btnagent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {


                if(dbHelper.testeloginagent(edtusername_agent.getText().toString(),edtpassword_agent.getText().toString())==1){
                    try{
                        Intent intent=new Intent(LoginAgentActivity.this,AcceuilEseActivity.class);
                        startActivity(intent);
                        Animatoo.animateSwipeRight(LoginAgentActivity.this);
                    }catch (Exception ex){}
                }else{
                    openDialog();
                }

            }
        });

    }

    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "";
    }
    public  void openDialog(){
        DialogLogin dialog=new DialogLogin();
        dialog.show(getSupportFragmentManager(),"dialog login");
    }
}
