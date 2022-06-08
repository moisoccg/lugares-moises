package com.example.lugares.ui.lugar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lugares.R
import com.example.lugares.databinding.FragmentLugarBinding
import com.example.lugares.viewmodel.LugarViewModel

class LugarFragment : Fragment() {

    private var _binding: FragmentLugarBinding? = null
    private lateinit var lugarViewModel: LugarViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         lugarViewModel =
            ViewModelProvider(this).get(LugarViewModel::class.java)

        _binding = FragmentLugarBinding.inflate(inflater, container, false)

        //binds to fragment_lugar.xml

        binding.btnAddLugar.setOnClickListener {
            findNavController().navigate(R.id.action_nav_lugar_to_addLugarFragment3) //take meto  fragment
        }
        return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}