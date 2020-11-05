package br.com.wcc.whatdidilearn.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.wcc.whatdidilearn.data.DatabaseItems
import br.com.wcc.whatdidilearn.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root) 
        
        val recyclerView = binding.itemsRecyclerView
        val adapter = LearnedItemsAdapter()
        recyclerView.adapter = adapter

        val database = DatabaseItems.getDatabase(this, CoroutineScope(Dispatchers.IO))
        val dao = database.learnedItemDao()
        val itemsList = dao.getAll()
        itemsList.observe(this, Observer { items ->
            adapter.data = items
        })
    }
}