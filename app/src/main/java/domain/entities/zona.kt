package domain.entities

class zonas : ArrayList<zona>()
data class zona(
    val id : Int,
    val Nom : String,
    val Latitude : Double,
    val Longitude : Double,
    val Descripcio : String,
    val created_at : String,
    val updated_at : String

)
