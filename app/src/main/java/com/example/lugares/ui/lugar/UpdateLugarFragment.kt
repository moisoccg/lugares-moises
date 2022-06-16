package com.example.lugares.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lugares.R
import com.example.lugares.databinding.FragmentAddLugarBinding
import com.example.lugares.databinding.FragmentUpdateLugarBinding
import com.example.lugares.model.Lugar
import com.example.lugares.viewmodel.LugarViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateLugarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateLugarFragment : Fragment() {
    private var _binding: FragmentUpdateLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel: LugarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateLugarBinding.inflate(inflater,container,false)

        lugarViewModel = ViewModelProvider(this).get(LugarViewModel::class.java)

        binding.btnUpdate.setOnClickListener {
            updateLugar()
        }

        return binding.root
    }

    private fun updateLugar(){
        val nombre = binding.lugarName.text.toString()

        if(validation(nombre)){
            //guardar lugar
            val lugar = Lugar(0,nombre)
            lugarViewModel.updateLuggar(lugar) //enviando a la bd
            Toast.makeText(requireContext(), getString(R.string.msg_success), Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(requireContext(), getString(R.string.msg_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun validation(nombre: String): Boolean {
        return !(nombre.isEmpty())
    }
}