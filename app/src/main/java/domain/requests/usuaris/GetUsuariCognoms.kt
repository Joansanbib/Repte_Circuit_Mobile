package domain.requests.usuaris

class GetUsuariCognoms(private val cognoms : String) {
    fun getParams() : Map<String, String>{
        val params = HashMap<String, String>()
        params["cognoms"] = cognoms
        params["api_key"] = "1"
        return params
    }
}