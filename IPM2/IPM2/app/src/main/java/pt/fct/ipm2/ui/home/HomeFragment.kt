package pt.fct.ipm2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
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
            val action=HomeFragmentDirections.actionNavHomeToNavExercises("Mão")
            NavHostFragment.findNavController(this).navigate(action)
        }

        val knee = root.findViewById<ImageView>(R.id.feet_category)
        knee.setOnClickListener(){
            val action=HomeFragmentDirections.actionNavHomeToNavExercises("Pé")
            NavHostFragment.findNavController(this).navigate(action)
        }
        val data =TrainingsDB.createDataSet()
        val streches = root.findViewById<ImageView>(R.id.alongamentos)
            streches.setOnClickListener {
                val i = data.indexOfFirst { it.title.trim().equals("Alongamento da Perna".trim()) }
                val action =HomeFragmentDirections.actionNavHomeToTrainingExercisesFragment(data.get(i))
                NavHostFragment.findNavController(this).navigate(action)
            }

        val entorse = root.findViewById<ImageView>(R.id.entorse)
        entorse.setOnClickListener {
            val i = data.indexOfFirst { it.title.trim().equals("Entorse do Calcanhar".trim()) }
            val action = HomeFragmentDirections.actionNavHomeToTrainingExercisesFragment(data.get(i))
            NavHostFragment.findNavController(this).navigate(action)
        }

            val tendinite = root.findViewById<ImageView>(R.id.tendinite)
            tendinite.setOnClickListener {
                val i = data.indexOfFirst { it.title.trim().equals("Tendinite no Pulso".trim()) }
                val action =HomeFragmentDirections.actionNavHomeToTrainingExercisesFragment(data.get(i))
                NavHostFragment.findNavController(this).navigate(action)
            }

            val torcicolo = root.findViewById<ImageView>(R.id.torcicolo)
            torcicolo.setOnClickListener {
                val i = data.indexOfFirst { it.title.trim().equals("Torcicolo".trim()) }
                val action =HomeFragmentDirections.actionNavHomeToTrainingExercisesFragment(data.get(i))
                NavHostFragment.findNavController(this).navigate(action)
            }

        //val textView: TextView = root.findViewById(R.id.text_home)
        //homeViewModel.text.observe(viewLifecycleOwner, Observer {
          //  textView.text = it
        //})

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            activity?.finishAffinity()
        }

        return root
    }


}