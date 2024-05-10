package ui.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.reptecircuit.R
import com.example.reptecircuit.databinding.LoginBinding
import kotlin.math.log

class Login() : Fragment() {

    private lateinit var binding: LoginBinding
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
    }
}