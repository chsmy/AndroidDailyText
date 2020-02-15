package com.chs.androiddailytext.jetpack.navigation;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chs.androiddailytext.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    private TextView tvTitle;
    private NavViewModel viewModel;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(getArguments()==null?"":getArguments().getString("title"));
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_secondFragment_to_thirdFragment);
            }
        });
        viewModel = new ViewModelProvider(requireActivity()).get(NavViewModel.class);
        tvTitle.setText(viewModel.getParams().getValue());
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getParams().setValue("Tom");
                tvTitle.setText(viewModel.getParams().getValue());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tvTitle.setText(viewModel.getParams().getValue());
    }

}
