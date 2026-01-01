package com.example.giftmanager.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.giftmanager.R;
import com.example.giftmanager.data.AppDatabase;
import com.example.giftmanager.data.entities.Person;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPersonFragment extends Fragment {
    private EditText etName, etBirthday;
    private final Calendar calendar = Calendar.getInstance();
    private static final String ARG_PERSON_ID = "personId";
    private Integer personId = -1;
    private Person personToEdit;

    public AddPersonFragment() { }
    
    public static AddPersonFragment newInstance(int personId) {
        AddPersonFragment f = new AddPersonFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PERSON_ID, personId);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().containsKey(ARG_PERSON_ID)) {
                personId = getArguments().getInt(ARG_PERSON_ID);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_person, container, false);
        etName = v.findViewById(R.id.etPersonName);
        etBirthday = v.findViewById(R.id.etBirthday);

        if (personId != null) {
            loadPerson(personId);
        }

        Button btnSave = v.findViewById(R.id.btnSavePerson);
        etBirthday.setOnClickListener(view -> showDatePicker());
        btnSave.setOnClickListener(view -> savePerson());
        return v;
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                requireContext(),
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    calendar.set(Calendar.YEAR, selectedYear);
                    calendar.set(Calendar.MONTH, selectedMonth);
                    calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    etBirthday.setText(sdf.format(calendar.getTime()));
                },
                year, month, day
        );
        dialog.show();
    }

    private void loadPerson(int personId) {
        AppDatabase db = AppDatabase.getInstance(requireContext());
        personToEdit = db.personDao().getById(personId);

        if (personToEdit != null) {
            etName.setText(personToEdit.name);
            etBirthday.setText(personToEdit.birthday);
        }
    }

    private void savePerson(){
        String name = etName.getText().toString().trim();
        String birthday = etBirthday.getText().toString().trim();
        if(name.isEmpty()) return;
        AppDatabase db = AppDatabase.getInstance(getContext());
        db.personDao().insert(new Person(name, birthday));
        getParentFragmentManager().popBackStack();
    }
}
