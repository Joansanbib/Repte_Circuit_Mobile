package domain.entities

import java.time.LocalDate

class usuari_resols : ArrayList<usuari_resol>()
data class usuari_resol(
    val id : Int,
    val Incidencia : Int,
    val Usuari : Int,
    val Inici : LocalDate,
    val Final : LocalDate,
    val Comentaris : String,
    val Estat : Array<String>,

)
