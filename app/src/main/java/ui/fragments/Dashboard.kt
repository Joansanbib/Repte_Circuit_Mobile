package ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reptecircuit.databinding.DashboardBinding
import com.example.reptecircuit.databinding.LoginBinding

class Dashboard : Fragment() {

    private lateinit var binding: DashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DashboardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logo.bringToFront()
        binding.usuaris.bringToFront()
        binding.incidencies.bringToFront()

    }
}