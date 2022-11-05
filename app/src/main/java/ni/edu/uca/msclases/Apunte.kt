package ni.edu.uca.msclases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ni.edu.uca.msclases.databinding.FragmentApunteBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Apunte : Fragment() {
    lateinit var fbinding: FragmentApunteBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fbinding = FragmentApunteBinding.inflate(layoutInflater)
        iniciar()
        return fbinding.root
    }

    private fun iniciar() {
        fbinding.btnizquierda.setOnClickListener {
            Navigation.findNavController(fbinding.root).navigate(R.id.menu)
        }
        fbinding.btnAgregar.setOnClickListener {
            Navigation.findNavController(fbinding.root).navigate(R.id.agregar_apunte)
        }
    }


}