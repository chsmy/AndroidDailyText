package com.chs.app_jetpack.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.chs.app_jetpack.R;
import com.chs.app_jetpack.ui.camera.CameraActivity;
import com.chs.app_jetpack.ui.camera.CameraViewActivity;
import com.chs.lib_navannotation.FragmentDestination;

@FragmentDestination(pageUrl = "main/tabs/home",asStarter = true)
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_take).setOnClickListener(v -> {
            CameraActivity.start(requireActivity());
        });
        view.findViewById(R.id.btn_camera_view).setOnClickListener(v ->{
            Intent intent = new Intent(requireContext(), CameraViewActivity.class);
            startActivity(intent);
        });
    }

}
