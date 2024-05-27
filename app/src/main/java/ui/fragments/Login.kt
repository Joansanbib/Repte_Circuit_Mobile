package ui.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reptecircuit.R
import com.example.reptecircuit.databinding.LoginBinding
import dagger.hilt.android.AndroidEntryPoint
import domain.entities.usuari
import okhttp3.internal.wait
import ui.viewmodels.ViewModelCredentials
import ui.viewmodels.ViewModelIncidencies
import kotlin.math.log

@AndroidEntryPoint
class Login() : Fragment() {

    private lateinit var binding: LoginBinding
    private val viewModelCredentials: ViewModelCredentials by viewModels()
    private var usuari : usuari? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contrasenyaInput.bringToFront()
        binding.usuariInput.bringToFront()

        viewModelCredentials.usuari.observe(viewLifecycleOwner) {
            usuari = it
            if(usuari != null){
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    val dashboard = Dashboard()
                    replace(R.id.fragmentContainerView, dashboard)
                    addToBackStack(null)
                    commit()
                }
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

        binding.registrarseClick.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val registerPage = Register()
                replace(R.id.fragmentContainerView, registerPage)
                addToBackStack(null)
                commit()
            }
        }

        binding.entrar.setOnClickListener {
            viewModelCredentials.login(binding.usuariInput.text.toString(), binding.contrasenyaInput.text.toString())
        }
    }
}