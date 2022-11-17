package ni.edu.uca.msclases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ni.edu.uca.msclases.databinding.ActivityMainBinding

import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import ni.edu.uca.msclases.Apuntes.Apunte
import ni.edu.uca.msclases.Apuntes.Apuntes
import ni.edu.uca.msclases.Contactos.Datos
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.io.FileInputStream
import java.io.ObjectInputStream
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    //Contactos

    // Declaro un ArrayList de objetos Datos (mismos que voy a guardar en un archivo y
    // al leer del archivo, los voy a almacenar aquí)

    private lateinit var binding: ActivityMainBinding
    var listaDatos: MutableList<Datos> = ArrayList()
    var listaApuntes: MutableList<Apuntes> = ArrayList()

    // Lista para el Spinner de nombres
    var listaNombres: MutableList<String> = ArrayList()
    var listanombres2: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //Apuntes
    //Metodo que guarda un nuevo objeto en el arraylist

    fun GuardarEnLista2(v: View?) {
        // Creo objetos Java y los vinculo con el layout
        val Titulo = findViewById<EditText>(R.id.etTitulo2)
        val Descripcion = findViewById<EditText>(R.id.etDescripcion2)
        val spinner2= findViewById<Spinner>(R.id.lstNombres1)


        // Agrego un elemento a mi ArrayList
        // (creo un nuevo objeto Datos y lo agrego)
        listaApuntes.add(
            Apuntes(
                Titulo.text.toString(),
                Descripcion.text.toString()

            )
        )

        // Muestro mensaje
        Toast.makeText(this, "Agregado a la lista", Toast.LENGTH_SHORT).show()

        // Agrego nombre al spinner
        listanombres2.add(Titulo.text.toString())
        val llenaSpinner = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listanombres2
        )
        llenaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        spinner2.adapter = llenaSpinner

        // Limpio los campos
        Titulo.setText("")
        Descripcion.setText("")

    }

    //Contactos
    // Método que guarda un nuevo objeto en el ArrayList
    fun GuardaEnLista(v: View?) {
        // Creo objetos Java y los vinculo con el layout
        val Nombre = findViewById<EditText>(R.id.etNombre)
        val Correo = findViewById<EditText>(R.id.etCorreo)
        val Telefono = findViewById<EditText>(R.id.etTelefono)
        val Ubicacion = findViewById<EditText>(R.id.etUbicacion)
        val Notas = findViewById<EditText>(R.id.etNotas)
        val lstNombres = findViewById<Spinner>(R.id.lstNombres)

        // Agrego un elemento a mi ArrayList
        // (creo un nuevo objeto Datos y lo agrego)
        listaDatos.add(
            Datos(
                Nombre.text.toString(),
                Correo.text.toString(), Telefono.text.toString().toInt(),
                Ubicacion.text.toString(),
                Notas.text.toString(),
            )
        )

        // Muestro mensaje
        Toast.makeText(this, "Agregado a la lista", Toast.LENGTH_SHORT).show()

        // Agrego nombre al spinner
        listaNombres.add(Nombre.text.toString())
        val llenaSpinner = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listaNombres
        )
        llenaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        lstNombres.adapter = llenaSpinner

        // Limpio los campos
        Nombre.setText("")
        Correo.setText("")
        Telefono.setText("")
        Ubicacion.setText("")
        Notas.setText("")
    }

    //Apuntes
    fun GuardaEnArchivo2(v: View?) {
        // El objeto File me permite acceder el archivo (en este caso, para escribir en él)
        // (obtengo la ruta donde almacenarlo; en la carpeta de la app)
        val ruta2 = applicationContext.filesDir
        // Éste es el nombre del archivo
        val nombreArch2 = "archivoP.tpo"

        // El acceso a archivo tiene que ir en un try catch por si sucede algo inesperado
        try {
            val escribirArch = FileOutputStream(File(ruta2, nombreArch2))
            // Tengo que usar un ObjectOutputStream porque el almacenamiento interno es un archivo de bytes
            // y este objeto es el que me permite convertir de objeto a byte. Si fuera un String u otra cosa,
            // bastaría escribirArch.write(lacadena.getBytes())
            // suponiendo que lacadena es un String que contiene el texto a guardar.
            val streamArch2 = ObjectOutputStream(escribirArch)
            streamArch2.writeObject(listaApuntes)
            streamArch2.close()
        } catch (e: Exception) {
            e.printStackTrace() // Si hay error, que muestre datos sobre el fallo
        }
    }

    //Contactos
    // Método que guarda la lista a un archivo interno
    fun GuardaEnArchivo(v: View?) {
        // El objeto File me permite acceder el archivo (en este caso, para escribir en él)
        // (obtengo la ruta donde almacenarlo; en la carpeta de la app)
        val ruta = applicationContext.filesDir
        // Éste es el nombre del archivo
        val nombreArch = "archivo.tpo"

        // El acceso a archivo tiene que ir en un try catch por si sucede algo inesperado
        try {
            val escribirArch = FileOutputStream(File(ruta, nombreArch))
            // Tengo que usar un ObjectOutputStream porque el almacenamiento interno es un archivo de bytes
            // y este objeto es el que me permite convertir de objeto a byte. Si fuera un String u otra cosa,
            // bastaría escribirArch.write(lacadena.getBytes())
            // suponiendo que lacadena es un String que contiene el texto a guardar.
            val streamArch = ObjectOutputStream(escribirArch)
            streamArch.writeObject(listaDatos)
            streamArch.close()
        } catch (e: Exception) {
            e.printStackTrace() // Si hay error, que muestre datos sobre el fallo
        }
    }

    //Apuntes
    fun LeeDelArchivo2(v: View?) {
        // Obtengo referencia a los controles de la pantalla que voy a usar
        val lstNombres1 = findViewById<Spinner>(R.id.lstNombres1)

        // El objeto File con la ruta donde almacenarlo
        val ruta2 = applicationContext.filesDir
        // Éste es el nombre del archivo
        val nombreArch2 = "archivo2.tpo"

        // Borro la lista y borro lo que está en el spinner (el adapter será el arreglo vacío)
        listanombres2.clear()
        var llenaSpinner2 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listanombres2
        )
        lstNombres1.adapter = llenaSpinner2

        // Leo los datos del archivo
        try {
            // FileInputStream me permite abri el archivo para leer de él
            val leeArch = FileInputStream(File(ruta2, nombreArch2))
            // El ObjectInputStream me pemite traducir el arreglo de bytes al Arraylist
            val streamArch = ObjectInputStream(leeArch)
            // Leo todo y lleno la lista
            listaApuntes = streamArch.readObject() as ArrayList<Apuntes>
            // Cierro el stream
            streamArch.close()

            // Lleno la lista de nombres (strings) con los nombres de la lista de datos
            listanombres2.clear()
            for (i in listanombres2.indices) {
                listanombres2.add(listanombres2[i])
            }
            // Lleno el Spinner de la nueva lista
            llenaSpinner2 = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, listanombres2
            )
            llenaSpinner2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            lstNombres1.adapter = llenaSpinner2
        } catch (e: Exception) {
            e.printStackTrace() // Si hay error, que muestre datos sobre el fallo
        }
    }

    //Contactos
    // Método que lee del archivo y lo pone en la lista
    fun LeeDelArchivo(v: View?) {
        // Obtengo referencia a los controles de la pantalla que voy a usar
        val lstNombres = findViewById<Spinner>(R.id.lstNombres)

        // El objeto File con la ruta donde almacenarlo
        val ruta = applicationContext.filesDir
        // Éste es el nombre del archivo
        val nombreArch = "archivo.tpo"

        // Borro la lista y borro lo que está en el spinner (el adapter será el arreglo vacío)
        listaNombres.clear()
        var llenaSpinner = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listaNombres
        )
        lstNombres.adapter = llenaSpinner

        // Leo los datos del archivo
        try {
            // FileInputStream me permite abri el archivo para leer de él
            val leeArch = FileInputStream(File(ruta, nombreArch))
            // El ObjectInputStream me pemite traducir el arreglo de bytes al Arraylist
            val streamArch = ObjectInputStream(leeArch)
            // Leo todo y lleno la lista
            listaDatos = streamArch.readObject() as ArrayList<Datos>
            // Cierro el stream
            streamArch.close()

            // Lleno la lista de nombres (strings) con los nombres de la lista de datos
            listaNombres.clear()
            for (i in listaDatos.indices) {
                listaNombres.add(listaDatos[i].nombre)
            }
            // Lleno el Spinner de la nueva lista
            llenaSpinner = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, listaNombres
            )
            llenaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            lstNombres.adapter = llenaSpinner
        } catch (e: Exception) {
            e.printStackTrace() // Si hay error, que muestre datos sobre el fallo
        }
    }

    //Apuntes
    fun MuestraDatos2(v: View?) {
        // Obtengo referencia a los controles de la pantalla que voy a usar
        val lstNombres1 = findViewById<Spinner>(R.id.lstNombres1)

        // Creo un Alert Builder (ventana estándar que puedo usar)
        val constructor2 = AlertDialog.Builder(this)
        constructor2.setTitle("Apuntes") // Pongo título a la ventana
        constructor2.setPositiveButton("Aceptar", null) // Agrego un botón

        // Si se seleccionó algo en el spinner, le sigo
        val index2 = lstNombres1.selectedItemId
        if (index2 > -1) {
            // Pongo mensaje a la ventana que voy a mostrar
            constructor2.setMessage(
                "\n * Titulo: " + listaApuntes[index2.toInt()].Titulo
                        + " \n- Descripcion: " + listaApuntes[index2.toInt()].Descripcion
            ) // El texto en la ventana
        } else {
            constructor2.setMessage("Debe seleccionar un nombre de la lista")
        }

        // Creo y muestro la ventana
        val ventanaMensaje2 = constructor2.create()
        ventanaMensaje2.show()
    }

    // Método que busca en la lista de datos y muestra información del que
    // se seleccionó en el Spinner
    fun MuestraDatos(v: View?) {
        // Obtengo referencia a los controles de la pantalla que voy a usar
        val lstNombres = findViewById<Spinner>(R.id.lstNombres)

        // Creo un Alert Builder (ventana estándar que puedo usar)
        val constructor = AlertDialog.Builder(this)
        constructor.setTitle("Contactos") // Pongo título a la ventana
        constructor.setPositiveButton("Aceptar", null) // Agrego un botón

        // Si se seleccionó algo en el spinner, le sigo
        val index = lstNombres.selectedItemId
        if (index > -1) {
            // Pongo mensaje a la ventana que voy a mostrar
            constructor.setMessage(
                "\n * Nombre: " + listaDatos[index.toInt()].nombre
                        + " \n- Correo: " + listaDatos[index.toInt()].correo
                        + " \n- Telefono: " + listaDatos[index.toInt()].telefono
                        + " \n- Ubicacion: " + listaDatos[index.toInt()].ubicacion
                        + " \n- Notas: " + listaDatos[index.toInt()].notas
            ) // El texto en la ventana
        } else {
            constructor.setMessage("Debe seleccionar un nombre de la lista")
        }

        // Creo y muestro la ventana
        val ventanaMensaje = constructor.create()
        ventanaMensaje.show()
    }





}