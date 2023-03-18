package com.example.guia7_dsm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val authUser:FirebaseAuth by lazy{FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun goTo(){
        if(authUser.currentUser != null) {
            startActivity(Intent(this, principal::class.java))
            finish()

        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sign_out -> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }
}