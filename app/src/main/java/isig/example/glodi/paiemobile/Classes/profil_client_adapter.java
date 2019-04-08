package isig.example.glodi.paiemobile.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import isig.example.glodi.paiemobile.R;

import java.util.ArrayList;

public class profil_client_adapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<clsclient> clientlist;

    public profil_client_adapter(Context context, int layout,ArrayList<clsclient> client) {
        this.context=context;
        this.layout=layout;
        this.clientlist=client;
    }

    @Override
    public int getCount() {
        return clientlist.size();
    }

    @Override
    public Object getItem(int position) {
        return clientlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName,txtadresse,txtcontact,txtcompte,txtdatesave,txtNamelabel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        profil_client_adapter.ViewHolder holder=new profil_client_adapter.ViewHolder();
        if(row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            holder.txtName=(TextView) row.findViewById(R.id.compte_client_name);
            holder.txtadresse=(TextView) row.findViewById(R.id.compte_client_adresse);
            holder.txtcontact=(TextView) row.findViewById(R.id.compte_client_contact);
            holder.txtcompte=(TextView) row.findViewById(R.id.compte_client_compte);
            holder.imageView=(ImageView) row.findViewById(R.id.compte_client_photo);
            holder.txtdatesave=(TextView) row.findViewById(R.id.compte_client_datesave);
            holder.txtNamelabel=(TextView) row.findViewById(R.id.compte_labelprofilclient);
            row.setTag(holder);
        }else{
            holder=(profil_client_adapter.ViewHolder) row.getTag();
        }
        clsclient client=clientlist.get(position);
        holder.txtName.setText(client.getNomclient());
        holder.txtadresse.setText(client.getAdresseclient());
        holder.txtcontact.setText(client.getContactclient());
        holder.txtcompte.setText(""+client.getCompteclient());
        holder.txtdatesave.setText(""+client.getDatesave());
        holder.txtNamelabel.setText(client.getNomclient());
        byte[] clientImage=client.getImage();
        Bitmap bitmap=BitmapFactory.decodeByteArray(clientImage,0,clientImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
