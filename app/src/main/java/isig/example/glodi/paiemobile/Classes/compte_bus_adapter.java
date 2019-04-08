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

public class compte_bus_adapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<clsBus> buslist;

    public compte_bus_adapter(Context context, int layout,ArrayList<clsBus> bus) {
        this.context=context;
        this.layout=layout;
        this.buslist=bus;
    }


    @Override
    public int getCount() {
        return buslist.size();
    }

    @Override
    public Object getItem(int position) {
        return buslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtnumPalque,txtMarque,txtProprietaire,txtChauffeur,txtcompte,txtlabelbus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        compte_bus_adapter.ViewHolder holder=new compte_bus_adapter.ViewHolder();
        if(row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            holder.txtnumPalque=(TextView) row.findViewById(R.id.compte_bus_numeroplaque);
            holder.txtMarque=(TextView) row.findViewById(R.id.compte_bus_maque);
            holder.txtProprietaire=(TextView) row.findViewById(R.id.compte_bus_proprietaire);
            holder.txtChauffeur=(TextView) row.findViewById(R.id.compte_bus_chauffeur);
            holder.txtcompte=(TextView) row.findViewById(R.id.compte_bus_montant);
            holder.txtlabelbus=(TextView) row.findViewById(R.id.compte_bus_labelphoto);
            row.setTag(holder);
        }else{
            holder=(compte_bus_adapter.ViewHolder) row.getTag();
        }
        clsBus bus=buslist.get(position);
        holder.txtnumPalque.setText(bus.getNumeroplaque_bus());
        holder.txtMarque.setText(bus.getMarque_bus());
        holder.txtProprietaire.setText(bus.getNom_proprietaire());
        holder.txtcompte.setText(""+bus.getCompte_bus());
        holder.txtChauffeur.setText(bus.getNom_chauffeur());
        holder.txtlabelbus.setText(bus.getNumeroplaque_bus());

        return row;
    }
}
