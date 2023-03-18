package com.example.guia7_dsm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

     companion object{
         private const val RC_SING_IN =423
     }
    //Creamos la referencia del objeto FirebaseAuth
    private lateinit var auth: FirebaseAuth


    //Referencia a componentes de nuestro Layout
    private lateinit var btnLogin: Button
    private lateinit var textViewRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        auth = FirebaseAuth.getInstance()

        btnLogin=findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener{
            val email=findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            val password=findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            this.login(email,password)

        }

        fun googleLogin(){

            val providers:   ArrayList<AuthUI.IdpConfig> = arrayListOf(
                AuthUI.IdpConfig.GoogleBuilder().build()
            )



            btnlogin.setOnClickListener {
            startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                    RC_SING_IN)

            }
        }

        textViewRegister=findViewById<TextView>(R.id.textViewRegister)
        textViewRegister.setOnClickListener{
            this.goToRegister()
        }
    }


    private fun login(email: String, password: String){

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
            if(task.isSuccessful){
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener{exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToRegister(){
        val intent= Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
