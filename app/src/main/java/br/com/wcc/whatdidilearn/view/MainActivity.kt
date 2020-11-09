package br.com.wcc.whatdidilearn.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.wcc.whatdidilearn.data.DatabaseItems
import br.com.wcc.whatdidilearn.databinding.ActivityMainBinding
import br.com.wcc.whatdidilearn.repository.LearnedItemsRepository
import br.com.wcc.whatdidilearn.viewmodel.LearnedItemViewModel
import br.com.wcc.whatdidilearn.viewmodel.LearnedItemViewModelFactory
import br.com.wcc.whatdidilearn.viewmodel.NewLearnedItemViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.itemsRecyclerView
        val adapter = LearnedItemsAdapter()
        recyclerView.adapter = adapter


        val database = DatabaseItems.getDatabase(this, CoroutineScope(Dispatchers.IO))
        val learnedItemsDao = database.learnedItemDao()
        val repository = LearnedItemsRepository(learnedItemsDao)

        val viewModelFactory = LearnedItemViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LearnedItemViewModel::class.java)

        val learnedItems = viewModel.learnedItems
        learnedItems.observe(this, Observer {
            adapter.data = it
        })

        binding.btFab.setOnClickListener {
            val intent = Intent(this, NewLearnedItem::class.java)
            startActivity(intent)
        }
    }
}