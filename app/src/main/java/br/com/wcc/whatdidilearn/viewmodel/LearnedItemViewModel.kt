package br.com.wcc.whatdidilearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wcc.whatdidilearn.data.ItemLearnedDao
import br.com.wcc.whatdidilearn.entities.ItemLearned
import br.com.wcc.whatdidilearn.repository.LearnedItemsRepository
import kotlinx.coroutines.launch

class LearnedItemViewModel(private var repository: LearnedItemsRepository) : ViewModel() {
    val learnedItems: LiveData<List<ItemLearned>>

    init {
        learnedItems = repository.learnedItems
    }

    fun insertNewLearnedItem(item: ItemLearned) {
        viewModelScope.launch {
            repository.insertNewLearnedItem(item)
        }
    }

}