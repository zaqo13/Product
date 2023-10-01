package com.example.product.Dashboard;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product.Adapter.ProfileAdapter;
import com.example.product.HelperMethod.DBHelper;
import com.example.product.HelperMethod.DBHelper_projection;
import com.example.product.Objects.ProfileItems;
import com.example.product.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recyclerViewProfile;
    ProfileAdapter profileAdapter;
    Cursor cursor;
    DBHelper dbHelper;
    View view;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler, container, false);

        recyclerViewProfile = view.findViewById(R.id.recycler_view_search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewProfile.setLayoutManager(linearLayoutManager);

        dbHelper = new DBHelper(requireContext());
        cursor = dbHelper.getAllUser();
        List<ProfileItems> profileData = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper_projection.UserContract.UserEntry.COLUMN_NAME);
            int mobileNumberIndex = cursor.getColumnIndex(DBHelper_projection.UserContract.UserEntry.COLUMN_MOBILE_NUMBER);
            int emailIndex = cursor.getColumnIndex(DBHelper_projection.UserContract.UserEntry.COLUMN_EMAIL);
            int passwordIndex = cursor.getColumnIndex(DBHelper_projection.UserContract.UserEntry.COLUMN_PASSWORD);

            do {
                String name = cursor.getString(nameIndex);
                String mobileNumber = cursor.getString(mobileNumberIndex);
                String email = cursor.getString(emailIndex);
                String password = cursor.getString(passwordIndex);

                Log.d("onCreateView", "database = " + name);

                ProfileItems profileItems = new ProfileItems(name, mobileNumber, email, password);
                profileData.add(profileItems);
            } while (cursor.moveToNext());

            cursor.close();
        }

        profileAdapter = new ProfileAdapter(profileData);
        recyclerViewProfile.setAdapter(profileAdapter);


        return view;
    }


}