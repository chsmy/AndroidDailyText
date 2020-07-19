package com.chs.app_jetpack.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chs.app_jetpack.R
import com.chs.app_jetpack.ui.motionlayout.MotionLayoutActivity
import com.chs.lib_navannotation.FragmentDestination
import kotlinx.android.synthetic.main.fragment_notifications.*

@FragmentDestination(pageUrl = "main/tabs/my")
class NotificationsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        motion1.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout)
        }
        motion2.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout1)
        }
        motion3.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout2)
        }
        motion4.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout3)
        }
        motion5.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout4)
        }
        motion6.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout5)
        }
        motion7.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout6)
        }
        motion8.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout7)
        }
        motion9.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout8)
        }
        motion10.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout9)
        }
        motion11.setOnClickListener {
            MotionLayoutActivity.start(requireContext(),R.layout.activity_motion_layout10)
        }
    }
}