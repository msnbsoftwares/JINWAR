package com.softgen.jinwar.ui.donate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.softgen.jinwar.R;

public class DonateFragment extends Fragment {

    private DonateViewModel donateViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        donateViewModel =
                ViewModelProviders.of(this).get(DonateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_donate, container, false);
        final TextView textView = root.findViewById(R.id.donationText);
        donateViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
