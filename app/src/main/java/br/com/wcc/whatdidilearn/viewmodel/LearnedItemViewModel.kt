package br.com.wcc.whatdidilearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.wcc.whatdidilearn.data.ItemLearnedDao
import br.com.wcc.whatdidilearn.entities.ItemLearned

class LearnedItemViewModel(private val dao: ItemLearnedDao): ViewModel(){
    val learnedItemsList: LiveData<List<ItemLearned>>

    init {
        learnedItemsList = dao.getAll()
    }
}