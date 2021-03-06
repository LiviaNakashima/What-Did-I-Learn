package br.com.wcc.whatdidilearn.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.wcc.whatdidilearn.entities.ItemLearned

@Dao
interface ItemLearnedDao {
    @Query("SELECT * FROM learned_item ORDER BY item_title ASC")
    fun getAll(): LiveData<MutableList<ItemLearned>>

    @Insert
    suspend fun insert(item: ItemLearned)

    @Delete
    suspend fun delete(item: ItemLearned)
}