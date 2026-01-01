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
import com.example.giftmanager.data.entities.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.VH> {
    public interface OnClick {
        void onClick(Person person);
        void onEdit(Person person);
        void onDelete(Person person);
    }

    private List<Person> list;
    private OnClick listener;

    public PersonAdapter(List<Person> list, OnClick listener){
        this.list = list;
        this.listener = listener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, int position){
        Person p = list.get(position);
        holder.name.setText(p.name);
        holder.itemView.setOnClickListener(v -> listener.onClick(p));
        holder.btnMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.inflate(R.menu.menu_card);
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.action_edit) {
                    listener.onEdit(p);
                    return true;
                } else if (id == R.id.action_delete) {
                    listener.onDelete(p);
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    @Override public int getItemCount(){ return list.size(); }

    static class VH extends RecyclerView.ViewHolder{
        TextView name;
        ImageButton btnMenu;
        VH(View v){
            super(v);
            name = v.findViewById(R.id.tvPersonName);
            btnMenu = v.findViewById(R.id.btnCardMenu);
        }
    }
}
