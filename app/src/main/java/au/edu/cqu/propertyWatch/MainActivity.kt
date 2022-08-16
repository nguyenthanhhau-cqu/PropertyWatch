package au.edu.cqu.propertyWatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import au.edu.cqu.propertyWatch.Database.PropertyRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Property Watch"
        PropertyRepository.initialize(this)
            supportFragmentManager
                .beginTransaction()
                .addToBackStack("list_fragment")
                .commit()

        if (savedInstanceState == null) {
            loadFragment(PropertyListFragment.newInstance())
        }
    }
    private fun loadFragment(fragment: Fragment)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
    override fun onBackPressed() {

        val currentFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.container)

    }
}