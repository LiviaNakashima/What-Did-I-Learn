package br.com.wcc.whatdidilearn.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.wcc.whatdidilearn.R
import br.com.wcc.whatdidilearn.data.DatabaseItems
import br.com.wcc.whatdidilearn.databinding.ActivityNewLearnedItemBinding
import br.com.wcc.whatdidilearn.repository.LearnedItemsRepository
import br.com.wcc.whatdidilearn.viewmodel.NewLearnedItemViewModel
import br.com.wcc.whatdidilearn.viewmodel.NewLearnedItemViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class NewLearnedItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewLearnedItemBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.title = "New Learned Item"

        val dao = DatabaseItems.getDatabase(this, CoroutineScope(Dispatchers.IO)).learnedItemDao()
        val repository = LearnedItemsRepository(dao)
        val viewModelFactory = NewLearnedItemViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NewLearnedItemViewModel::class.java)

        binding.btSave.setOnClickListener {
            when {
                binding.etTextoItem.text.isEmpty() -> {
                    binding.etTextoItem.error = "Preencha o título"
                }
                binding.etDescription.text.isEmpty() -> {
                    binding.etDescription.error = "Preencha a descrição"
                }
                else -> {
                    val title = binding.etTextoItem.text.toString()
                    val description = binding.etDescription.text.toString()

                    viewModel.insertNewLearnedItem(title, description)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}