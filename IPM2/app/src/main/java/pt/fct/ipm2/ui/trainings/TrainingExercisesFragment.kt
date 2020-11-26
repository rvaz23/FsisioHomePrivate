package pt.fct.ipm2.ui.trainings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.fct.ipm.ReciclerView.TrainingsAdapter
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesAdapter
import pt.fct.ipm2.MainActivity
import pt.fct.ipm2.R
import pt.fct.ipm2.TrainingsRecyclerView.TrainingExercisesAdapter
import pt.fct.ipm2.TrainingsRecyclerView.TrainingsDB
import pt.fct.ipm2.ui.exercises.ExercisesFragmentArgs


class TrainingExercisesFragment : Fragment(),ExercisesAdapter.OnItemClickListener {


    private lateinit var trainingsViewModel: TrainingsViewModel
    private lateinit var recyclerViewTr: RecyclerView
    private lateinit var viewAdapterTr: RecyclerView.Adapter<*>
    private lateinit var viewManagerTr: RecyclerView.LayoutManager
    lateinit var root:View

    val args: TrainingExercisesFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        trainingsViewModel =
                ViewModelProvider(this).get(TrainingsViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_training_exercises, container, false)
        // val textView: TextView = root.findViewById(R.id.text_gallery)
        //exercisesViewModel.text.observe(viewLifecycleOwner, Observer {
        //   textView.text = it
        //})

        (activity as MainActivity).supportActionBar?.title = args.training.title
        val listOfTrainings = args.training.exercises
        trainrecyclerView(listOfTrainings)

        return root
    }


    fun trainrecyclerView(list: MutableList<Exercises>){
        viewManagerTr = LinearLayoutManager(this.context)
        viewAdapterTr = ExercisesAdapter(list,this)

        recyclerViewTr = root.findViewById<RecyclerView>(R.id.training__exercises).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManagerTr

            // specify an viewAdapter (see also next example)
            adapter = viewAdapterTr
        }

        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.divider))
        recyclerViewTr.addItemDecoration(divider)
    }

    override fun onItemClick(exercise: Exercises) {
        Toast.makeText(root.context, "Item ${exercise.title} clicked", Toast.LENGTH_LONG).show()
    }

}