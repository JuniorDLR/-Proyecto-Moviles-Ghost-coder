package ni.edu.uca.msclases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import ni.edu.uca.msclases.databinding.FragmentSplashBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Splash : Fragment() {

    private lateinit var fbinding: FragmentSplashBinding

    val usuario = "User"
    val contrasena = "123"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fbinding = FragmentSplashBinding.inflate(layoutInflater)
        iniciar()
        return fbinding.root

    }

    private fun iniciar() {


        fbinding.btnIniciar.setOnClickListener {
            val user = fbinding.etUser.text.toString()
            val pass = fbinding.etPass.text.toString()
            Validarcredenciales(user,pass)
        }
    }


    private fun Validarcredenciales(a: String, b: String) {


        val si = "Bienvenido a Mis Clases!"
        val no = "La contraseña o el usuario es incorrecto"
        val duration = Toast.LENGTH_LONG


        if ((usuario == a) && (contrasena == b)) {
            Navigation.findNavController(fbinding.root).navigate(R.id.menu)
            val toast = Toast.makeText(context, si, duration)
            toast.show()
            limpiar()


        } else {

            val toast = Toast.makeText(context, no, duration)
            toast.show()
        }
    }

    private fun limpiar(){
        fbinding.etPass.setText("")
        fbinding.etUser.setText("");
    }
}