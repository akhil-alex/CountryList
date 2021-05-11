package com.assignment.countrylist.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.assignment.countrylist.model.Row
import com.assignment.countrylist.network.RetrofitClient
import com.assignment.countrylist.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NetworkRepository {

    fun loadCountries(context: Context): LiveData<List<Row>>? {
        val utils = Utils()
        if (utils.isNetworkAvailable(context)) {
            val compositeDisposable = CompositeDisposable()
            compositeDisposable.add(
                RetrofitClient.instance.getData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ response ->
                        response.rows?.let { DatabaseRepository.insertCountries(context, it) }
                        DatabaseRepository.insertTitle(context, response)
                    },
                        { t ->
                            Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show()
                        })
            )
        }
        return DatabaseRepository.getAllCountry(context)
    }

    fun loadTitle(context: Context): LiveData<String> {
        return DatabaseRepository.getTitle(context)

    }

}