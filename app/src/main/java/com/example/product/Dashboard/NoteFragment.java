package com.example.product.Dashboard;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product.Adapter.NoteAdapter;
import com.example.product.HelperMethod.DBHelper;
import com.example.product.Objects.ProfileItems;
import com.example.product.R;

import java.util.ArrayList;
import java.util.List;


public class NoteFragment extends Fragment {


    RecyclerView recyclerView;
    DBHelper dbHelper;
    Cursor cursor;
    NoteAdapter noteAdapter;
    View view;


    public NoteFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_recycler, container, false);


        recyclerView = view.findViewById(R.id.recycler_view_search);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayout);


        dbHelper = new DBHelper(requireContext());
        cursor = dbHelper.getAllUser();
        List<ProfileItems> dataItems = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME);
            int mobIndex = cursor.getColumnIndex(DBHelper.COLUMN_MOBILE_NUMBER);
            int emailIndex = cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL);
            int passwordIndex = cursor.getColumnIndexOrThrow(DBHelper.COLUMN_PASSWORD);

            do {
                String name = cursor.getString(nameIndex);
                String mobNo = cursor.getString(mobIndex);
                String email = cursor.getString(emailIndex);
                String password = cursor.getString(passwordIndex);

                ProfileItems profileItems = new ProfileItems(name, mobNo, email, password);
                dataItems.add(profileItems);

            } while (cursor.moveToNext());
            cursor.close();
        }

        noteAdapter = new NoteAdapter(dataItems);
        recyclerView.setAdapter(noteAdapter);


        return view;
    }


}