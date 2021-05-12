package com.assignment.countrylist.network
import com.assignment.countrylist.model.DataModels
import io.reactivex.Observable
import retrofit2.http.GET
interface NetworkApi {
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getData(): Observable<DataModels>
}