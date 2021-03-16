package pt.fct.ipm

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.fct.ipm2.Database
import pt.fct.ipm2.R
import pt.fct.ipm2.User
import java.time.Instant
import java.time.format.DateTimeFormatter

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val btn = findViewById<Button>(R.id.regist)
        val back = findViewById<ImageView>(R.id.back)
        val un = findViewById<EditText>(R.id.editTextTextPersonName3)
        val e = findViewById<EditText>(R.id.editTextTextPersonName2)
        val pass = findViewById<EditText>(R.id.editTextTextPassword2)
        val pass2 = findViewById<EditText>(R.id.editTextTextPassword3)
        val d = findViewById<EditText>(R.id.editTextDate)
        val progress = findViewById<ProgressBar>(R.id.progressBar)
        progress.visibility= View.INVISIBLE

        btn.setOnClickListener(){
           progress.visibility= View.VISIBLE
            val userName = un.text.toString()
            val email = e.text.toString()
            val password = pass.text.toString()
            val password2 = pass2.text.toString()
            val date = d.text.toString()

            if(userName.isEmpty()) {
                un.error = getString(R.string.invalid_username)
                un.requestFocus()
            }else if(email.isEmpty()) {
                e.error = getString(R.string.invalid_email)
                e.requestFocus()
            }else if(password.isEmpty()) {
                pass.error = getString(R.string.invalid_password)
                pass.requestFocus()
            }else if(password2.isEmpty()) {
                pass2.error = getString(R.string.invalid_password2)
                pass2.requestFocus()
            }else if(date.isEmpty()) {
                d.error = getString(R.string.invalid_date)
                d.requestFocus()
            }else if(password!=password2) {
                pass.error = getString(R.string.passwords_not_match)
                pass.requestFocus()
            }else{
                //val u = User(userName,email,password,date)
                //Database.addUser(u)
                var user = registerFirebase(email,password)
                Log.d("user", user.toString())
                if (user!=null){
                    this.finish()
                    val intent = Intent(this,Login::class.java)
                    startActivity(intent)
                }
            }
        }

        back.setOnClickListener(){
            this.finish()
            val intent = Intent(this,Login::class.java)
        }


    }


    fun registerFirebase(email:String,password:String): FirebaseUser? {
        //birthday,country, id
        val progress = findViewById<ProgressBar>(R.id.progressBar)
        FirebaseApp.initializeApp(this);
        val db = Firebase.firestore
        auth = Firebase.auth
        var user = auth.currentUser
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    user = auth.currentUser
                    var birthday = Timestamp(
                        200L,
                        123)
                    add_to_db(email,birthday,"","")
                } else {
                    // If sign in fails, display a message to the user.
                    user = auth.currentUser
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
                progress.visibility= View.INVISIBLE
            }
        return user
    }

    fun add_to_db(email:String,birthday:Timestamp,country:String,id:String){
        val user = hashMapOf(
            "email" to email,
            "birthday" to birthday,
            "country" to country,
            "id" to id
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }

    }

}