package ni.edu.uca.msclases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.msclases.apuntes.ApunteAdapter
import ni.edu.uca.msclases.apuntes.ApunteProvider
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
        iniciar()
        initRecyclerView()
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


    private fun initRecyclerView(){
        val recycler =fbinding.recycleViewApunte.findViewById<RecyclerView>(R.id.recycleViewApunte)
        recycler.layoutManager = LinearLayoutManager(fbinding.recycleViewApunte.context)
        recycler.adapter = ApunteAdapter(ApunteProvider.listApunte)
    }


}