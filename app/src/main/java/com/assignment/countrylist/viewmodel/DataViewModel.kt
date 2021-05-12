package com.assignment.countrylist.viewmodel
import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.countrylist.model.Row
import com.assignment.countrylist.repository.NetworkRepository
class DataViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private var context = getApplication<Application>().applicationContext
    var listofData: LiveData<List<Row>>  = MutableLiveData()
    private var networkRepository: NetworkRepository = NetworkRepository()
    var titleData: LiveData<String> = MutableLiveData()
    fun getCountries(): LiveData<List<Row>> {
        listofData = networkRepository.loadCountries(context)!!
        return listofData
    }
    val title: LiveData<String>
        get() = titleData
    fun updateActionBarTitle() {
        titleData= networkRepository.loadTitle(context)

    }
}