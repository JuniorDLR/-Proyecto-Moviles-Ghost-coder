package ni.edu.uca.msclases.apuntes

import ni.edu.uca.msclases.RecycleApuntes.Apunte


class ApunteProvider {
    companion object{
        var listApunte = listOf<Apunte>(

            Apunte("Fisica","Prueba 24 de noviembre"),
            Apunte("Kotlin","Proyecto final 30 de noviembre"),
            Apunte("Sofware","Casos de uso  10 de noviembre"),
            Apunte("Web","Capa de entidades,negocio y datos"),
            Apunte("ESTRES","Todo el dia con un recyclerView"),
            Apunte("Ayuda","Pasense el codigo")

        )



    }
}