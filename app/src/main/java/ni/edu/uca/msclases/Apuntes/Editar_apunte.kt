package ni.edu.uca.msclases.Apuntes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.msclases.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Editar_apunte : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_editar_apunte, container, false)
    }


}