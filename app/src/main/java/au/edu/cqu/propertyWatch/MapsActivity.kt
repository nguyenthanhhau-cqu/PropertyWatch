package au.edu.cqu.propertyWatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import au.edu.cqu.propertyWatch.Database.Property

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import au.edu.cqu.propertyWatch.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    var mName: String? = null
    var mLat = 0.0
    var mLon = 0.0
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Map"

        if (intent != null && intent.hasExtra("map_data"))
        {
            val mapData = intent.getSerializableExtra("map_data") as Property

            mName = "$" + mapData.price.toString()
            mLat = mapData.lat
            mLon = mapData.lon
        }

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
        val venueLatLon = LatLng(mLat, mLon)
        mMap.addMarker(MarkerOptions().position(venueLatLon).title(mName))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(venueLatLon, 14f))
    }
}