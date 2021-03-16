package pt.fct.ipm2.ui.mytrainings

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import pt.fct.ipm2.Database
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesAdapter
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesCategoriesAdapter
import pt.fct.ipm2.ExercisesRecyclerView.ExercisesDB
import pt.fct.ipm2.MainActivity
import pt.fct.ipm2.MyTrainingRecyclerView.MyTrainingExercisesEditAdapter
import pt.fct.ipm2.R
import pt.fct.ipm2.ui.exercises.ExercisesCategoriesViewModel
import pt.fct.ipm2.ui.home.ShowExerciseFragmentArgs
import pt.fct.ipm2.ui.trainings.Trainings
import java.text.FieldPosition

class AfterCreateTrainingFragment:Fragment(),MyTrainingExercisesEditAdapter.OnItemClickListener {

    private lateinit var afterCreateTrainingViewModel: AfterCreateTrainingViewModel
    private lateinit var recyclerViewEx: RecyclerView
    private lateinit var viewAdapterEx: RecyclerView.Adapter<*>
    private lateinit var viewManagerEx: RecyclerView.LayoutManager
    lateinit var root:View
    lateinit var addbtn: FloatingActionButton


    val args: AfterCreateTrainingFragmentArgs by navArgs()
    lateinit var training: Trainings

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        afterCreateTrainingViewModel =
                ViewModelProvider(this).get(AfterCreateTrainingViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_aftercreatetraining, container, false)
        // val textView: TextView = root.findViewById(R.id.text_gallery)

        //exercisesViewModel.text.observe(viewLifecycleOwner, Observer {
        //   textView.text = it
        //})

        training=args.mytraining
        addbtn= root.findViewById(R.id.addexercises)
        addbtn.setOnClickListener{
            addexercises()
        }

        (activity as MainActivity).supportActionBar?.title  =args.mytraining.title

        exercisesRecyclerView(training.exercises)

        val confirm = root.findViewById<Button>(R.id.confirm).setOnClickListener {
            // atualizar meus treinos
            confirmitems()

        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action = AfterCreateTrainingFragmentDirections.actionAfterCreateTrainingFragmentToNavMytrainings()
            NavHostFragment.findNavController(this@AfterCreateTrainingFragment).navigate(action)
        }

        return root
    }

    fun confirmitems(){
        var i =Database.mytrainings.indexOfFirst { it.title == args.mytraining.title }
        if (i<0){
            if(!args.mytraining.exercises.isEmpty()){
                Database.mytrainings.add(args.mytraining)
                val snack = Snackbar.make(root,"Treino criado com sucesso", Snackbar.LENGTH_SHORT)
                snack.show()
                val action = AfterCreateTrainingFragmentDirections.actionAfterCreateTrainingFragmentToNavMytrainings()
                NavHostFragment.findNavController(this).navigate(action)
            }else{
                discardialog(i)
            }
        }else{
            if(args.mytraining.exercises.isEmpty()){
                discardialog(i)
            }else{
                Database.mytrainings[i] = args.mytraining
                val snack = Snackbar.make(root,"Treino atualizado com sucesso", Snackbar.LENGTH_SHORT)
                snack.show()
                val action = AfterCreateTrainingFragmentDirections.actionAfterCreateTrainingFragmentToNavMytrainings()
                NavHostFragment.findNavController(this).navigate(action)
            }

        }




    }

    fun discardialog(index: Int ){
            //val mDialogView = LayoutInflater.from(context).inflate(R.layout.create_train_dialog, null)
            //AlertDialogBuilder
            var typeFace: Typeface? = context?.let { ResourcesCompat.getFont(it, R.font.baloo) }

            var tv = TextView(context);
            tv.text = "Deseja remover o treino?"
            tv.textSize = 30.0F
            tv.gravity = Gravity.CENTER
            tv.setTypeface(typeFace)


            var input = TextView(context);
            input.setPadding(60,0,0,0)
            input.setTypeface(typeFace)
            input.text = "Não podem existir treinos sem exercicios"

            val mBuilder = AlertDialog.Builder(context)
                    .setCustomTitle(tv).setView(input)
            //show dialog
            //login button click of custom layout
            mBuilder.setPositiveButton("OK") { dialog, which ->
                if (index>=0){
                    Database.mytrainings.removeAt(index)
                    val snack = Snackbar.make(root,"Treino foi eliminado ", Snackbar.LENGTH_SHORT)
                    snack.show()
                }else{
                    val snack = Snackbar.make(root,"Treino não foi criado", Snackbar.LENGTH_SHORT)
                    snack.show()
                }
                val action = AfterCreateTrainingFragmentDirections.actionAfterCreateTrainingFragmentToNavMytrainings()
                NavHostFragment.findNavController(this).navigate(action)
            }
            //cancel button click of custom layout
            mBuilder.setNeutralButton("Cancelar") { dialog, which ->
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

    fun addexercises(){
        val action = AfterCreateTrainingFragmentDirections.actionAfterCreateTrainingFragmentToSearchCategoriesFragment(training)
        NavHostFragment.findNavController(this).navigate(action)
    }

    fun exercisesRecyclerView(list: MutableList<Exercises>){
        viewManagerEx = LinearLayoutManager(root.context) //GridLayoutManager(, 2)//LinearLayoutManager(this)
        viewAdapterEx = MyTrainingExercisesEditAdapter(list, this)

        recyclerViewEx = root.findViewById<RecyclerView>(R.id.recyclerView).apply {
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

        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP + ItemTouchHelper.DOWN, 0) {

            override fun onMove(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder
            ): Boolean {
                (viewAdapterEx as MyTrainingExercisesEditAdapter).onItemMove(viewHolder?.adapterPosition, target?.adapterPosition);
                return true
            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }
        })

        touchHelper.attachToRecyclerView(recyclerViewEx)

    }

    override fun onItemClick(exercise: Exercises) {


    }

    override fun onDeleteClick(exercise: Exercises,position: Int) {
        args.mytraining.exercises.remove(exercise)
        viewAdapterEx.notifyItemRemoved(position)
    }
}