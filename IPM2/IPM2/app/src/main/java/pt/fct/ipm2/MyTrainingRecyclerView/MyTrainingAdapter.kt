package pt.fct.ipm2.MyTrainingRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.fct.ipm2.R
import pt.fct.ipm2.ui.trainings.Trainings

class MyTrainingAdapter(val exercises: List<Trainings>, val listener: OnItemClickListener): RecyclerView.Adapter<MyTrainingAdapter.MyViewHolder>() {

    //private var items:List<Exercises> = ArrayList()

    inner class MyViewHolder  (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val training = itemView.findViewById(R.id.text_title) as TextView
        val edit_btn =itemView.findViewById(R.id.edit_button) as ImageButton
        val delete_button = itemView.findViewById(R.id.delete_button) as ImageButton

        init {
            itemView.setOnClickListener(this)
            edit_btn.setOnClickListener(this)
            delete_button.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            when (v.id) {
                R.id.edit_button -> {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        listener.onEditClick(exercises[position],position)
                    }
                }
                R.id.delete_button ->{
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
        fun onItemClick(training: Trainings)
        fun onEditClick(training: Trainings,position: Int)
        fun onDeleteClick(training: Trainings,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_text_meustreinos, parent, false)
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