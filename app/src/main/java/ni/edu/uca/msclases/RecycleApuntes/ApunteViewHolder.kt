package ni.edu.uca.msclases.apuntes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.msclases.R
import ni.edu.uca.msclases.RecycleApuntes.Apunte

class ApunteViewHolder(view:View):RecyclerView.ViewHolder(view){

    val Titulo = view.findViewById<TextView>(R.id.etTitulo)
    val Descripcion = view.findViewById<TextView>(R.id.etDescripcion)

    fun render(apunte_: Apunte){
        Titulo.text = apunte_.Titulo
        Descripcion.text = apunte_.Descripcion

    }
}