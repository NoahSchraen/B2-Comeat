package com.example.comeat

import Modele
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RechercheRepasActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recherche_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Variables de reception des valeures de spécialité et date
        var specialiteRepas = ""
        var dateRepas = ""

        //récupération des ids des bouttons et Textviews utilisés
        val btnSelectionnerDate : Button = findViewById(R.id.btnDate)
        val btnValider : Button = findViewById(R.id.btnRechercheConfirmer)
        val tvDate : TextView = findViewById(R.id.tvDate)

        btnValider.isEnabled = false


        //récupération du spinner et création de son adaptateur
        val spSpecialite : Spinner = findViewById(R.id.spSpecialites)
        val adaptateur = ArrayAdapter(this, android.R.layout.simple_spinner_item, Modele.getSpecialites())


        spSpecialite.adapter = adaptateur

        //fonction de vérification de l'état du bouton
        fun verifierButton(){
            if (spSpecialite.selectedItem != null && tvDate.text.isEmpty()) {
                btnValider.isEnabled = true
            }
        }

        spSpecialite.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                specialiteRepas = spSpecialite.selectedItem.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                verifierButton()
            }
        }

        btnSelectionnerDate.setOnClickListener({
            //récupération de la date du jour
            val DateCourante = LocalDate.now()
            val annee = DateCourante.year
            val mois = DateCourante.monthValue -1
            val jour = DateCourante.dayOfMonth

            val datePickerDialog = DatePickerDialog(
                this,
                {
                    view, anneeSelect, moisSelect, jourSelect ->
                    val dateSelectionnee = LocalDate.of(anneeSelect, moisSelect + 1 , jourSelect)
                    val formateur = DateTimeFormatter.ofPattern("dd/MM/yy")
                    val DateFormatee = dateSelectionnee.format(formateur)

                    tvDate.text = DateFormatee
                    dateRepas = DateFormatee
                },
                annee, mois, jour
            )
            datePickerDialog.show()
            verifierButton() //vérification finale pour activer le boutton "Confirmer"

        })
        btnValider.setOnClickListener({
            val intent = Intent(this, ListeRepasActivity::class.java)
            intent.putExtra("Spécialité choisie", specialiteRepas)
            intent.putExtra("Date Choisie", dateRepas)
            startActivity(intent)
        })

    }
}