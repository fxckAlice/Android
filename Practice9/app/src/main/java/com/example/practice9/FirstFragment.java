package com.example.practice9;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FirstFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_first,
                container,
                false
        );

        Button button = view.findViewById(R.id.buttonChange);

        button.setOnClickListener(v ->
                Toast.makeText(
                        getActivity(),
                        "Кнопка натиснута",
                        Toast.LENGTH_SHORT
                ).show()
        );

        return view;
    }
}