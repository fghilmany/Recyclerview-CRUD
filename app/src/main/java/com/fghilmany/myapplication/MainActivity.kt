package com.fghilmany.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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

}