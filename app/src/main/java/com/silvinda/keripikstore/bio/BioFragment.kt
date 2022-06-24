package com.silvinda.keripikstore.bio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.silvinda.keripikstore.databinding.FragmentBioDataBinding

class BioFragment : Fragment() {
    private var _binding: FragmentBioDataBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBioDataBinding.inflate(inflater, container, false)
        return binding.root
    }
}