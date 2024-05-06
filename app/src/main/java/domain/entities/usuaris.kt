package domain.entities

import java.sql.Timestamp
import java.time.LocalDate

data class usuaris(
    val id : Int,
    val NIF : String,
    val Nom : String,
    val Cognoms : String,
    val Data_naixament : LocalDate,
    val Rol : Array<String>,
    val Lleng_preferencia : String,
    val name : String,
    val email : String,
    val password : String,
    val Perfil_xat : String,
    val email_verified_at : Timestamp,
    val profile_photo_path : String

)
