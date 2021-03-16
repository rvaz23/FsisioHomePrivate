package pt.fct.ipm2.ui.mytrainings

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import pt.fct.ipm.ReciclerView.TrainingsAdapter
import pt.fct.ipm2.Database
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.MyTrainingRecyclerView.MyTrainingAdapter
import pt.fct.ipm2.R
import pt.fct.ipm2.TrainingsRecyclerView.TrainingsDB
import pt.fct.ipm2.ui.trainings.Trainings

class MyTrainingsFragment : Fragment(), MyTrainingAdapter.OnItemClickListener  {

    private lateinit var myTrainingsViewModel: MyTrainingsViewModel
    private lateinit var  createTrainBtn: FloatingActionButton
    private lateinit var root:View
    private var trainName= ""

    private lateinit var recyclerViewTr: RecyclerView
    private lateinit var viewAdapterTr: RecyclerView.Adapter<*>
    private lateinit var viewManagerTr: RecyclerView.LayoutManager
    private lateinit var datab:MutableList<Trainings>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        myTrainingsViewModel =
                ViewModelProvider(this).get(MyTrainingsViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_mytrainings, container, false)
       // val textView: TextView = root.findViewById(R.id.text_slideshow)
       // myTrainingsViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        //})



        createTrainBtn= root.findViewById(R.id.createTrainButton)
        createTrainBtn.setOnClickListener{
            openCreateTrainDialog()
        }

        var d= Database.mytrainings
        if(d.isEmpty()) {
            trainrecyclerView(d)
        }else{
            datab = d.sortedBy { it.title } as MutableList<Trainings>
            trainrecyclerView(datab)
            root.findViewById<TextView>(R.id.textView5).visibility = View.GONE
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            NavHostFragment.findNavController(this@MyTrainingsFragment).navigate(R.id.nav_home)
        }


        return root
    }


    private fun openCreateTrainDialog() {
        //val mDialogView = LayoutInflater.from(context).inflate(R.layout.create_train_dialog, null)
        //AlertDialogBuilder
        var input = EditText(context);
        var typeFace: Typeface? = context?.let { ResourcesCompat.getFont(it, R.font.baloo) }
        input.setTypeface(typeFace)
        input.hint = "Nome do Treino"
        var tv = TextView(context);
        tv.text = "Inserir Nome do Treino:"
        tv.textSize = 30.0F
        tv.gravity = Gravity.CENTER
        tv.setTypeface(typeFace)

        val mBuilder = AlertDialog.Builder(context)
                .setView(input)
            .setCustomTitle(tv)
        //show dialog
        //login button click of custom layout
        mBuilder.setPositiveButton("OK") { dialog, which ->
            //dismiss dialog
            //get text from EditTexts of custom layout
            trainName = input.text.toString()
            //set the input text in TextView
            // mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)

            if (trainName.trim() != ""){
                addNewTrainning(trainName)
            }

        }
        //cancel button click of custom layout
        mBuilder.setNeutralButton("Cancel") { dialog, which ->
        }
        val dialog = mBuilder.create()

        dialog.setOnShowListener {
            var b1 = dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
            var b2 = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            b1.setTypeface(typeFace)
            b1.textSize = 20.0F
            b2.setTypeface(typeFace)
            b2.textSize = 20.0F
        }

        // Display the alert dialog on interface
        dialog.show()
    }

    fun addNewTrainning(name:String){
        var check=false
        for (i in Database.mytrainings){
            if (i.title==name){
                check=true
            }
        }
        for (i in TrainingsDB.createDataSet()){
            if (i.title==name){
                check=true
            }
        }
        if (!check){
            var list: MutableList<Exercises> = mutableListOf()
            //val tr =Trainings(trainName, list)
            //Database.mytrainings.add(tr)
            //var snack = Snackbar.make(root, "Treino "+ trainName+" criado!", Snackbar.LENGTH_SHORT).show()
            val nwtr=Trainings(trainName, arrayListOf<Exercises>())
            val action = MyTrainingsFragmentDirections.actionNavMytrainingsToAfterCreateTrainingFragment(nwtr)
            NavHostFragment.findNavController(this).navigate(action)
        }else{
            var snack = Snackbar.make(root, "Já existe treino com esse nome!", Snackbar.LENGTH_SHORT)
                snack.setBackgroundTint(resources.getColor(R.color.red))
            snack.show()
        }
    }


    fun trainrecyclerView(list: MutableList<Trainings>){
        viewManagerTr = LinearLayoutManager(this.context)
        viewAdapterTr = MyTrainingAdapter(list,this)

        recyclerViewTr = root.findViewById<RecyclerView>(R.id.mytrainings).apply {
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


    override fun onItemClick(training: Trainings) {
        val action = MyTrainingsFragmentDirections.actionNavMytrainingsToTrainingExercisesFragment(training)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onEditClick(training: Trainings, position: Int) {
        val exercises = arrayListOf<Exercises>()
        exercises.addAll(training.exercises)
        val nwtr = Trainings(training.title,exercises)
        val action = MyTrainingsFragmentDirections.actionNavMytrainingsToAfterCreateTrainingFragment(nwtr)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onDeleteClick(training: Trainings, position:Int) {
        Database.mytrainings.remove(training)
        viewAdapterTr.notifyItemRemoved(position)
        var action = MyTrainingsFragmentDirections.actionNavMytrainingsSelf()
        NavHostFragment.findNavController(this).navigate(action)
        Snackbar.make(root, "Treino removido com sucesso", Snackbar.LENGTH_SHORT).show()
    }


}
