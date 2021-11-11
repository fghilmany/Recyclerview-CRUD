package com.fghilmany.myapplication

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.fghilmany.myapplication.databinding.ItemRvBinding

class Adapter: RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    var listData = mutableListOf<Data>()


    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return AdapterViewHolder(view)
    }

    fun addItem(data: Data, position: Int?){
        if (position == null)
            listData.add(data)
        else
            listData.add(position, data)
    }

    fun editItem(data: Data){
        data.apply {
            listData.find { it.id == data.id }?.name = name
            listData.find { it.id == data.id }?.desc = desc
        }
    }

    fun moveItem(data: Data, position: Int){
        val index = listData.indexOfFirst { it.id == data.id }
        listData.removeAt(index)
        listData.add(position, data)
    }

    fun removeItem(data: Data){
        listData.remove(data)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val result = listData[position]
        holder.bind(result, position)
    }

    override fun getItemCount(): Int = listData.size

    inner class AdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemRvBinding.bind(view)
        fun bind(result: Data, position: Int) {
            with(binding){

                result.apply {
                    tvName.text = name
                    tvDesc.text = desc
                    itemView.setOnClickListener {
                        DialogAddItemFragment.newInstance(true, result, position).show((itemView.context as FragmentActivity).supportFragmentManager, DialogAddItemFragment.TAG)
                    }
                }
            }
        }

    }
}