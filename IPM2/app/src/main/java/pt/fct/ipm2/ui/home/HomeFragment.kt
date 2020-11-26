package pt.fct.ipm2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesCategoriesAdapter
import pt.fct.ipm.ReciclerView.TrainingsAdapter
import pt.fct.ipm2.R
import pt.fct.ipm2.TrainingsRecyclerView.TrainingsDB
import pt.fct.ipm2.ui.trainings.Trainings

class HomeFragment : Fragment(), ExercisesCategoriesAdapter.OnItemClickListener,TrainingsAdapter.OnItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerViewEx: RecyclerView
    private lateinit var viewAdapterEx: RecyclerView.Adapter<*>
    private lateinit var viewManagerEx: RecyclerView.LayoutManager

    private lateinit var recyclerViewTr: RecyclerView
    private lateinit var viewAdapterTr: RecyclerView.Adapter<*>
    private lateinit var viewManagerTr: RecyclerView.LayoutManager

    lateinit var root:View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
         root = inflater.inflate(R.layout.fragment_home, container, false)

        val showallex = root.findViewById<TextView>(R.id.showAllEx)
        showallex.setOnClickListener(){

            NavHostFragment.findNavController(this).navigate(R.id.action_nav_home_to_nav_exercises_categories)
        }

        val ahowalltr= root.findViewById<TextView>(R.id.showAllTre)
        ahowalltr.setOnClickListener(){
            NavHostFragment.findNavController(this).navigate(R.id.action_nav_home_to_nav_trainings)
        }

        //val textView: TextView = root.findViewById(R.id.text_home)
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
          //  textView.text = it
        //})


        val object1 = Exercises("ombro","Ombro","ombro","Explicar como fazer o exercicio")
        val object2 = Exercises("ombro","perna","ombro","Explicar como fazer o exercicio")
        val object4 = Exercises("ombro","Ombro","ombro","Explicar como fazer o exercicio")
        val object5 = Exercises("ombro","perna","ombro","Explicar como fazer o exercicio")


        val listOfExercisesTypes: MutableList<Exercises> = arrayListOf(
            object1,
            object2,
            object5,
            object4,
        )

        val listTrainings = TrainingsDB.createDataSet()
        val listOfTrainings = listTrainings.take(4)

        trainrecyclerView(listOfTrainings as MutableList<Trainings>)
        exercisesRecyclerView(listOfExercisesTypes)

        return root
    }


    fun trainrecyclerView( list :MutableList<Trainings>){
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

    fun exercisesRecyclerView( list :MutableList<Exercises>){
        viewManagerEx = GridLayoutManager(root.context, 2)//LinearLayoutManager(this)
        viewAdapterEx = ExercisesCategoriesAdapter(list,this)

        recyclerViewEx = root.findViewById<RecyclerView>(R.id.Exercises).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManagerEx

            // specify an viewAdapter (see also next example)
            adapter = viewAdapterEx
        }
    }

    override fun onItemClick(position: Int, category: String) {
        val action =HomeFragmentDirections.actionNavHomeToNavExercises(category)
        NavHostFragment.findNavController(this).navigate(action)

    }

    override fun onItemClickTr(position: Int, training: Trainings) {
        val action=HomeFragmentDirections.actionNavHomeToTrainingExercisesFragment(training)
        NavHostFragment.findNavController(this).navigate(action)
    }

}