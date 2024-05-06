package domain.requests.usuaris

class GetUsuariNom(private val nom : String) {
    fun getParams() : Map<String, String>{
        val params = HashMap<String, String>()
        params["nom"] = nom
        params["api_key"] = "1"
        return params
    }
}