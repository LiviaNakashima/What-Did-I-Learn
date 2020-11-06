package br.com.wcc.whatdidilearn.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.wcc.whatdidilearn.data.ItemLearnedDao

class NewLearnedItemViewModelFactory (private val dao: ItemLearnedDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewLearnedItemViewModel::class.java)) {
            return NewLearnedItemViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}