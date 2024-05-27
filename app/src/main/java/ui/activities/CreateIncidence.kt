package ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.reptecircuit.databinding.CreateIncidenceBinding
import dagger.hilt.android.AndroidEntryPoint
import domain.entities.incidencia
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import ui.viewmodels.ViewModelIncidencies
import ui.viewmodels.ViewModelZones
import kotlin.properties.Delegates

@AndroidEntryPoint
class CreateIncidence  : AppCompatActivity() {

    private lateinit var binding: CreateIncidenceBinding
    private val viewModelZones: ViewModelZones by viewModels()
    private val viewModelIncidencies: ViewModelIncidencies by viewModels()
    private var idZona by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateIncidenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var zones: Array<String> = emptyArray()
        viewModelZones.getAllZones()
        viewModelZones.zones.observe(this) { zoneList ->
            val updatedZones = zoneList.map { it.Nom }.toTypedArray()
            zones = updatedZones

            binding.zona.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Seleccioni una opció")
                builder.setItems(zones) { dialog, which ->
                    val selectedItem = zones[which]
                    binding.zona.text = selectedItem
                    idZona = zoneList[which].id
                }
                builder.show()
            }
        }



        binding.estat.setOnClickListener {
            val options = arrayOf(
                "Solucionada",
                "Borrador",
                "Informativa",
                "Inactiva",
                "Requereix",
                "En manteniment"
            )
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Seleccioni una opció")
            builder.setItems(options) { dialog, which ->
                val selectedItem = options[which]
                binding.estat.text = selectedItem
            }
            builder.show()
        }
        binding.prioritat.setOnClickListener {
            val options = arrayOf("Poca", "Informativa", "Normal", "Mínima", "Màxima")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Seleccioni una opció")
            builder.setItems(options) { dialog, which ->
                val selectedItem = options[which]
                binding.prioritat.text = selectedItem
            }
            builder.show()
        }
        binding.rol.setOnClickListener {
            val options = arrayOf("Operari", "Administrador")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Seleccioni una opció")
            builder.setItems(options) { dialog, which ->
                val selectedItem = options[which]
                binding.rol.text = selectedItem
            }
            builder.show()
        }
        binding.enviar.setOnClickListener {
            val nom = binding.nom.text.toString()
            val descripcio =  binding.descripcio.text.toString()
            val data = java.util.Date()
            val estat = binding.estat.text.toString()
            val prioritat = binding.prioritat.text.toString()
            val rol = binding.rol.text.toString()
            var incidencia: incidencia = incidencia(
                0, "$nom", "$descripcio", data, "$estat", "$prioritat",
                "null", "$rol", idZona, "", ""
            )
            viewModelIncidencies.createIncidence(incidencia)
            viewModelIncidencies.missatge.observe(this) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Resultat")
                builder.setMessage(it)
                builder.show()
            }
            finish()
        }
    }
}