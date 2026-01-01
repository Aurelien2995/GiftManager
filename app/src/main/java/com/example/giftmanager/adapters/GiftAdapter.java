package com.example.giftmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.giftmanager.R;
import com.example.giftmanager.data.entities.GiftIdea;

import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.VH> {
    public interface OnClick {
        void onClick(GiftIdea gift);
        void onEdit(GiftIdea gift);
        void onDelete(GiftIdea gift);
    }

    private List<GiftIdea> list;
    private OnClick listener;

    public GiftAdapter(List<GiftIdea> list, OnClick listener){
        this.list = list;
        this.listener = listener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position){
        GiftIdea g = list.get(position);
        holder.title.setText(g.title + (g.offered ? " (offert)" : ""));
        holder.desc.setText(g.description);
        holder.itemView.setOnClickListener(v -> listener.onClick(g));
        holder.btnMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.inflate(R.menu.menu_card);
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.action_edit) {
                    listener.onEdit(g);
                    return true;
                } else if (id == R.id.action_delete) {
                    listener.onDelete(g);
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    @Override public int getItemCount(){ return list.size(); }

    static class VH extends RecyclerView.ViewHolder{
        TextView title, desc;
        ImageButton btnMenu;
        VH(View v){
            super(v);
            title = v.findViewById(R.id.tvGiftTitle);
            desc = v.findViewById(R.id.tvGiftDesc);
            btnMenu = v.findViewById(R.id.btnCardMenu);
        }
    }
}
