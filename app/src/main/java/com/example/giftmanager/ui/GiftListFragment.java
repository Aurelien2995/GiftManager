package com.example.giftmanager.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.giftmanager.R;
import com.example.giftmanager.data.AppDatabase;
import com.example.giftmanager.data.entities.GiftIdea;
import com.example.giftmanager.adapters.GiftAdapter;

import java.util.List;

public class GiftListFragment extends Fragment {
    private static final String ARG_PERSON_ID = "personId";
    private static final String ARG_PERSON_NAME = "personName";

    private int personId;
    private String personName;

    public static GiftListFragment newInstance(int personId, String personName){
        GiftListFragment f = new GiftListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PERSON_ID, personId);
        args.putString(ARG_PERSON_NAME, personName);
        f.setArguments(args);
        return f;
    }

    private RecyclerView recyclerView;

    public GiftListFragment(){ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            personId = getArguments().getInt(ARG_PERSON_ID);
            personName = getArguments().getString(ARG_PERSON_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_gift_list, container, false);
        recyclerView = v.findViewById(R.id.recyclerGifts);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        FloatingActionButton fab = v.findViewById(R.id.fabAddGift);
        fab.setOnClickListener(view -> {
            AddGiftFragment frag = AddGiftFragment.newInstance(personId);
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, frag)
                    .addToBackStack(null)
                    .commit();
        });

        updateToolbarTitle(personName);
        loadGifts();
        return v;
    }

    private void loadGifts(){
        AppDatabase db = AppDatabase.getInstance(getContext());
        List<GiftIdea> gifts = db.giftDao().getForPerson(personId);
        GiftAdapter.OnClick listener = new GiftAdapter.OnClick() {
            @Override
            public void onClick(GiftIdea gift) {
                gift.offered = !gift.offered;
                db.giftDao().update(gift);
                loadGifts();
            }

            @Override
            public void onEdit(GiftIdea gift) {
                Fragment fragment = AddGiftFragment.newInstanceForEdition(gift.id);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onDelete(GiftIdea gift) {
                db.giftDao().delete(gift);
                loadGifts();
            }
        };
        GiftAdapter adapter = new GiftAdapter(gifts, listener);
        recyclerView.setAdapter(adapter);
    }

    private void updateToolbarTitle(String name) {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(name);
        }
    }
}
