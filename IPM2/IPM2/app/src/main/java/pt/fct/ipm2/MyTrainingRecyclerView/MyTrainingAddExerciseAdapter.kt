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


class MyTrainingAddExerciseAdapter constructor(val exercises: List<Exercises>,val selectedexercises: List<Exercises>, val listener: OnItemClickListener): RecyclerView.Adapter<MyTrainingAddExerciseAdapter.MyViewHolder>() {

    //private var items:List<Exercises> = ArrayList()



    inner class MyViewHolder  (itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val exercise_title = itemView.findViewById(R.id.text_title) as TextView

        val add_button = itemView.findViewById(R.id.adicionar) as ImageButton

        init {



            add_button.setOnClickListener(this)


        }

        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                if(selectedexercises.contains(exercises[position])){
                    add_button.setBackgroundResource(R.drawable.mais_branco)
                }else{
                    add_button.setBackgroundResource(R.drawable.ic_baseline_check_24)
                }
                listener.onItemClick(exercises[position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(exercise: Exercises)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_text_exercise, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(inflater)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if(!selectedexercises.contains(exercises[position])){
            holder.add_button.setBackgroundResource(R.drawable.mais_branco)
        }else{
            holder.add_button.setBackgroundResource(R.drawable.ic_baseline_check_24)
        }

        val feed = exercises[position]
        holder.exercise_title.text = feed.title
    }


    override fun getItemCount(): Int {
        return exercises.size
    }




}