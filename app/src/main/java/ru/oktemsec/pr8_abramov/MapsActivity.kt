package ru.oktemsec.pr8_abramov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.oktemsec.pr8_abramov.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomGesturesEnabled = true
            uiSettings.isCompassEnabled = true
        }
        // Add a marker in Abramov and move the camera
        val sydney = LatLng(61.660133, 129.396604)
        mMap.addMarker(MarkerOptions().position(sydney).title("Abramov is here"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnMapClickListener {
            val marker:MarkerOptions = MarkerOptions().position(LatLng(it.latitude, it.longitude)).title("Координаты: ${it.latitude} ${it.longitude}")
            mMap.addMarker((marker))
            System.out.println("${it.latitude}---${it.latitude}")
        }
    }

    fun ChangeType(view: View) {
        when(mMap.mapType) {
            GoogleMap.MAP_TYPE_NORMAL -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            GoogleMap.MAP_TYPE_SATELLITE -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            GoogleMap.MAP_TYPE_HYBRID -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            GoogleMap.MAP_TYPE_TERRAIN -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }
    }
}