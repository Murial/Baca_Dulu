package com.bacadulu.bacaduluapps.fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bacadulu.bacaduluapps.R
import com.bacadulu.bacaduluapps.databinding.ActivityHomeBinding
import com.bacadulu.bacaduluapps.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    public lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root:View = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }
}