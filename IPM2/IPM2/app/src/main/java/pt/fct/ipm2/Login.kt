package pt.fct.ipm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pt.fct.ipm2.*

class Login : AppCompatActivity() {

    private lateinit var preferencesConfig: SharedPreferencesConfig
    private lateinit var intentMain: Intent
    private lateinit var checkBox: CheckBox
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var auth: FirebaseAuth

    private fun checkUser(un:String, pass:String){

        val string = Database.checkUser(un)

        if(string == null){
            username.error = getString(R.string.user_not_exists)
            username.requestFocus()
        }else if(string != pass){
            password.error = getString(R.string.wrong_password)
            password.requestFocus()
        }else{
            if(checkBox.isChecked) {
                preferencesConfig.writeLoginStatus(true)
            }

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val txt = findViewById<TextView>(R.id.create)
        val login = findViewById<TextView>(R.id.login)
        checkBox = findViewById(R.id.checkBox)
        username = findViewById(R.id.editTextTextPersonName)
        password = findViewById(R.id.editTextTextPassword)
        intentMain = Intent(this, MainActivity::class.java)


// ...
// Initialize Firebase Auth
        FirebaseApp.initializeApp(this);
        auth = Firebase.auth


        login.setOnClickListener(){
            val userName = username.text.toString().trim()
            val pass = password.text.toString().trim()

            auth.signInWithEmailAndPassword(userName, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        val intent = Intent(this,MainActivity::class.java)
                        this.finish()
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }

        txt.setOnClickListener(){
            val intent = Intent(this,Register::class.java)
            startActivity(intent)

        }

    /*
        preferencesConfig =
            SharedPreferencesConfig(
                applicationContext
            )

        if(preferencesConfig.readLoginStatus()){
            this.finish()
            startActivity(intentMain)
        }


        var user=User("admin","admin","admin","2212")
        Database.addUser(user)



     */
    }
}