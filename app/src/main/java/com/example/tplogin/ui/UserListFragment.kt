package com.example.tplogin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tplogin.databinding.FragmentHomeBinding
import com.example.tplogin.databinding.FragmentUsersListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {


        val binding =
            FragmentUsersListBinding.inflate(layoutInflater, container, false)

        return binding.root}



}