package au.edu.cqu.propertyWatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import au.edu.cqu.propertyWatch.api.PropertyItem

class PropertyWatchrViewModel : ViewModel() {

    val propertyItemLiveData: LiveData<List<PropertyItem>> = WatchrFetchr().fetchProperties()

}
