package com.example.lugares.ui.lugar

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lugares.R
import com.example.lugares.databinding.FragmentUpdateLugarBinding
import com.example.lugares.model.Lugar
import com.example.lugares.viewmodel.LugarViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateLugarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateLugarFragment : Fragment() {
    private var _binding: FragmentUpdateLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel: LugarViewModel

    private val args by navArgs<UpdateLugarFragmentArgs>()

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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteLugar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateLugar(){
        val nombre = binding.lugarName.text.toString()

        if(validation(nombre)){
            //guardar lugar
            val lugar = Lugar(args.lugar.id,nombre)
            lugarViewModel.updateLuggar(lugar) //enviando a la bd
            Toast.makeText(requireContext(), getString(R.string.msg_success), Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateLugarFragment2_to_nav_lugar)
        } else{
            Toast.makeText(requireContext(), getString(R.string.msg_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun validation(nombre: String): Boolean {
        return !(nombre.isEmpty())
    }

    private fun deleteLugar(){
       val builder = AlertDialog.Builder(requireContext())
       builder.setPositiveButton(getString(R.string.yes)) {
           _,_ -> lugarViewModel.deleteLuggar(args.lugar)
           Toast.makeText(requireContext(), getString(R.string.delete_success) +"a ${args.lugar.nombre}!", Toast.LENGTH_LONG).show()
           findNavController().navigate(R.id.action_updateLugarFragment2_to_nav_lugar)
       }
        builder.setNegativeButton(getString(R.string.no)) { _,_ ->}
        builder.setTitle(R.string.delete_success)
        builder.setMessage(getString(R.string.safeToDelete) + " ${args.lugar.nombre}?")
        builder.create().show()
    }
}