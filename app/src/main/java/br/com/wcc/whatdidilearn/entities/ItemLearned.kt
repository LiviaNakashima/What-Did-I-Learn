package br.com.wcc.whatdidilearn.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learned_item")
data class ItemLearned(
    @ColumnInfo(name = "item_title")
    val title: String,

    @ColumnInfo(name = "item_description")
    val description: String,

    @ColumnInfo(name = "item_understanding_level")
    val understandingLevel: Level,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    val id: Int = 0
)