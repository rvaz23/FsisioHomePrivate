package pt.fct.ipm2.ui.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesCategoriesAdapter
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesDB
import pt.fct.ipm2.R


class ExercisesCategoriesFragment : Fragment(), ExercisesCategoriesAdapter.OnItemClickListener {

    private lateinit var exercisesCategoriesViewModel: ExercisesCategoriesViewModel
    private lateinit var recyclerViewEx: RecyclerView
    private lateinit var viewAdapterEx: RecyclerView.Adapter<*>
    private lateinit var viewManagerEx: RecyclerView.LayoutManager
    lateinit var root:View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        exercisesCategoriesViewModel =
                ViewModelProvider(this).get(ExercisesCategoriesViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_exercisescategories, container, false)
       // val textView: TextView = root.findViewById(R.id.text_gallery)
        //exercisesViewModel.text.observe(viewLifecycleOwner, Observer {
         //   textView.text = it
        //})


    val data = ExercisesDB.createDataSet()

        var datab = data.distinctBy { it.category }
        datab = datab.sortedBy { it.category }
        exercisesRecyclerView(datab as MutableList<Exercises>)

        return root
    }



    fun exercisesRecyclerView( list :MutableList<Exercises>){
        viewManagerEx = GridLayoutManager(root.context, 2)//LinearLayoutManager(this)
        viewAdapterEx = ExercisesCategoriesAdapter(list,this,resources)

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
        val action = ExercisesCategoriesFragmentDirections.actionNavExercisescategoriesToNavExercises(category)
        NavHostFragment.findNavController(this).navigate(action)
    }

}