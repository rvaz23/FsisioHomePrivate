package pt.fct.ipm2.ui.trainings

import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import java.io.Serializable

data class Trainings(val title:String,val exercises: MutableList<Exercises>) :Serializable