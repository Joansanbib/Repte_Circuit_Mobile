package ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reptecircuit.R
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
        binding.incidencies.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val incidencies = Incidencies()
                replace(R.id.fragmentContainerView, incidencies)
                addToBackStack(null)
                commit()
            }
        }
        binding.zones.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val zones = Zones()
                replace(R.id.fragmentContainerView, zones)
                addToBackStack(null)
                commit()
            }
        }

    }
}