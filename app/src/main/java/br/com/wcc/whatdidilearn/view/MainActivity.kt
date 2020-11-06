package br.com.wcc.whatdidilearn.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.wcc.whatdidilearn.data.DatabaseItems
import br.com.wcc.whatdidilearn.databinding.ActivityMainBinding
import br.com.wcc.whatdidilearn.viewmodel.LearnedItemViewModel
import br.com.wcc.whatdidilearn.viewmodel.LearnedItemViewModelFactory
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
        val viewModelFactory = LearnedItemViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LearnedItemViewModel::class.java)
        val itemsList = viewModel.learnedItemsList
        itemsList.observe(this, Observer { items ->
            adapter.data = items
        })
    }

    fun irTelaNova(componente:View){
        val novaTela = Intent(this, NewLearnedItem::class.java)

        startActivity(novaTela)
    }
}