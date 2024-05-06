package domain.requests.zones

class GetZonaDesc(private val text : String) {
    fun getParams() : Map<String, String>{
        val params = HashMap<String, String>()
        params["text"] = text
        params["api_key"] = "1"
        return params
    }
}