package ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.reptecircuit.R
import com.example.reptecircuit.databinding.RegisterBinding

class Register() : Fragment() {
    private lateinit var binding: RegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contrasenyaInput.bringToFront()
        binding.usuariInput.bringToFront()
        binding.repetirContrasenyaInput.bringToFront()

        binding.RolBtn.setOnClickListener {
            showRolDialog(it)
        }
        binding.registrarseClick.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val loginPage = Login()
                replace(R.id.fragmentContainerView, loginPage)
                addToBackStack(null)
                commit()
            }

        }
        binding.entrar.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val dashboard = Dashboard()
                replace(R.id.fragmentContainerView, dashboard)
                addToBackStack(null)
                commit()
            }
        }
        val backgroundVideo = binding.backgroundVideo as VideoView
        val videoPath = "android.resource://" + requireContext().packageName + "/" + R.raw.background_video
        val videoUri = Uri.parse(videoPath)
        backgroundVideo.setVideoURI(videoUri)
        backgroundVideo.setOnPreparedListener {
            it.isLooping = true
            it.start()
        }

    }
    fun showRolDialog(view: View){
        val roles = arrayOf("Administrador", "Operari")

        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Seleccionar Rol")
        builder.setItems(roles) { _, which ->
            binding.Rol.setText(roles[which])
            binding.Rol.visibility = View.VISIBLE
            binding.RolBtn.visibility = View.GONE
            binding.guideline6.setGuidelinePercent(0.76f)
        }
        builder.show()
    }
}