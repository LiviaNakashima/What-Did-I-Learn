package br.com.wcc.whatdidilearn.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.wcc.whatdidilearn.R
import br.com.wcc.whatdidilearn.data.DatabaseItems
import br.com.wcc.whatdidilearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root) 
        
        val recyclerView = binding.itemsRecyclerView
        val adapter = LearnedItemsAdapter()
        recyclerView.adapter = adapter
        val learnedItemsList = DatabaseItems.getAll()
        adapter.data = learnedItemsList
    }
}