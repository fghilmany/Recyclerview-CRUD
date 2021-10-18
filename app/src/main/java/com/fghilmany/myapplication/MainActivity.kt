package com.fghilmany.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Listener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listAdapter = Adapter()
        listAdapter.listData = listData()
        listAdapter.listener = this

        with(binding.rvList){
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    override fun onRemoveItem(data: Data) {

    }
}