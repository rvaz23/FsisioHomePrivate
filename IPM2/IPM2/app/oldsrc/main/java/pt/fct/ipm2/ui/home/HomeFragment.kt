package pt.fct.ipm2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

class HomeFragment : Fragment() {

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

        val shoulder= root.findViewById<ImageView>(R.id.shoulder_category)
        shoulder.setOnClickListener(){
            val action = HomeFragmentDirections.actionNavHomeToNavExercises("Ombro")
            NavHostFragment.findNavController(this).navigate(action)
        }

        val cervical = root.findViewById<ImageView>(R.id.cervical_category)
        cervical.setOnClickListener(){
            val action=HomeFragmentDirections.actionNavHomeToNavExercises("Cervical")
            NavHostFragment.findNavController(this).navigate(action)
        }

        val hand = root.findViewById<ImageView>(R.id.hand_category)
        hand.setOnClickListener(){
            val action=HomeFragmentDirections.actionNavHomeToNavExercises("Mao")
            NavHostFragment.findNavController(this).navigate(action)
        }

        val knee = root.findViewById<ImageView>(R.id.knee_category)
        knee.setOnClickListener(){
            val action=HomeFragmentDirections.actionNavHomeToNavExercises("Joelho")
            NavHostFragment.findNavController(this).navigate(action)
        }

        //val textView: TextView = root.findViewById(R.id.text_home)
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
          //  textView.text = it
        //})



        return root
    }


}