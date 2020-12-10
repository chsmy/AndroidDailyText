package com.chs.app_jetpack.bing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.chs.app_jetpack.databinding.FragmentMainBinding

class MainFragment : ViewBindingFragment<FragmentMainBinding>() {

    override fun onCreateBinding(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun FragmentMainBinding.onViewCreated() {
        textView.text = "MainFragment"
        textView.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked.", Toast.LENGTH_SHORT).show()
        }
    }

}