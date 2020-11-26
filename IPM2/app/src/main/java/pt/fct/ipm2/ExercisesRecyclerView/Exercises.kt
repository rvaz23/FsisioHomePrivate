package pt.fct.ipm2.ExercisesRecyclerView

import java.io.Serializable
import java.util.*

data class Exercises( val title:String, val category: String,val image: String, val description: String) : Serializable