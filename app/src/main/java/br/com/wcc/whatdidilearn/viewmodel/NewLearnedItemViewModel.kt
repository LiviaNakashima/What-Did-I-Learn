package br.com.wcc.whatdidilearn.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wcc.whatdidilearn.data.ItemLearnedDao
import br.com.wcc.whatdidilearn.entities.ItemLearned
import br.com.wcc.whatdidilearn.entities.Level
import kotlinx.coroutines.launch

class NewLearnedItemViewModel(private var dao: ItemLearnedDao): ViewModel() {
    fun insertNewLearnedItem(itemTitle: String, itemDescription: String){
        viewModelScope.launch {
            val item = ItemLearned(itemTitle, itemDescription, Level.HIGH)
            dao.insert(item)
        }
    }
}