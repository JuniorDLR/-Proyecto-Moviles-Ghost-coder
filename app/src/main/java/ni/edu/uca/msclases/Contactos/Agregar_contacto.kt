package ni.edu.uca.msclases.Contactos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.msclases.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Agregar_contacto : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_contacto, container, false)
    }


}