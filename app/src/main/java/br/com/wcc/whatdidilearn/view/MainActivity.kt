package br.com.wcc.whatdidilearn.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.wcc.whatdidilearn.data.DatabaseItems
import br.com.wcc.whatdidilearn.databinding.ActivityMainBinding
import br.com.wcc.whatdidilearn.entities.ItemLearned
import br.com.wcc.whatdidilearn.repository.LearnedItemsRepository
import br.com.wcc.whatdidilearn.viewmodel.LearnedItemViewModel
import br.com.wcc.whatdidilearn.viewmodel.LearnedItemViewModelFactory
import br.com.wcc.whatdidilearn.viewmodel.NewLearnedItemViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

        val recyclerView = binding.itemsRecyclerView
        val adapter = LearnedItemsAdapter()
        recyclerView.adapter = adapter


        val database = DatabaseItems.getDatabase(this, CoroutineScope(Dispatchers.IO))
        val dao = database.learnedItemDao()
        val repository = LearnedItemsRepository(dao)
        val viewModelFactory = LearnedItemViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LearnedItemViewModel::class.java)
        val itemsList = viewModel.learnedItems
        itemsList.observe(this, Observer { items ->
            adapter.data = items
        })

        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val itemToDelete = adapter.data[position]
                    adapter.deleteItem(itemToDelete)
                    viewModel.deleteLearnedItem(itemToDelete)

                }

            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        binding.btFab.setOnClickListener {
            val intent = Intent(this, NewLearnedItem::class.java)
            startActivity(intent)
        }


        val removedItemThroughButtonClickOnAdapter = adapter.getRemoveItemPosition()
        removedItemThroughButtonClickOnAdapter?.let {
            viewModel.deleteLearnedItem(
                it
            )
        }

    }

}