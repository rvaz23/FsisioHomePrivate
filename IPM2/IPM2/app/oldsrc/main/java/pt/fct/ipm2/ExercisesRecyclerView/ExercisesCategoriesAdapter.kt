package pt.fct.ipm2.ExercisesRecyclerView

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.recycler_item.view.*
import pt.fct.ipm2.R
import kotlin.coroutines.coroutineContext


class ExercisesCategoriesAdapter constructor(val exercises: List<Exercises>, val listener: OnItemClickListener,resources:Resources): RecyclerView.Adapter<ExercisesCategoriesAdapter.MyViewHolder>() {


    private var items:List<Exercises> = ArrayList()
    var resource =resources
    inner class MyViewHolder  (itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        val exercise_type = itemView.findViewById(R.id.category_image) as ImageView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position,exercises[position].category)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int,category: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_box, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(inflater)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val feed = exercises[position]

        when(feed.category){
            "Perna"->holder.exercise_type.setImageResource(R.drawable.joelho)
            "Ombro"->holder.exercise_type.setImageResource(R.drawable.ombro)
            "Joelho"->holder.exercise_type.setImageResource(R.drawable.joelho)
            "Mao"->holder.exercise_type.setImageResource(R.drawable.mao)
            "Cervical"->holder.exercise_type.setImageResource(R.drawable.cervical)
        }
    }

    override fun getItemCount(): Int {
        return exercises.size
    }




}