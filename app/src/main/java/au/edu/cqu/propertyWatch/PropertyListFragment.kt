package au.edu.cqu.propertyWatch

import au.edu.cqu.propertyWatch.adapters.PropertyAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.cqu.propertyWatch.Database.Property
import au.edu.cqu.propertyWatch.Database.PropertyListViewModel
import au.edu.cqu.propertyWatch.Database.PropertyRepository

class PropertyListFragment :Fragment() {
    var propertyList =  mutableListOf<Property>();

    var id: String = ""
    var address: String = ""
    var price: Int = 0
    var phone: String = ""
    var lat : Double = 0.0
    var lon : Double = 0.0

    private val propertyWatchrViewModel = PropertyWatchrViewModel()

    companion object {
        fun newInstance() = PropertyListFragment()
    }

    private lateinit var mPropertyListViewModel: PropertyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = activity as ViewModelStoreOwner

        mPropertyListViewModel = ViewModelProvider(context).get(PropertyListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val recyclerView =
            inflater.inflate(R.layout.fragment_list, container, false) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        mPropertyListViewModel.propertyList.observe(
            viewLifecycleOwner, Observer { propertyList ->

                if (propertyList.isEmpty()) {
                    PropertyRepository.loadData()
                    return@Observer
                }
                recyclerView.adapter = PropertyAdapter(propertyList)
            })
        return recyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var property: Property

        super.onViewCreated(view, savedInstanceState)
        propertyWatchrViewModel.propertyItemLiveData.observe(
            viewLifecycleOwner
        ) { PropertyItems ->
            //Loop through all PropertyItem list then add to Property list
            for (i in PropertyItems.indices) {
                address  = PropertyItems[i].address
                phone  = PropertyItems[i].phone
                id  = PropertyItems[i].id
                lat  = PropertyItems[i].lat
                lon  = PropertyItems[i].lon
                price = PropertyItems[i].price
                property = Property(id, address, price, phone,lat,lon)
                propertyList.add(property)
            }
            PropertyRepository.INSTANCE?.addProperties(propertyList);
        }

    }
}