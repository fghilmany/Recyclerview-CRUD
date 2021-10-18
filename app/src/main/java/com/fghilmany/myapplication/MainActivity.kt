package com.fghilmany.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Listener {

    private lateinit var binding : ActivityMainBinding

    private val listAdapter = Adapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DialogAddItemFragment.listener = this

        listAdapter.listData = listData()
        listAdapter.listener = this

        with(binding.rvList){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }

        binding.btAddItem.setOnClickListener {
            DialogAddItemFragment.newInstance(false).show(supportFragmentManager, DialogAddItemFragment.TAG)
        }
    }

    override fun onAddItem(data: Data, position: Int?) {
        listAdapter.addItem(data, position)
        if (position == null)
            listAdapter.notifyItemInserted(listAdapter.listData.size)
        else
            listAdapter.notifyItemInserted( position )
    }

    override fun onEditItem(data: Data, position: Int?, isMove: Boolean) {
        if (isMove){
            val index = listAdapter.listData.indexOfFirst { it.id == data.id }
            listAdapter.moveItem(data, position?:0)
            listAdapter.notifyItemRemoved(index)
            listAdapter.notifyItemInserted(position?:0)


        }else{
            listAdapter.editItem(data)
            listAdapter.notifyItemChanged(position?:0)
        }
    }

    override fun onRemoveItem(data: Data) {
        val index = listAdapter.listData.indexOfFirst { it.id == data.id }
        listAdapter.removeItem(data)
        listAdapter.notifyItemRemoved(index)
    }
}