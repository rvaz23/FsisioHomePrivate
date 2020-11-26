package pt.fct.ipm2.ui.trainings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.fct.ipm.ReciclerView.TrainingsAdapter
import pt.fct.ipm2.R
import pt.fct.ipm2.TrainingsRecyclerView.TrainingsDB
import pt.fct.ipm2.ui.exercises.ExercisesCategoriesFragmentDirections

class TrainingsFragment : Fragment(),TrainingsAdapter.OnItemClickListener {

    private lateinit var trainingsViewModel: TrainingsViewModel
    private lateinit var recyclerViewTr: RecyclerView
    private lateinit var viewAdapterTr: RecyclerView.Adapter<*>
    private lateinit var viewManagerTr: RecyclerView.LayoutManager
    lateinit var root:View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        trainingsViewModel =
                ViewModelProvider(this).get(TrainingsViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_trainings, container, false)
       // val textView: TextView = root.findViewById(R.id.text_gallery)
        //exercisesViewModel.text.observe(viewLifecycleOwner, Observer {
         //   textView.text = it
        //})

        val listOfTrainings = TrainingsDB.createDataSet()

        Toast.makeText(root.context, "Numero de elementos: ${listOfTrainings.size}", Toast.LENGTH_LONG).show()
        trainrecyclerView(listOfTrainings)

        return root
    }



    fun trainrecyclerView(list: MutableList<Trainings>){
        viewManagerTr = GridLayoutManager(root.context, 2)//LinearLayoutManager(this)
        viewAdapterTr = TrainingsAdapter(list,this)

        recyclerViewTr = root.findViewById<RecyclerView>(R.id.Trainings).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManagerTr

            // specify an viewAdapter (see also next example)
            adapter = viewAdapterTr
        }
    }


    override fun onItemClickTr(position: Int, training: Trainings) {
        val action = TrainingsFragmentDirections.actionNavTrainingsToTrainingExercisesFragment(training)
        NavHostFragment.findNavController(this).navigate(action)
    }

}