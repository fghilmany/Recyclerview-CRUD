package com.fghilmany.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fghilmany.myapplication.databinding.ItemRvBinding

class Adapter: RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    var listener: Listener? = null
    var listData = mutableListOf<Data>()


    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, null, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val result = listData[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int = listData.size

    inner class AdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemRvBinding.bind(view)
        fun bind(result: Data) {
            with(binding){
                result.apply {
                    tvName.text = name
                    tvDesc.text = desc
                    if (listener != null)
                        ivRemove.setOnClickListener {
                            listener?.onRemoveItem(result)
                        }
                }
            }
        }

    }
}