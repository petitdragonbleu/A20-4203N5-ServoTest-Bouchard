package org.bouchard;

import org.bouchard.exceptions.*;
import org.bouchard.impl.Impl;
import org.bouchard.interfaces.Service;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Service service = new Impl();
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        int choix = 0;

        do{
            System.out.println( "Bonjour, voici les options possibles: " );
            System.out.println( "1 : Créer une question" );
            System.out.println( "2 : Créer un vote" );
            System.out.println( "3 : Afficher la liste des questions triées" );
            System.out.println( "4 : Afficher la moyenne, l'écart type et la distribution d'une question" );
            System.out.println( "5 : Quitter" );
            while(scan.hasNext() && !scan.hasNextInt())
            {
                System.err.println("Il faut entrer un nombre !");
                scan.next();
            }
            choix = scan.nextInt();
            while(choix < 1 || choix > 5){
                System.err.println("Nombre invalide !");
                choix = scan.nextInt();
            }
            switch (choix){
                case 1:
                    System.out.println("Quel est votre question ?");
                    String contenu = scan.next();
                    VDQuestion question = new VDQuestion();
                    question.questionId = null;
                    question.contenu = contenu;
                    try {
                        service.ajoutQuestion(question);
                    } catch  (QuestionIdNonNulle | QuestionExistante | QuestionPlusPetiteQue5 | QuestionPlusGrandeQue255 | QuestionContenuNulle mauvaiseQuestion) {
                        mauvaiseQuestion.printStackTrace();
                    }
                    break;
                case 2:
                    if(service.questionsParNombreVotes().size() < 1) {System.err.println("Il n'y a aucune question de créer !"); break;}
                    System.out.println("Choisisser une question par son id");
                    int questionChoisie = 0;
                    int indice = 0;
                    String nom = "";

                    for(VDQuestion q : service.questionsParNombreVotes()){
                        System.out.println("Id : " + q.questionId + " - Question : " + q.contenu);
                    }

                    while(scan.hasNext() && !scan.hasNextInt()){
                        System.err.println("Veuillez entrer un nombre !");
                        scan.next();
                    }
                    questionChoisie = scan.nextInt();
                    while(!iDValide(questionChoisie, service)){
                        System.err.println("Veuillez choisir un id valide !");
                        questionChoisie = scan.nextInt();
                    }
                    System.out.println("Veuillez entrer votre nom");
                    nom = scan.next();
                    while(nom.length()  < 3){
                        System.err.println("Veuillez entrer un nom valide");
                        nom = scan.next();
                    }
                    System.out.println("Veuillez entrer votre note de 0 à 5");
                    while(scan.hasNext() && !scan.hasNextInt()){
                        System.err.println("Veuillez entrer un nombre !");
                        scan.next();
                    }
                    indice = scan.nextInt();
                    while(indice < 0 || indice > 5){
                        System.err.println("Veuillez entrer un indice valide !");
                        indice = scan.nextInt();
                    }
                    VDVote vote = new VDVote();
                    vote.questionId = questionChoisie;
                    vote.personne = nom;
                    vote.indice = indice;
                    try {
                        service.ajoutVote(vote);
                    } catch (MauvaisIndice | VoteNonAssocierAQuestion | VoteIdNonNulle | VoteDejaFait mauvaisVote) {
                        mauvaisVote.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Voici la liste de questions");
                    for(VDQuestion q : service.questionsParNombreVotes()){
                        System.out.println("Id : " + q.questionId + " - Question : " + q.contenu + " - Nombres de vote :" + q.nbVote);
                    }
                    break;
                case 4:
                    if(service.questionsParNombreVotes().size() < 1) {System.err.println("Il n'y a aucune question de créer !"); break;}
                    System.out.println("Choisisser une question par son id");
                    int questionAfficher = 0;
                    for(VDQuestion q : service.questionsParNombreVotes()){
                        System.out.println("Id : " + q.questionId + " - Question : " + q.contenu);
                    }
                    while(scan.hasNext() && !scan.hasNextInt()){
                        System.err.println("Veuillez entrer un nombre !");
                        scan.next();
                    }
                    questionChoisie = scan.nextInt();
                    while(!iDValide(questionChoisie, service)){
                        System.err.println("Veuillez choisir un id valide !");
                        questionChoisie = scan.nextInt();
                    }
                    VDQuestion qAfficher = new VDQuestion();
                    for(VDQuestion q : service.questionsParNombreVotes()){
                        if(q.questionId == questionAfficher)
                            qAfficher = q;
                    }
                    System.out.println("Id : " + qAfficher.questionId + "\n - Moyenne : " + service.moyennePour(qAfficher) + "\n - EcartType : " +  service.ecartTypePour(qAfficher) +
                            "\n" + afficherDistrib(qAfficher,service));
                    break;
                case 5:
                    break;
            }
        }
        while(choix != 5);
    }

    public static Boolean iDValide(int id, Service service)
    {
        for (VDQuestion q : service.questionsParNombreVotes()){
            if(q.questionId == id)
                return true;
        }
        return false;
    }

    public static String afficherDistrib(VDQuestion question, Service service){
        String resultat = "";
        Map<Integer, Integer> distribution =  new HashMap<Integer, Integer>();
        distribution = service.distributionPour(question);
        for(Integer indice : distribution.keySet()){
            resultat += "Indice :" + indice + "-  Nombre :" + distribution.get(indice) + "\n";
        }
        return resultat;
    }


}
