package br.com.wcc.whatdidilearn.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.convertTo
import androidx.lifecycle.ViewModelProvider
import br.com.wcc.whatdidilearn.R
import br.com.wcc.whatdidilearn.data.DatabaseItems
import br.com.wcc.whatdidilearn.databinding.ActivityNewLearnedItemBinding
import br.com.wcc.whatdidilearn.entities.Level
import br.com.wcc.whatdidilearn.repository.LearnedItemsRepository
import br.com.wcc.whatdidilearn.viewmodel.NewLearnedItemViewModel
import br.com.wcc.whatdidilearn.viewmodel.NewLearnedItemViewModelFactory
import kotlinx.android.synthetic.main.activity_new_learned_item.*
import kotlinx.android.synthetic.main.item_learned.*
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

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.radio_high -> {
                    if (checked) {
                        Level.HIGH
                    } else {
                        null
                    }
                }
                R.id.radio_medium -> {
                    if (checked) {
                        Level.MEDIUM
                    } else {
                        null
                    }
                }
                R.id.radio_low -> {
                    if (checked) {
                        Level.LOW
                    } else {
                        null
                    }
                }
            }
        }
    }
}