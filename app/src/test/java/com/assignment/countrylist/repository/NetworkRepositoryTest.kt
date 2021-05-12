package com.assignment.countrylist.repository
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
class NetworkRepositoryTest {
    private val context = Mockito.mock(Context::class.java)
    @Mock
    var repository: NetworkRepository = NetworkRepository()
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    @Test(expected = RuntimeException::class)
    fun test_throwsException() {
        Mockito.`when`(repository.loadCountries(context))
            .thenThrow(RuntimeException::class.java)
        repository.loadCountries(context)
    }
    @Test(expected = RuntimeException::class)
    fun title_throwsException() {
        Mockito.`when`(repository.loadTitle(context)).thenThrow(RuntimeException::class.java)
        repository.loadTitle(context)
    }
}