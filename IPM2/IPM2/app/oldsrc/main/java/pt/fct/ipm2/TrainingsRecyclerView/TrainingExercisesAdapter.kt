package pt.fct.ipm2.TrainingsRecyclerView


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.recycler_item.view.*
import pt.fct.ipm2.R
import pt.fct.ipm2.ui.home.HomeFragment
import pt.fct.ipm2.ui.trainings.Trainings


class TrainingExercisesAdapter(val exercises: List<Trainings>, val listener: OnItemClickListener): RecyclerView.Adapter<TrainingExercisesAdapter.MyViewHolder>() {

    //private var items:List<Exercises> = ArrayList()

    inner class MyViewHolder  (itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        val training = itemView.findViewById(R.id.text_title) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClickTr(position,exercises[position])
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClickTr(position: Int,training: Trainings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_text, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(inflater)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val feed = exercises[position]
        holder.training.text = feed.title
    }

    override fun getItemCount(): Int {
        return exercises.size
    }




}