package pt.fct.ipm2.ExercisesRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.recycler_item.view.*
import pt.fct.ipm2.R


class ExercisesAdapter constructor(val exercises: List<Exercises>, val listener: OnItemClickListener): RecyclerView.Adapter<ExercisesAdapter.MyViewHolder>() {

    //private var items:List<Exercises> = ArrayList()


    inner class MyViewHolder  (itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val exercise_title = itemView.findViewById(R.id.text_title) as TextView
        val exercise_image = itemView.findViewById(R.id.imageView3) as ImageView
        val exercise_time = itemView.findViewById(R.id.textView2) as TextView
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
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
            .inflate(R.layout.recycler_text_trainings, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(inflater)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        val feed = exercises[position]
        holder.exercise_title.text = feed.title
        holder.exercise_image.setImageResource(feed.image)
        holder.exercise_time.text = feed.duration
    }

    override fun getItemCount(): Int {
        return exercises.size
    }




}