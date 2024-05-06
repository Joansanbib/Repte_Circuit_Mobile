package domain.entities

import java.time.LocalDate

data class usuari_resols(
    val id : Int,
    val Incidencia : Int,
    val Usuari : Int,
    val Inici : LocalDate,
    val Final : LocalDate,
    val Comentaris : String,
    val Estat : Array<String>,

)
