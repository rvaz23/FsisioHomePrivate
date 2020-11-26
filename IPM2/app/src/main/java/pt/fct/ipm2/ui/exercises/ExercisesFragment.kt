package pt.fct.ipm2.ui.exercises

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesAdapter
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesDB
import pt.fct.ipm2.MainActivity
import pt.fct.ipm2.R


class ExercisesFragment() :Fragment(), ExercisesAdapter.OnItemClickListener {


    private lateinit var exercisesViewModel: ExercisesViewModel
    private lateinit var recyclerViewEx: RecyclerView
    private lateinit var viewAdapterEx: RecyclerView.Adapter<*>
    private lateinit var viewManagerEx: RecyclerView.LayoutManager
    lateinit var root:View
    val args: ExercisesFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //exercisesCategoriesViewModel =
        //  ViewModelProvider(this).get(ExercisesCategoriesViewModel::class.java)
        exercisesViewModel =
                ViewModelProvider(this).get(ExercisesViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_exercises, container, false)
        // val textView: TextView = root.findViewById(R.id.text_gallery)
        //exercisesViewModel.text.observe(viewLifecycleOwner, Observer {
        //   textView.text = it
        //})
        (activity as MainActivity).supportActionBar?.title = args.category

        val data = ExercisesDB.createDataSet()
        data.removeIf { x -> x.category != args.category }

        exercisesRecyclerView(data)

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

        val divider =DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.divider))
        recyclerViewEx.addItemDecoration(divider)

    }

    override fun onItemClick(exercise: Exercises) {
        Toast.makeText(root.context, "Item ${exercise.title} clicked", Toast.LENGTH_LONG).show()
    }


}