package br.com.wcc.whatdidilearn.repository

import br.com.wcc.whatdidilearn.data.DatabaseItems
import br.com.wcc.whatdidilearn.data.ItemLearnedDao
import br.com.wcc.whatdidilearn.entities.ItemLearned

class LearnedItemsRepository(private val dao: ItemLearnedDao) {
    val learnedItems = dao.getAll()

    suspend fun insertNewLearnedItem(item: ItemLearned) {
        dao.insert(item)
    }
}