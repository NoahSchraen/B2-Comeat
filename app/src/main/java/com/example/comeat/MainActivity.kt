package com.example.comeat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import Utilisateur

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saisieEmail : TextView = findViewById(R.id.edtTextEmail)
        val saisieMdp : TextView = findViewById(R.id.edtTextPassword)


        val btnValider: Button = findViewById(R.id.btnValider)

        btnValider.setOnClickListener({
            val email : String = saisieEmail.text.toString()
            val mdp : String = saisieMdp.text.toString()

            var user : Utilisateur? = Modele.findUtilisateur(email, mdp)

            //verification des données pour se connecter
            if (user != null){
                Session.ouvrir(user)
                val intent = Intent(this, MenuRepasActivity::class.java )
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_LONG).show()
            }

            Log.d("ACT_CONN", "Tentative de Connexion : $email/$mdp")
        })


        val btnAnnuler : Button = findViewById(R.id.btnAnnuler)

        btnAnnuler.setOnClickListener({
            saisieEmail.text = ""
            saisieMdp.text = ""

            Log.d("ACT_CONN" , "Annulation")
        })

    }
}



