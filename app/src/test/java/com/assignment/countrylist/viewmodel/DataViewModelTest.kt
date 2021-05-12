package com.assignment.countrylist.viewmodel
import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.assignment.countrylist.model.Row
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
@Suppress("DEPRECATION")
@RunWith(MockitoJUnitRunner::class)
class DataViewModelTest {
    private val application = Mockito.mock(Application::class.java)
    @Mock
    var dataViewModel: DataViewModel = DataViewModel(application)
    private var listData: MutableLiveData<List<Row>> = MutableLiveData<List<Row>>()
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
        val row = Row("testDescription", "https://reactnativecode.com/wp-content/uploads/2018/02/Default_Image_Thumbnail.png","testTitle", 1)
        datarrayList.add(row)
        listData.postValue(datarrayList)
    }
    @Test
    @Throws(InterruptedException::class)
    fun dataComparison() {
        dataViewModel = Mockito.mock(DataViewModel::class.java)
        val listData: MutableLiveData<List<Row>> = MutableLiveData<List<Row>>()
        val dataArrayList = arrayListOf<Row>()
        val row = Row("testDescription", "https://reactnativecode.com/wp-content/uploads/2018/02/Default_Image_Thumbnail.png","testTitle", 1)
        dataArrayList.add(row)
        listData.postValue(dataArrayList)
        Mockito.`when`(dataViewModel.getCountries()).thenReturn(listData)
        junit.framework.Assert.assertEquals(
            listData.value,
            dataViewModel.getCountries().value
        )
    }
    @Test
    fun testNotNullResponse() {
        Mockito.`when`(dataViewModel.getCountries()).thenReturn(listData)
        MatcherAssert.assertThat(
            dataViewModel.getCountries(),
            CoreMatchers.`is`(CoreMatchers.notNullValue())
        )
    }
    @Test(expected = RuntimeException::class)
    fun testThrowsException() {
        Mockito.`when`(dataViewModel.getCountries()).thenThrow(RuntimeException::class.java)
        dataViewModel.getCountries()
    }
    @Test
    fun checkSize() {
        dataViewModel = Mockito.mock(DataViewModel::class.java)
        val listData: MutableLiveData<List<Row>> = MutableLiveData<List<Row>>()
        val dataArrayList = arrayListOf<Row>()
        val row = Row("testDescription", "https://reactnativecode.com/wp-content/uploads/2018/02/Default_Image_Thumbnail.png","testTitle", 1)
        dataArrayList.add(row)
        listData.postValue(dataArrayList)
        Mockito.`when`(dataViewModel.getCountries()).thenReturn(listData)
        val subscriber = dataViewModel.getCountries()
        subscriber.value?.let { TestCase.assertEquals(it.size, 1) }
    }
}