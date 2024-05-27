package ui.fragments

import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.GpsStatus
import android.location.Location
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.reptecircuit.R
import com.example.reptecircuit.databinding.ZonesBinding
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class Zones : Fragment(), MapListener, GpsStatus.Listener {
    private lateinit var binding : ZonesBinding
    private lateinit var mMap: MapView
    private lateinit var controller: IMapController;
    private lateinit var mMyLocationOverlay: MyLocationNewOverlay;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ZonesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureMap()

    }

    fun configureMap(){
        Configuration.getInstance().load(
            requireContext(),
            requireActivity().getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        )
        mMap = binding.osmmap
        mMap.setTileSource(TileSourceFactory.MAPNIK)
        mMap.setMultiTouchControls(true)
        mMap.getLocalVisibleRect(Rect())
        configurarUbicacio()
        controller = mMap.controller
        val ubicacion = Location("provider")
        ubicacion.latitude = 41.569420
        ubicacion.longitude = 2.257558

        val ubicacionParseada = GeoPoint(ubicacion.latitude, ubicacion.longitude)

        controller.setCenter(ubicacionParseada)

        controller.setZoom(16.7)

        defineZones()

        mMap.addMapListener(this)
    }

    fun defineZones(){
        val texto = "Pàrking"
        val zona = GeoPoint(41.569013, 2.257102)

        val marcador = Marker(mMap)
        marcador.position = zona
        marcador.title = texto
        marcador.icon = ContextCompat.getDrawable(requireActivity(), R.drawable.marker)

        mMap.overlays.add(marcador)



    }


    fun configurarUbicacio(){
        val provUbicacio = GpsMyLocationProvider(context)
        val superposicioUbicacio = MyLocationNewOverlay(provUbicacio, mMap)
        superposicioUbicacio.enableMyLocation()
        mMap.overlays.add(superposicioUbicacio)

    }

    override fun onScroll(event: ScrollEvent?): Boolean {
        return true
    }

    override fun onZoom(event: ZoomEvent?): Boolean {
        return false
    }

    override fun onGpsStatusChanged(event: Int) {
        TODO("Not yet implemented")
    }
}