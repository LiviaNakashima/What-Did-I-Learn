package br.com.wcc.whatdidilearn.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.wcc.whatdidilearn.data.Converters
import br.com.wcc.whatdidilearn.data.ItemLearnedDao
import br.com.wcc.whatdidilearn.entities.ItemLearned
import br.com.wcc.whatdidilearn.entities.Level
import br.com.wcc.whatdidilearn.repository.LearnedItemsRepository
import kotlinx.coroutines.launch

class NewLearnedItemViewModel(private var repository: LearnedItemsRepository): ViewModel() {

    fun insertNewLearnedItem(itemTitle: String, itemDescription: String) {
        viewModelScope.launch {
            val item = ItemLearned(itemTitle, itemDescription, Level.)
            repository.insertNewLearnedItem(item)
        }
    }
}