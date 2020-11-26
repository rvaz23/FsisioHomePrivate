package pt.fct.ipm2.ui.mytrainings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pt.fct.ipm2.R

class MyTrainingsFragment : Fragment() {

    private lateinit var myTrainingsViewModel: MyTrainingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        myTrainingsViewModel =
                ViewModelProvider(this).get(MyTrainingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_mytrainings, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        myTrainingsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}