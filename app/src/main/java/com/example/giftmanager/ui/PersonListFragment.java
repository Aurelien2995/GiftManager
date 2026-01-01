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

import com.example.giftmanager.data.entities.GiftIdea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.giftmanager.R;
import com.example.giftmanager.data.AppDatabase;
import com.example.giftmanager.data.entities.Person;
import com.example.giftmanager.adapters.PersonAdapter;

import java.util.List;

public class PersonListFragment extends Fragment {
    private RecyclerView recyclerView;
    private PersonAdapter adapter;

    public PersonListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_person_list, container, false);
        recyclerView = v.findViewById(R.id.recyclerPersons);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        FloatingActionButton fab = v.findViewById(R.id.fabAddPerson);
        fab.setOnClickListener(view -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new AddPersonFragment())
                    .addToBackStack(null)
                    .commit();
        });

        updateToolbarTitle();
        loadPersons();
        return v;
    }

    private void loadPersons(){
        AppDatabase db = AppDatabase.getInstance(getContext());
        List<Person> people = db.personDao().getAll();
        PersonAdapter.OnClick listener = new PersonAdapter.OnClick() {
            @Override
            public void onClick(Person person) {
                GiftListFragment frag = GiftListFragment.newInstance(person.id, person.name);
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, frag)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onEdit(Person person) {
                AddPersonFragment fragment = AddPersonFragment.newInstance(person.id);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onDelete(Person person) {
                db.personDao().delete(person);
                loadPersons();

            }
        };
        adapter = new PersonAdapter(people,listener);
        recyclerView.setAdapter(adapter);
    }

    private void updateToolbarTitle() {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(R.string.app_name);
        }
    }
}
