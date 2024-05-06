package domain.requests.incidencies

class GetIncidenciaData (private val data : String) {
    fun getParams() : Map<String, String>{
        val params = HashMap<String, String>()
        params["data"] = data
        params["api_key"] = "1"
        return params
    }
}