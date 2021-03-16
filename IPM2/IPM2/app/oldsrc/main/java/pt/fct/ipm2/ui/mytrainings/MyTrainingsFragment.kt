package pt.fct.ipm2.ui.mytrainings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
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
import pt.fct.ipm2.R
import pt.fct.ipm2.ui.trainings.Trainings

class MyTrainingsFragment : Fragment(), TrainingsAdapter.OnItemClickListener {

    private lateinit var myTrainingsViewModel: MyTrainingsViewModel
    private lateinit var  createTrainBtn: FloatingActionButton
    private lateinit var root:View
    private var trainName= ""

    private lateinit var recyclerViewTr: RecyclerView
    private lateinit var viewAdapterTr: RecyclerView.Adapter<*>
    private lateinit var viewManagerTr: RecyclerView.LayoutManager

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

        trainrecyclerView(Database.mytrainings)

        return root
    }


    private fun openCreateTrainDialog() {
        //val mDialogView = LayoutInflater.from(context).inflate(R.layout.create_train_dialog, null)
        //AlertDialogBuilder
        var input = EditText(context);
        input.hint = "Nome do Treino"
        val mBuilder = AlertDialog.Builder(context)
                .setView(input)
                .setTitle("Inserir Nome do Treino:")
        //show dialog
        //login button click of custom layout
        mBuilder.setPositiveButton("OK") { dialog, which ->
            //dismiss dialog
            //get text from EditTexts of custom layout
            trainName =input.text.toString()
            //set the input text in TextView
            // mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)

            addNewTrainning(trainName)



        }
        //cancel button click of custom layout
        mBuilder.setNeutralButton("Cancel") { dialog, which ->
        }
        val dialog = mBuilder.create()
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
        if (!check){
            var list: MutableList<Exercises> = mutableListOf()
            val tr =Trainings(trainName, list)
            Database.mytrainings.add(tr)
            var snack = Snackbar.make(root, "Treino "+ trainName+" criado!", Snackbar.LENGTH_SHORT).show()
        }else{
            var snack = Snackbar.make(root, "Já existe treino com esse nome!", Snackbar.LENGTH_SHORT)
                snack.setBackgroundTint(resources.getColor(R.color.red))
            snack.show()
        }
    }


    fun trainrecyclerView(list: MutableList<Trainings>){
        viewManagerTr = LinearLayoutManager(this.context)
        viewAdapterTr = TrainingsAdapter(list,this)

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

    override fun onItemClickTr(position: Int, training: Trainings) {
        val action = MyTrainingsFragmentDirections.actionNavMytrainingsToTrainingExercisesFragment(training)
        NavHostFragment.findNavController(this).navigate(action)
    }
}