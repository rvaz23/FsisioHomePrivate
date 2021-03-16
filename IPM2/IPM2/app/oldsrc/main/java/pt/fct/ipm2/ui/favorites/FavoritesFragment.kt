package pt.fct.ipm2.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import pt.fct.ipm2.Database
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesAdapter
import pt.fct.ipm2.MainActivity
import pt.fct.ipm2.R
import pt.fct.ipm2.ui.mytrainings.MyTrainingsViewModel

class FavoritesFragment : Fragment(), ExercisesAdapter.OnItemClickListener {

    private lateinit var favViewModel: FavoritesViewModel
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
        favViewModel =
                ViewModelProvider(this).get(FavoritesViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_favorites, container, false)
        // val textView: TextView = root.findViewById(R.id.text_slideshow)
        // myTrainingsViewModel.text.observe(viewLifecycleOwner, Observer {
        //textView.text = it
        //})

        exercisesRecyclerView(Database.favorites)
        (activity as MainActivity).supportActionBar?.title = "Favoritos"
        return root
    }



    fun exercisesRecyclerView(list: MutableList<Exercises>){
        viewManagerEx = LinearLayoutManager(root.context) //GridLayoutManager(, 2)//LinearLayoutManager(this)
        viewAdapterEx = ExercisesAdapter(list, this)

        recyclerViewEx = root.findViewById<RecyclerView>(R.id.Exercises).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManagerEx

            // specify an viewAdapter (see also next example)
            adapter = viewAdapterEx
        }

        val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.divider))
        recyclerViewEx.addItemDecoration(divider)

    }

    override fun onItemClick(exercise: Exercises) {

        val action = FavoritesFragmentDirections.actionNavFavoritesToShowExerciseFragment(exercise)
        NavHostFragment.findNavController(this).navigate(action)
    }

}