package br.com.wcc.whatdidilearn.data

import androidx.room.TypeConverter
import br.com.wcc.whatdidilearn.R
import br.com.wcc.whatdidilearn.entities.Level

class Converters {
    @TypeConverter
    fun levelToInt(level:Level): Int{
        return level.color
    }

    @TypeConverter
    fun intToLevel(color: Int): Level{
        return when(color){
            R.color.red -> Level.LOW
            R.color.yellow -> Level.MEDIUM
            else -> Level.HIGH
        }
    }
}