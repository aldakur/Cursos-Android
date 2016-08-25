package service.webservice.facilito.codigo.com.ejemplocfwebservices.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import service.webservice.facilito.codigo.com.ejemplocfwebservices.POJO.Usuario;
import service.webservice.facilito.codigo.com.ejemplocfwebservices.R;

/**
 * Created by aldakur on 23/8/16.
 */
public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder>{
    List<Usuario> usuarioList = new ArrayList<>();
    Context context;

    public UsuariosAdapter(Context context, List<Usuario> usuarioList){
        this.context = context;
        this.usuarioList = usuarioList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(usuarioList.get(position).getNombre());
        Picasso.with(context).load(usuarioList.get(position).getTwitter()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View item){
            super(item);
            title = (TextView) item.findViewById(R.id.title);
            imageView = (ImageView) item.findViewById(R.id.imageview);
            cardView = (CardView) item.findViewById(R.id.cardview);

        }

    }
}
