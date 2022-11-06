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
import ni.edu.uca.msclases.RecycleApuntes.Apunte
import ni.edu.uca.msclases.apuntes.ApunteAdapter
import ni.edu.uca.msclases.apuntes.ApunteProvider
import ni.edu.uca.msclases.databinding.FragmentApunteBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Apunte : Fragment() {
    lateinit var fbinding: FragmentApunteBinding

    var ApunteMutablelist:MutableList<Apunte> = ApunteProvider.listApunte.toMutableList()
    private lateinit var adapter:ApunteAdapter



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
          adapter = ApunteAdapter(
          listaApunte = ApunteMutablelist,
              onClickListener = {Apunte->onItemSelected(Apunte)},
              onClickDelete = {position->onDeletedItem(position)}

          )
        val manager = LinearLayoutManager(context)
        //val decoration =DividerItemDecoration(context,manager.orientation)
        fbinding.recycleViewApunte.layoutManager = manager
        fbinding.recycleViewApunte.adapter = adapter

    }

    private fun onDeletedItem(position: Int){
        ApunteMutablelist.removeAt(position)
        adapter.notifyItemRemoved(position)


    }
    private fun onItemSelected(ap:Apunte){
        Toast.makeText(context,ap.Titulo,Toast.LENGTH_LONG).show()

}


}