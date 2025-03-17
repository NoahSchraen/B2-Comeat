package com.example.comeat

object Modele {
    val utilisateurs : MutableList<Utilisateur> = mutableListOf(
        Utilisateur(1, "LOTHBROK", "Ragnar" , "ragnar@lothbrok@gmail.com", "Odin@Thor"),
        Utilisateur(2, "LOTHBROK", "Lagertha", "lagertha.lothbrok@gmail.com","Loki&Freyja")
    )
    fun findUtilisateur(email : String , mdp : String ) : Utilisateur? {
        for (utilisateur in utilisateurs){
            if (utilisateur.email == email && utilisateur.mdp == mdp){
                return utilisateur
            }
        }
        return null

        // return utilisateurs.find {it.email == email && it.mdp == mdp }
    }
}