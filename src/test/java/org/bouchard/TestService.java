package org.bouchard;

import org.bouchard.exceptions.*;
import org.bouchard.impl.Impl;
import org.bouchard.interfaces.Service;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestService {

    @Test(expected = QuestionIdNonNulle.class)
    public void creerQuestionAvecIdNonNulle() throws MauvaiseQuestion, QuestionExistante, QuestionIdNonNulle, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        VDQuestion question = new VDQuestion();
        question.questionId = 1;
        question.contenu = "Ma question";

        Service service = new Impl();
        service.ajoutQuestion(question);
    }

    @Test(expected = QuestionExistante.class)
    public void creerQuestionExistante() throws MauvaiseQuestion, QuestionExistante, QuestionIdNonNulle, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        VDQuestion question2 = new VDQuestion();
        question2.contenu = "Ma question";

        Service service = new Impl();
        service.ajoutQuestion(question);
        service.ajoutQuestion(question2);
    }

    @Test(expected = QuestionExistante.class)
    public void creerQuestionExistanteMajuscule() throws MauvaiseQuestion, QuestionExistante, QuestionIdNonNulle, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        VDQuestion question2 = new VDQuestion();
        question2.contenu = "MA QUESTION";

        Service service = new Impl();
        service.ajoutQuestion(question);
        service.ajoutQuestion(question2);
    }

    @Test(expected = QuestionContenuNulle.class)
    public void creerQuestionContenuNulle() throws QuestionPlusPetiteQue5, QuestionIdNonNulle, QuestionContenuNulle, QuestionExistante, QuestionPlusGrandeQue255 {
        VDQuestion question = new VDQuestion();

        Service service = new Impl();
        service.ajoutQuestion(question);
    }

    @Test(expected = QuestionPlusPetiteQue5.class)
    public void creerQuestionPetitContenu() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma";

        Service service = new Impl();
        service.ajoutQuestion(question);
    }

    @Test(expected = QuestionPlusGrandeQue255.class)
    public void creerQuestionGrandContenu() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        VDQuestion question = new VDQuestion();
        question.contenu = "Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue.";

        Service service = new Impl();
        service.ajoutQuestion(question);
    }

    @Test
    public void creerQuestionsIdDifferents() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        VDQuestion question1 = new VDQuestion();
        question1.contenu = "Ma question";

        VDQuestion question2 = new VDQuestion();
        question2.contenu = "Ma question2";

        Service service = new Impl();
        service.ajoutQuestion(question1);
        service.ajoutQuestion(question2);

        Assert.assertTrue(question1.questionId != question2.questionId);
    }

    @Test(expected = VoteIdNonNulle.class)
    public void creerVoteIdNonNulle() throws MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.questionId = question.questionId;
        vote.indice = 5;
        vote.voteId = 1;

        service.ajoutVote(vote);
    }

    @Test(expected = VoteDejaFait.class)
    public void creerVoteDejatFait() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {
        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.questionId = question.questionId;
        vote.indice = 5;

        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote.personne = "Gab";
        vote.questionId = question.questionId;
        vote.indice = 4;

        service.ajoutVote(vote);
    }

    @Test(expected = VoteDejaFait.class)
    public void creerVoteDejatFaitMajuscule() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {
        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.questionId = question.questionId;
        vote.indice = 5;

        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote.personne = "GAB";
        vote.questionId = question.questionId;
        vote.indice = 4;

        service.ajoutVote(vote);
    }

    @Test(expected = MauvaisIndice.class)
    public void creerVoteMauvaisIndicePetit() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {
        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.questionId = question.questionId;
        vote.indice = -1;

        service.ajoutVote(vote);
    }

    @Test(expected = MauvaisIndice.class)
    public void creerVoteMauvaisIndiceGrand() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {
        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.questionId = question.questionId;
        vote.indice = 6;

        service.ajoutVote(vote);
    }

    @Test(expected = VoteNonAssocierAQuestion.class)
    public void creerVoteQuestionIdNulle() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {
        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.indice = 4;

        service.ajoutVote(vote);
    }

    @Test(expected = VoteNonAssocierAQuestion.class)
    public void creerVoteQuestionIdInexistante() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {
        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.questionId = 289;
        vote.indice = 4;

        service.ajoutVote(vote);
    }

    @Test
    public void listeQuestionsTrieesNbDeVotesDifferents() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        Service service = new Impl();

        VDQuestion question1 = new VDQuestion();
        question1.contenu = "Ma question 1";
        service.ajoutQuestion(question1);

        VDQuestion question2 = new VDQuestion();
        question2.contenu = "Ma question 2";
        service.ajoutQuestion(question2);

        VDQuestion question3 = new VDQuestion();
        question3.contenu = "Ma question 3";
        service.ajoutQuestion(question3);

        VDVote vote1 = new VDVote();
        vote1.indice = 4;
        vote1.questionId = question3.questionId;
        vote1.personne = "Gab";
        service.ajoutVote(vote1);

        VDVote vote2 = new VDVote();
        vote2.indice = 4;
        vote2.questionId = question3.questionId;
        vote2.personne = "Dan";
        service.ajoutVote(vote2);

        VDVote vote3 = new VDVote();
        vote3.indice = 4;
        vote3.questionId = question2.questionId;
        vote3.personne = "Marc";
        service.ajoutVote(vote3);

        List<VDQuestion> resultat = new ArrayList<VDQuestion>();
        resultat.add(question3);
        resultat.add(question2);
        resultat.add(question1);

        Assert.assertEquals(resultat, service.questionsParNombreVotes());

    }

    @Test
    public void listeQuestionsTrieesMemeNombreDeVotes() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        Service service = new Impl();

        VDQuestion question1 = new VDQuestion();
        question1.contenu = "Quel est ton nom ?";
        service.ajoutQuestion(question1);

        VDQuestion question2 = new VDQuestion();
        question2.contenu = "Est-ce que tu aime le sport ?";
        service.ajoutQuestion(question2);

        VDQuestion question3 = new VDQuestion();
        question3.contenu = "ou aime-tu manger ?";
        service.ajoutQuestion(question3);

        VDVote vote1 = new VDVote();
        vote1.indice = 4;
        vote1.questionId = question3.questionId;
        vote1.personne = "Gab";
        service.ajoutVote(vote1);

        VDVote vote2 = new VDVote();
        vote2.indice = 4;
        vote2.questionId = question2.questionId;
        vote2.personne = "Gab";
        service.ajoutVote(vote2);

        VDVote vote3 = new VDVote();
        vote3.indice = 4;
        vote3.questionId = question1.questionId;
        vote3.personne = "Gab";
        service.ajoutVote(vote3);

        List<VDQuestion> resultat = new ArrayList<VDQuestion>();
        resultat.add(question2);
        resultat.add(question3);
        resultat.add(question1);

        Assert.assertEquals(resultat, service.questionsParNombreVotes());
    }

    @Test
    public void calculDistribution() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";
        service.ajoutQuestion(question);

        VDVote vote1 = new VDVote();
        vote1.indice = 1;
        vote1.personne = "Gab";
        vote1.questionId = question.questionId;
        service.ajoutVote(vote1);

        VDVote vote2 = new VDVote();
        vote2.indice = 5;
        vote2.personne = "Dan";
        vote2.questionId = question.questionId;
        service.ajoutVote(vote2);

        VDVote vote3 = new VDVote();
        vote3.indice = 4;
        vote3.personne = "Marc";
        vote3.questionId = question.questionId;
        service.ajoutVote(vote3);

        VDVote vote4 = new VDVote();
        vote4.indice = 2;
        vote4.personne = "Laura";
        vote4.questionId = question.questionId;
        service.ajoutVote(vote4);

        VDVote vote5 = new VDVote();
        vote5.indice = 3;
        vote5.personne = "Maude";
        vote5.questionId = question.questionId;
        service.ajoutVote(vote5);

        VDVote vote6 = new VDVote();
        vote6.indice = 0;
        vote6.personne = "Gilles";
        vote6.questionId = question.questionId;
        service.ajoutVote(vote6);

        Map<Integer, Integer> resultat = new HashMap<Integer, Integer>();

        resultat.put(0, 1);
        resultat.put(1,1);
        resultat.put(2,1);
        resultat.put(3,1);
        resultat.put(4,1);
        resultat.put(5,1);

        Assert.assertEquals(resultat, service.distributionPour(question));
    }

    @Test
    public void calculMoyenne() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {

        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.indice = 2;
        vote.personne = "Gab";
        vote.questionId = question.questionId;
        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote2.indice = 2;
        vote2.personne = "Dan";
        vote2.questionId = question.questionId;
        service.ajoutVote(vote2);

        VDVote vote3 = new VDVote();
        vote3.indice = 5;
        vote3.personne = "Marc";
        vote3.questionId = question.questionId;
        service.ajoutVote(vote3);

        Assert.assertEquals(3, service.moyennePour(question), 0.0000000001);
    }

    @Test
    public void calculEcartType() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {
        Service service = new Impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";
        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.indice = 2;
        vote.personne = "Gab";
        vote.questionId = question.questionId;
        service.ajoutVote(vote);

        VDVote vote2 = new VDVote();
        vote2.indice = 2;
        vote2.personne = "Dan";
        vote2.questionId = question.questionId;
        service.ajoutVote(vote2);

        VDVote vote3 = new VDVote();
        vote3.indice = 5;
        vote3.personne = "Marc";
        vote3.questionId = question.questionId;
        service.ajoutVote(vote3);

        Assert.assertEquals(1.4142135623731, service.ecartTypePour(question), 0.0000000000001);
    }

    @Test
    public void nomEtudiant(){
        String resultat = "Bouchard Gabriel";
        Service service = new Impl();
        Assert.assertEquals(resultat, service.nomEtudiant());
    }
}
