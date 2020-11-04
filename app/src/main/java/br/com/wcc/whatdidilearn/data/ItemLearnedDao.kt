package br.com.wcc.whatdidilearn.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.wcc.whatdidilearn.entities.ItemLearned

@Dao
interface ItemLearnedDao {
    @Query("SELECT * FROM learned_item ORDER BY item_title ASC")
    fun getAll(): List<ItemLearned>

    @Insert
    fun insert(item: ItemLearned)
}