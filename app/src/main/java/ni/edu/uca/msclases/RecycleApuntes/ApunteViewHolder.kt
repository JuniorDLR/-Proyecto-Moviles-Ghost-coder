package ni.edu.uca.msclases.apuntes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.msclases.R
import ni.edu.uca.msclases.RecycleApuntes.Apunte
import ni.edu.uca.msclases.databinding.ItemApunteBinding

class ApunteViewHolder(view:View):RecyclerView.ViewHolder(view){
    val binding = ItemApunteBinding.bind(view)

    val Titulo = view.findViewById<TextView>(R.id.etTitulo)
    val Descripcion = view.findViewById<TextView>(R.id.etDescripcion)

    fun render(
        Ap:Apunte,
        onClickListener:(Apunte)->Unit,
        onClickDelete:(Int)->Unit
    ){
        binding.etTitulo.text = Ap.Titulo
        binding.etDescripcion.text=Ap.Descripcion
        binding.BorrarItem.setOnClickListener { onClickDelete(adapterPosition)}


    }
}