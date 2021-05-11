package com.assignment.countrylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.countrylist.R
import com.assignment.countrylist.databinding.RecyclerCardviewBinding
import com.assignment.countrylist.model.Row

class DataAdapter(private val rowList:List<Row>):
    RecyclerView.Adapter<DataAdapter.MyViewHolder>(){
    inner class MyViewHolder(binding:RecyclerCardviewBinding):RecyclerView.ViewHolder(binding.root)
    private lateinit var binding:RecyclerCardviewBinding

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.recycler_cardview,parent,false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val row=rowList[position]
        binding.dataModel=row
    }

    override fun getItemCount(): Int {
        return rowList.size   }
}