package domain.requests.usuaris

class GetUsuariNif(private val Nif : String) {
    fun getParams() : Map<String, String>{
        val params = HashMap<String, String>()
        params["Nif"] = Nif
        params["api_key"] = "1"
        return params
    }
}