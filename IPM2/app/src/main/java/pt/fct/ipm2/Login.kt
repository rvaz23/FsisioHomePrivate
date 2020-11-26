package pt.fct.ipm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.Main
import pt.fct.ipm2.MainActivity
import pt.fct.ipm2.R

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val txt = findViewById<TextView>(R.id.create)
        val login = findViewById<TextView>(R.id.login)



        txt.setOnClickListener(){
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }

        login.setOnClickListener(){
            this.finish()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}