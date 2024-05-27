package ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reptecircuit.databinding.IncidenciaBinding

class IncidenciaDetall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : IncidenciaBinding = IncidenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nomInput.text = intent.getStringExtra("nom")
        binding.descripcioInput.text = intent.getStringExtra("desc")
        binding.dataInput.text = intent.getStringExtra("data")
        binding.estatInput.text = intent.getStringExtra("estat")
        binding.prioritatInput.text = intent.getStringExtra("prioritat")
        binding.rolInput.text = intent.getStringExtra("rol")
        binding.zonaInput.text = intent.getStringExtra("zona")


    }
}