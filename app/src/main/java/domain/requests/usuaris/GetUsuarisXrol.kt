package domain.requests.usuaris

class GetUsuarisXrol(private val rol : String) {
    fun getParams() : Map<String, String>{
        val params = HashMap<String, String>()
        params["rol"] = rol
        params["api_key"] = "1"
        return params
    }
}