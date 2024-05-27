package domain.entities

import com.google.gson.annotations.SerializedName
import java.util.Date

/*class incidencies : ArrayList<incidencia>()*/
data class incidencia(
    val id: Int,
    val Nom: String,
    val Descripcio: String,
    val Data: Date,
    val Estat: String,
    val Prioritat: String,
    val Ruta_img: String,
    val Rol_assignat: String,
    val Zona : Int,
    val created_at : String,
    val updated_at : String

    )
