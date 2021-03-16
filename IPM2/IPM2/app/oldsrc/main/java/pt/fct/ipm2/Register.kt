package pt.fct.ipm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import pt.fct.ipm2.R

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val btn = findViewById<Button>(R.id.regist)
        val back = findViewById<ImageView>(R.id.back)




        btn.setOnClickListener(){
            this.finish()
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        back.setOnClickListener(){
            this.finish()
            val intent = Intent(this,Login::class.java)
        }


    }

}