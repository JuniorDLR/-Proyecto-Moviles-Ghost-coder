package ni.edu.uca.msclases.apuntes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.msclases.R
import ni.edu.uca.msclases.RecycleApuntes.Apunte


class ApunteAdapter(private val listaApunte: List<Apunte>) : RecyclerView.Adapter<ApunteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApunteViewHolder {

        val layaotInflter = LayoutInflater.from(parent.context)
        return ApunteViewHolder(layaotInflter.inflate(R.layout.item_apunte,parent,false))

    }

    override fun onBindViewHolder(holder: ApunteViewHolder, position: Int) {

        val item = listaApunte[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =listaApunte.size

}