package pt.fct.ipm2.MyTrainingRecyclerView

//import kotlinx.android.synthetic.main.recycler_item.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.R
import java.util.*


class MyTrainingExercisesEditAdapter constructor(val exercises: List<Exercises>, val listener: OnItemClickListener): RecyclerView.Adapter<MyTrainingExercisesEditAdapter.MyViewHolder>() {

    //private var items:List<Exercises> = ArrayList()
    var isInTrain=false


    inner class MyViewHolder  (itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val exercise_title = itemView.findViewById(R.id.text_title) as TextView
        val delete_button = itemView.findViewById(R.id.delete_button) as ImageButton



        init {
            itemView.setOnClickListener(this)
            delete_button.setOnClickListener(this)

        }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.delete_button -> {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        listener.onDeleteClick(exercises[position],position)
                    }
                }
                else -> {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        listener.onItemClick(exercises[position])
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(exercise: Exercises)

        fun onDeleteClick(exercise: Exercises, position: Int)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_text_edit, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(inflater)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        val feed = exercises[position]
        holder.exercise_title.text = feed.title
    }

    fun onItemMove(fromPosition: Int?, toPosition: Int?): Boolean {
        fromPosition?.let {
            toPosition?.let {
                if (fromPosition < toPosition) {
                    for (i in fromPosition until toPosition) {
                        Collections.swap(exercises, i, i + 1)
                    }
                } else {
                    for (i in fromPosition downTo toPosition + 1) {
                        Collections.swap(exercises, i, i - 1)
                    }
                }
                notifyItemMoved(fromPosition, toPosition)
                return true
            }
        }
        return false
    }

    override fun getItemCount(): Int {
        return exercises.size
    }




}