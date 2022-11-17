package ni.edu.uca.msclases.Apuntes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.msclases.R

import ni.edu.uca.msclases.databinding.FragmentApunteBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Apunte : Fragment() {
    lateinit var fbinding: FragmentApunteBinding





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fbinding = FragmentApunteBinding.inflate(layoutInflater)


        return fbinding.root
    }






}


