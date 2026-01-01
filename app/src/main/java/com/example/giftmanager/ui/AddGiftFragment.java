package com.example.giftmanager.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.giftmanager.R;
import com.example.giftmanager.data.AppDatabase;
import com.example.giftmanager.data.entities.GiftIdea;

public class AddGiftFragment extends Fragment {
    private static final String ARG_PERSON_ID = "personId";
    private static final String ARG_GIFT_ID = "giftId";

    private Integer giftId = null;
    private int personId;

    private EditText etTitle, etDesc, etPrice, etLink;
    private Spinner spOccasion;
    private GiftIdea giftToEdit;

    public static AddGiftFragment newInstance(int personId){
        AddGiftFragment f = new AddGiftFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PERSON_ID, personId);
        f.setArguments(args);
        return f;
    }

    public static AddGiftFragment newInstanceForEdition(int giftId){
        AddGiftFragment f = new AddGiftFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_GIFT_ID, giftId);
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
            if (getArguments().containsKey(ARG_GIFT_ID)) {
                giftId = getArguments().getInt(ARG_GIFT_ID);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_add_gift, container, false);
        etTitle = v.findViewById(R.id.etGiftTitle);
        etDesc = v.findViewById(R.id.etGiftDesc);
        etPrice = v.findViewById(R.id.etGiftPrice);
        etLink = v.findViewById(R.id.etGiftLink);
        spOccasion = v.findViewById(R.id.spOccasion);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.occasions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOccasion.setAdapter(adapter);

        if (giftId != null) {
            loadGift(giftId);
        }

        Button btnSave = v.findViewById(R.id.btnSaveGift);
        btnSave.setOnClickListener(view -> saveGift());
        return v;
    }

    private void loadGift(int id) {
        AppDatabase db = AppDatabase.getInstance(requireContext());
        new Thread(() -> {
            giftToEdit = db.giftDao().getById(id);
            if (giftToEdit != null) {
                requireActivity().runOnUiThread(this::fillGiftData);
            }
        }).start();
    }

    private void fillGiftData() {
        etTitle.setText(giftToEdit.title);
        etDesc.setText(giftToEdit.description);
        etPrice.setText(String.valueOf(giftToEdit.price));
        etLink.setText(giftToEdit.link);
        ArrayAdapter adapter = (ArrayAdapter) spOccasion.getAdapter();
        int position = adapter.getPosition(giftToEdit.occasion);
        if (position >= 0) spOccasion.setSelection(position);
    }

    private void saveGift(){
        String title = etTitle.getText().toString().trim();
        if (title.isEmpty()) {
            etTitle.setError("Le titre est obligatoire");
            return;
        }
        String desc = etDesc.getText().toString().trim();
        double price = 0;
        try {
            price = Double.parseDouble(etPrice.getText().toString());
        } catch (Exception ignored) {}
        String link = etLink.getText().toString().trim();
        String occ = spOccasion.getSelectedItem().toString();
        AppDatabase db = AppDatabase.getInstance(getContext());
        if (giftToEdit != null) {
            giftToEdit.title = title;
            giftToEdit.description = desc;
            giftToEdit.price = price;
            giftToEdit.link = link;
            giftToEdit.occasion = occ;
            db.giftDao().update(giftToEdit);
        } else {
            GiftIdea gift = new GiftIdea(personId, title, desc, price, link, occ, false);
            db.giftDao().insert(gift);
        }
        getParentFragmentManager().popBackStack();
    }
}
