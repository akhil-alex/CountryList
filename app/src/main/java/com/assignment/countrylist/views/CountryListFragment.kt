package com.assignment.countrylist.views
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.countrylist.R
import com.assignment.countrylist.adapter.DataAdapter
import com.assignment.countrylist.databinding.FragmentListBinding
import com.assignment.countrylist.model.Row
import com.assignment.countrylist.utils.ConnectivityReceiver
import com.assignment.countrylist.viewmodel.DataViewModel
@Suppress("DEPRECATION", "COMPATIBILITY_WARNING")
class CountryListFragment:Fragment(), ConnectivityReceiver.ConnectivityReceiverListener {
    private lateinit var dataAdapter: DataAdapter
    private lateinit var dataViewModel: DataViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var binding: FragmentListBinding
    private lateinit var mutablelist: MutableLiveData<List<Row>>
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var textNoData: TextView
    private lateinit var textActionBar:TextView
    private var isConnected = true
    private var datalist: List<Row> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        val view: View = binding.root
        requireActivity().registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        recyclerView =view . findViewById (R.id.rec_list)
        progressBar = view.findViewById(R.id.progressbar)
        swipeContainer = view.findViewById(R.id.swipe_refresh)
        textNoData = view.findViewById(R.id.tv_nodata)
        textActionBar=view.findViewById(R.id.action_bar)
        mutablelist = MutableLiveData<List<Row>>()
        initializeRecyclerView()
        dataAdapter = DataAdapter(datalist)
        dataViewModel= ViewModelProviders.of( this).get(DataViewModel::class.java)
        return view
    }
    private fun observeData() { //observe the view model data
        dataViewModel.getCountries()
        dataViewModel.listofData
            .observe(this, {
                setObserveData(it)
            })
        updateActionBar()
    }
    private fun initializeRecyclerView() //recyclerview initialization
    {
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
    }
    private fun setObserveData(datalist: List<Row>) //set values to the adaptor
    {
        if (datalist.isEmpty()) {
            if (!isConnected) {
                textNoData.visibility = View.VISIBLE
                Toast.makeText(
                    requireContext(),
                    getString(R.string.plz_check_internet),
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            textNoData.visibility = View.GONE
            if (!isConnected)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.data_loading),
                    Toast.LENGTH_LONG
                ).show()
        }
        if(dataAdapter.itemCount==0) {
            dataAdapter = DataAdapter(datalist)
            binding.dataadapter = dataAdapter
        }
        Handler().postDelayed({
            swipeContainer.isRefreshing = false
            progressBar.visibility = View.GONE }, 1000)
    }
    private fun updateActionBar() //actionbar title update
    {
        dataViewModel.updateActionBarTitle()
        dataViewModel.titleData.observe(this , {
            textActionBar.text = it
            if(textActionBar.text.isEmpty())
                textActionBar.text =getText(R.string.app_name)

        })
    }
    override fun onNetworkConnectionChanged(isconnected: Boolean) {
        this.isConnected = isconnected
        progressBar.visibility = View.VISIBLE
        observeData()
    }
    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }
    override fun onStart() {
        super.onStart()
        swipeContainer.setOnRefreshListener {
            dataAdapter = DataAdapter(emptyList())
            observeData()
        }
    }
}



