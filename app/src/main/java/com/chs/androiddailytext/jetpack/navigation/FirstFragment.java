package com.chs.androiddailytext.jetpack.navigation;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chs.androiddailytext.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    private NavViewModel viewModel;
    private TextView tvTitle;

    public FirstFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tv_title);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title","我是前面传过来的");
                Navigation.findNavController(v).navigate(R.id.action_firstFragment_to_secondFragment,bundle);
            }
        });
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setData(Uri.parse("http://www.chs.com/Divad"));
//                Navigation.findNavController(v).handleDeepLink(intent);

                Bundle bundle = new Bundle();
                bundle.putString("userName","大海");
                PendingIntent pendingIntent = new NavDeepLinkBuilder(requireContext())
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.thirdFragment)
                        .setArguments(bundle)
                        .createPendingIntent();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    notificationManager.createNotificationChannel(new NotificationChannel(
                            "deepLink","deepLinkName", NotificationManager.IMPORTANCE_HIGH
                    ));
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "deepLink")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("测试deepLink")
                        .setContentText("哈哈哈")
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                notificationManager.notify(10,builder.build());
            }
        });
        viewModel = new ViewModelProvider(requireActivity()).get(NavViewModel.class);
        tvTitle.setText(viewModel.getParams().getValue());
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getParams().setValue("Rouse");
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
