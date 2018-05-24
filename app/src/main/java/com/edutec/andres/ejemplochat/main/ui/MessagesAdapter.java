package com.edutec.andres.ejemplochat.main.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import com.edutec.andres.ejemplochat.R;
import com.edutec.andres.ejemplochat.entities.Message;
import com.edutec.andres.ejemplochat.lib.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andres on 12/05/2018.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder>{

    private ImageLoader imageLoader;
    private Context context;
    private List<Message> messages;

    public MessagesAdapter (ImageLoader imageLoader, Context context, List<Message> messages) {
        this.imageLoader = imageLoader;
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.mensaje.setText(message.getMensaje());
        holder.nombre.setText(message.getUser());
        if (message.getAvatar() != null) {
            imageLoader.load(holder.avatar, message.getAvatar());
        }
    }

    public void addMessage(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.nombre)
        TextView nombre;
        @BindView(R.id.mensaje)
        TextView mensaje;

        public ViewHolder(android.view.View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
