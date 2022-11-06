package ni.edu.uca.msclases.Horarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import ni.edu.uca.msclases.R
import ni.edu.uca.msclases.databinding.FragmentHorarioBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Horario : Fragment() {
    private lateinit var binding: FragmentHorarioBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHorarioBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEditarHorario.setOnClickListener {
            it.findNavController().navigate(R.id.editar_horario)
        }
        binding.btnAgregarHorario.setOnClickListener{
            it.findNavController().navigate(R.id.agregarHorario)
        }
    }

}