package pt.fct.ipm2.ExercisesRecyclerView

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

//escrever time
data class Exercises( val title:String, val category: String,val image: Int, val description: String, val duration:String) : Serializable

/*
@Entity(tableName = "Exercises")
class Exercises(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name="category")
    val category: String,
    @ColumnInfo(name = "description")
    val description: String,
    val image: Int,  val duration:String) : Serializable

 */