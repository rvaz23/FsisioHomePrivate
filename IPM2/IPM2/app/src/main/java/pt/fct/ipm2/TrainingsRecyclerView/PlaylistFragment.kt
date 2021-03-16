package pt.fct.ipm2.ui.home

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import pt.fct.ipm2.Database
import pt.fct.ipm2.ExercisesRecyclerView.Exercises
import pt.fct.ipm2.MainActivity
import pt.fct.ipm2.R
import pt.fct.ipm2.TrainingsRecyclerView.TrainingsDB
import pt.fct.ipm2.ui.exercises.ExercisesFragmentArgs
import pt.fct.ipm2.ui.trainings.Trainings
import java.util.*
import kotlin.collections.ArrayList


class PlaylistFragment : Fragment() {

    private lateinit var homeViewModel: ShowExerciseViewModel
    private lateinit var recyclerViewEx: RecyclerView
    private lateinit var viewAdapterEx: RecyclerView.Adapter<*>
    private lateinit var viewManagerEx: RecyclerView.LayoutManager

    private lateinit var recyclerViewTr: RecyclerView
    private lateinit var viewAdapterTr: RecyclerView.Adapter<*>
    private lateinit var viewManagerTr: RecyclerView.LayoutManager

    lateinit var root:View

    val args: PlaylistFragmentArgs by navArgs()

    private lateinit var  countdownText: TextView
    private lateinit var  startButton: Button

    private lateinit var  favButton: Button

    private lateinit var  reset: Button

    private lateinit var countdown_timer: CountDownTimer
    private var isRunning: Boolean = false
    private var time_in_milli_seconds = 0L
    private var initalTime = ""

    private lateinit var  addToTrain: Button

    private lateinit var exercises:MutableList<Exercises>

    private var checkedColorsArray = BooleanArray(0)


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(ShowExerciseViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_playlist, container, false)


        exercises = args.playlist.exercises

        (activity as MainActivity).supportActionBar?.title = exercises[0].title
        root.findViewById<TextView>(R.id.textView4).setText(exercises[0].description)
        root.findViewById<ImageView>(R.id.imageView6).setImageResource(exercises[0].image)
        root.findViewById<TextView>(R.id.timer).text = exercises[0].duration


        //timer//////////////////////
        countdownText= root.findViewById(R.id.timer)
        initalTime=countdownText.text.toString().substring(0,2)///
        time_in_milli_seconds = initalTime.toLong()*60000L
        startButton= root.findViewById(R.id.countdown_button)
        reset= root.findViewById(R.id.reset)


        startButton.setOnClickListener {
            if (isRunning) {
                val snack = Snackbar.make(root,"Pausou o  Temporizador",Snackbar.LENGTH_SHORT)
                snack.show()
                pauseTimer()
            } else {
                val snack = Snackbar.make(root,"Iniciou o Temporizador",Snackbar.LENGTH_SHORT)
                snack.show()
                startTimer()
            }
        }

        reset.setOnClickListener {
            if(!isRunning){
                resetTimer()
                val snack = Snackbar.make(root,"Reiniciou o temporizador",Snackbar.LENGTH_SHORT)
                snack.show()
            }else{
                val snack = Snackbar.make(root,"Necessário Pausar Para Reiniciar",Snackbar.LENGTH_SHORT)
                snack.show()
            }
        }
        //FavButton//////////////////////
        favButton= root.findViewById(R.id.favBtn)
        var isFav =false
        if (Database.favorites.contains(exercises[0])){
            isFav=true
            favButton.setBackgroundResource(R.drawable.starreenchida)
        }
        favButton.setOnClickListener{
            if (isFav) {
                favButton.setBackgroundResource(R.drawable.star)
                Database.favorites.remove(exercises[0])
                isFav=false
                val snack = Snackbar.make(root,"Retirou o exercicio do Favoritos",Snackbar.LENGTH_SHORT)
                snack.show()
            } else {
                favButton.setBackgroundResource(R.drawable.starreenchida)
                val snack = Snackbar.make(root,"Adicionou o exercicio do Favoritos",Snackbar.LENGTH_SHORT)
                Database.favorites.add(exercises[0])
                isFav=true
                snack.show()
            }
        }
        //addToMyTrains//////////////////////////
        addToTrain=root.findViewById(R.id.addToTrain)
        addToTrain.setOnClickListener {
            createAlertDialog()
        }

        val next = root.findViewById<Button>(R.id.next).setOnClickListener(){
            next()
        }


