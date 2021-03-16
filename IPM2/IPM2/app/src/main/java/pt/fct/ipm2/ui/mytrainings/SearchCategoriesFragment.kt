package pt.fct.ipm2.ui.mytrainings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesCategoriesAdapter
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesDB
import pt.fct.ipm2.MainActivity
import pt.fct.ipm2.R


class SearchCategoriesFragment : Fragment(),ExercisesCategoriesAdapter.OnItemClickListener {

    private lateinit var searchCategoriesViewModel: SearchCategoriesViewModel
    private lateinit var recyclerViewEx: RecyclerView
    private lateinit var viewAdapterEx: RecyclerView.Adapter<*>
    private lateinit var viewManagerEx: RecyclerView.LayoutManager
    private lateinit var root:View
    val args: SearchCategoriesFragmentArgs by navArgs()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchCategoriesViewModel =
                ViewModelProvider(this).get(SearchCategoriesViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_search_categories, container, false)
        // val textView: TextView = root.findViewById(R.id.text_slideshow)
        // myTrainingsViewModel.text.observe(viewLifecycleOwner, Observer {
        //textView.text = it
        //})

        (activity as MainActivity).supportActionBar?.title  ="Categories"
        val data = ExercisesDB.createDataSet()

        var datab = data.distinctBy { it.category }
        datab = datab.sortedBy { it.category }
        exercisesRecyclerView(datab as MutableList<Exercises>)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action = SearchCategoriesFragmentDirections.actionSearchCategoriesFragmentToAfterCreateTrainingFragment(args.mytraining)
            NavHostFragment.findNavController(this@SearchCategoriesFragment).navigate(action)
        }
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
        val action = SearchCategoriesFragmentDirections.actionSearchCategoriesFragmentToAddExercisesTrainingFragment(args.mytraining,category)
        NavHostFragment.findNavController(this).navigate(action)
    }

}