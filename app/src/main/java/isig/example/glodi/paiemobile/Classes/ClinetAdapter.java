package isig.example.glodi.paiemobile.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import isig.example.glodi.paiemobile.ListClientFragment;
import isig.example.glodi.paiemobile.R;

import java.util.ArrayList;

public class ClinetAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<clsclient> clientlist;

    public  static ClinetAdapter glos;

    public ClinetAdapter(Context context, int layout,ArrayList<clsclient> client) {
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
        TextView txtName,txtadresse,txtcontact,txtcompte;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        ViewHolder holder=new ViewHolder();
        if(row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            holder.txtName=(TextView) row.findViewById(R.id.client_nom_view);
            holder.txtadresse=(TextView) row.findViewById(R.id.client_adresse_view);
            holder.txtcontact=(TextView) row.findViewById(R.id.client_contact_view);
            holder.txtcompte=(TextView) row.findViewById(R.id.client_compte_view);
            holder.imageView=(ImageView) row.findViewById(R.id.client_imageView);

            row.setTag(holder);
        }else{
            holder=(ViewHolder) row.getTag();
        }
        clsclient client=clientlist.get(position);
        holder.txtName.setText(client.getNomclient());
        holder.txtadresse.setText(client.getAdresseclient());
        holder.txtcontact.setText(client.getContactclient());
        holder.txtcompte.setText(""+client.getCompteclient());

        byte[] clientImage=client.getImage();
        Bitmap bitmap=BitmapFactory.decodeByteArray(clientImage,0,clientImage.length);
        holder.imageView.setImageBitmap(bitmap);



        return row;
    }
}