        return root
    }


    fun next(){
        if (exercises.size>1){
            args.playlist.exercises.removeFirst()
            //ir para o proximo
            val action = PlaylistFragmentDirections.actionPlaylistFragmentSelf(args.playlist)
            NavHostFragment.findNavController(this).navigate(action)
        }else{
            //retornar a treino
                for (i in Database.mytrainings){
                    Log.d("dsadsad ",args.playlist.title)
                    Log.d("database", i.title)
                }

                var i =Database.mytrainings.indexOfFirst { it.title.trim().equals(args.playlist.title.trim(),true) }
                if (i<0){
                    i =TrainingsDB.createDataSet().indexOfFirst { it.title.trim().equals(args.playlist.title.trim(),true)}
                        val action = PlaylistFragmentDirections.actionPlaylistFragmentToTrainingExercisesFragment(TrainingsDB.createDataSet()[i])
                        NavHostFragment.findNavController(this).navigate(action)
                }else{
                    val action = PlaylistFragmentDirections.actionPlaylistFragmentToTrainingExercisesFragment(Database.mytrainings[i])
                    NavHostFragment.findNavController(this).navigate(action)
                }

        }

    }



    private fun createAlertDialog() {

        val builder = AlertDialog.Builder(context)
        // String array for alert dialog multi choice items


        val colorsArray = arrayOfNulls<String>(Database.mytrainings.size)
        //val aux = Database.trainingsNames()
        //colorsArray.set(0,aux[0])
        //colorsArray.set(1,aux[1])

        var title = EditText(context);
        var typeFace: Typeface? = context?.let { ResourcesCompat.getFont(it, R.font.baloo) }
        title.setTypeface(typeFace)
        title.hint = "Nome do Treino"
        var tv = TextView(context);
        tv.text = "Selecionar Treino:"
        tv.textSize = 30.0F
        tv.gravity = Gravity.CENTER
        tv.setTypeface(typeFace)

        checkedColorsArray= BooleanArray(Database.mytrainings.size)
        //
        for (i in Database.mytrainings){
            val index =Database.mytrainings.indexOf(i)
            val b =i.exercises.contains(exercises[0])
            colorsArray.set(index,i.title)
            if (b){
                checkedColorsArray[Database.mytrainings.indexOf(i)] = true
            }
        }


        //checkedColorsArray = booleanArrayOf(false, // Black checked
        //       false// Orange
        //)
        // Boolean array for initial selected items
        // Convert the color array to list
        val colorsList = Arrays.asList(*colorsArray)
        //setTitle
        builder.setCustomTitle(tv)

        if( colorsList.isEmpty()){
            var input = TextView(context);
            input.setPadding(60,0,0,0)
            input.setTypeface(typeFace)
            input.text = "Não existem treinos criados!"
            builder.setView(input)
        }


        //set multichoice
        builder.setMultiChoiceItems(colorsArray, checkedColorsArray) { dialog, which, isChecked ->
            // Update the current focused item's checked status
            checkedColorsArray[which] = isChecked
            // Get the current focused item
            val currentItem = colorsList[which]
            // Notify the current action
            //val snack = Snackbar.make(root, currentItem + " " + isChecked, Snackbar.LENGTH_SHORT)
            //snack.show()
        }


        // Set the positive/yes button click listener
        builder.setPositiveButton("OK") { dialog, which ->
            var data = ""
            for (i in checkedColorsArray.indices) {
                val checked = checkedColorsArray[i]
                if (checked) {
                    data = colorsArray[i].toString()
                    for (i in Database.mytrainings){
                        if (i.title.trim().equals(data.trim(),true)){
                            if (!i.exercises.contains(exercises[0])){
                                i.exercises.add(exercises[0])
                            }
                            val snack = Snackbar.make(root, "Meus treinos foram atualizados", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    data = colorsArray[i].toString()
                    for (i in Database.mytrainings){
                        if (i.title.trim().equals(data.trim(),true)){
                            if (i.exercises.contains(exercises[0])){
                                i.exercises.remove(exercises[0])
                            }
                            val snack = Snackbar.make(root, "Meus treinos foram atualizados", Snackbar.LENGTH_SHORT).show()
                        }
                    }

                }
                /*val snack :Snackbar
                if(data.equals("")){
                    snack = Snackbar.make(root, "Não Adicionou a Nenhum Treino", Snackbar.LENGTH_SHORT)
                }else{
                    for (i in Database.mytrainings){
                        if (i.title.trim().equals(data.trim(),true)){
                            i.exercises.add(args.exercise)
                        }
                    }
                    Log.e("dasdsadadsadas",data)
                    snack = Snackbar.make(root,"Adicionado a "+ data, Snackbar.LENGTH_SHORT)
                }*/
                // snack.show()
            }
        }
        // Set the neutral/cancel button click listener
        builder.setNeutralButton("Cancel") { dialog, which ->
            // Do something when click the neutral button
        }
        val dialog = builder.create()

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

    private fun pauseTimer() {

        startButton.text= "Start"
        startButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle_play,0,0,0)

        countdown_timer.cancel()
        isRunning = false
    }

    private fun startTimer() {
        countdown_timer = object : CountDownTimer(time_in_milli_seconds, 1000) {
            override fun onFinish() {
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }
        }
        countdown_timer.start()

        isRunning = true

        startButton.text="Pause"
        startButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_pause_circle_outline_24,0,0,0)


    }

    private fun resetTimer() {
        time_in_milli_seconds = initalTime.toLong()*60000L
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        countdownText.text = "0$minute:$seconds"+"0"
    }

    private fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        countdownText.text = "0$minute:$seconds"
    }

    /*

override fun onPositiveButtonClicker(list: Array<out String>?, selectedItemList: ArrayList<String>?) {
    var stringBuilder = StringBuilder()
    stringBuilder.append("Selected Choices =")
    for(str in selectedItemList!!){
        stringBuilder.append(str+" ")
    }
    tvSelectedChoices.setText(stringBuilder)
}

override fun onNegativeButtonClicker() {
    tvSelectedChoices.setText("Dialog Cancel")
}*/


}