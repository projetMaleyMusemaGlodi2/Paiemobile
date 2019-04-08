package isig.example.glodi.paiemobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import isig.example.glodi.paiemobile.Classes.Dbpaiemobile;
import isig.example.glodi.paiemobile.DbHelpper.DbHelper;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AgentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgentFragment newInstance(String param1, String param2) {
        AgentFragment fragment = new AgentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Calendar calendar=Calendar.getInstance();
        String currentDate=DateFormat.getDateInstance().format(calendar.getTime());
        //btn_save_agent
        View view=inflater.inflate(R.layout.fragment_agent, container, false);
        btnsaveagent=view.findViewById(R.id.btn_save_agent);
        agent_nomagent=view.findViewById(R.id.txtagentnomsave);
        agent_adresseagent=view.findViewById(R.id.txtagentadresse);
        agent_contactagent=view.findViewById(R.id.txtagentcontactsave);
        agent_usernameagent=view.findViewById(R.id.txtagentusernamesave);
        agent_passwordagent=view.findViewById(R.id.txtagentpasswordsave);
        agent_date_save=""+currentDate.toString();
        agent_niveau=view.findViewById(R.id.txtagentniveausave);
        agent_sync=Dbpaiemobile.SYNC_STATUS_FAILED;

        enregistremenmtagent();

        return  view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private Button btnsaveagent;
    private EditText agent_nomagent;
    private EditText agent_adresseagent;
    private EditText agent_contactagent;
    private String agent_date_save;
    private EditText agent_usernameagent;
    private EditText agent_passwordagent;
    private Integer agent_sync ;
    private EditText agent_niveau;


    void enregistremenmtagent(){

        btnsaveagent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ActivityCompat.requestPermissions(SaveClientActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);*/
                try{
                    saveToLocalStorage(
                            agent_nomagent.getText().toString(),
                            agent_adresseagent.getText().toString(),
                            agent_contactagent.getText().toString(),
                            agent_usernameagent.getText().toString(),
                            agent_passwordagent.getText().toString(),
                            agent_date_save,
                            agent_niveau.getText().toString(),
                            agent_sync);

                }catch (Exception e){
                    e.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_SHORT);

            }
        });
    }


    private void saveToLocalStorage(String nomagent, String adresseagent, String contactagent,
                                    String usernameagent,String passwordagent, String datesave,String niveau, int sync_status)
    {
        try{
            DbHelper dbHelper=new DbHelper(getActivity());
            SQLiteDatabase database=dbHelper.getWritableDatabase();
            dbHelper.saveTolocalDatabase_agent(nomagent,adresseagent,contactagent,usernameagent,passwordagent,datesave, niveau,sync_status,database);
            dbHelper.close();

        }catch (Exception e){
            e.printStackTrace();

        }
    }





}
