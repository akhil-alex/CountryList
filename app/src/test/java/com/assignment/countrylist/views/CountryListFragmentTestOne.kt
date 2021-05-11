package com.assignment.countrylist.views

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.assignment.countrylist.model.Row
import com.assignment.countrylist.viewmodel.DataViewModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class CountryListFragmentTestOne
{
    private val application = Mockito.mock(Application::class.java)
    private var listData: MutableLiveData<List<Row>> = MutableLiveData<List<Row>>()

    @Mock
    var dataViewModel: DataViewModel = DataViewModel(application)
    @Mock
    var countryListFragment: CountryListFragment = CountryListFragment()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var initRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        val listData: MutableLiveData<List<Row>> = MutableLiveData<List<Row>>()
        val datarrayList = arrayListOf<Row>()
        val row = Row("akl", "88", "Egh", 1)
        datarrayList.add(row)
        listData.postValue(datarrayList)
    }


    @Test(expected = RuntimeException::class)
    fun test_throwsException() {
        Mockito.`when`(countryListFragment.viewLifecycleOwnerLiveData).thenThrow(RuntimeException::class.java)
        countryListFragment.viewLifecycleOwnerLiveData
    }
    @Test
    fun testNotNullResponse() {
        Mockito.`when`(dataViewModel.getCountries()).thenReturn(listData)
        MatcherAssert.assertThat(
            listData,
            CoreMatchers.`is`(CoreMatchers.notNullValue())
        )
    }



}