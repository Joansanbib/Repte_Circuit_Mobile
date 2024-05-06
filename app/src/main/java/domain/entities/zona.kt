package domain.entities

class zonas : ArrayList<zona>()
data class zona(
    val id : Int,
    val Nom : String,
    val Descripcio : String,
    val Ultima_incidencia : Int

)
