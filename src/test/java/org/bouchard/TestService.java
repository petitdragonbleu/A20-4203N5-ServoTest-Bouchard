package org.bouchard;

import org.bouchard.exceptions.*;
import org.bouchard.impl.impl;
import org.bouchard.interfaces.Service;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;
import org.junit.Assert;
import org.junit.Test;

public class TestService {

    @Test(expected = QuestionIdNonNulle.class)
    public void creerQuestionAvecIdNonNulle() throws MauvaiseQuestion, QuestionExistante, QuestionIdNonNulle {

        VDQuestion question = new VDQuestion();
        question.questionId = 1;
        question.contenu = "Ma question";

        Service service = new impl();
        service.ajoutQuestion(question);
    }

    @Test(expected = QuestionExistante.class)
    public void creerQuestionExistante() throws MauvaiseQuestion, QuestionExistante, QuestionIdNonNulle {

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        VDQuestion question2 = new VDQuestion();
        question2.contenu = "Ma question";

        Service service = new impl();
        service.ajoutQuestion(question);
        service.ajoutQuestion(question2);
    }

    @Test(expected = QuestionExistante.class)
    public void creerQuestionExistanteMajuscule() throws MauvaiseQuestion, QuestionExistante, QuestionIdNonNulle {

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        VDQuestion question2 = new VDQuestion();
        question2.contenu = "MA QUESTION";

        Service service = new impl();
        service.ajoutQuestion(question);
        service.ajoutQuestion(question2);
    }

    @Test(expected = MauvaiseQuestion.class)
    public void creerQuestionPetitContenu() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante {

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma";

        Service service = new impl();
        service.ajoutQuestion(question);
    }

    @Test(expected = MauvaiseQuestion.class)
    public void creerQuestionGrandContenu() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante {

        VDQuestion question = new VDQuestion();
        question.contenu = "Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue. Cette question est trop longue.";

        Service service = new impl();
        service.ajoutQuestion(question);
    }

    @Test
    public void creerQuestionsIdDifferents() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante {

        VDQuestion question1 = new VDQuestion();
        question1.contenu = "Ma question";

        VDQuestion question2 = new VDQuestion();
        question2.contenu = "Ma question2";

        Service service = new impl();
        service.ajoutQuestion(question1);
        service.ajoutQuestion(question2);

        Assert.assertTrue(question1.questionId != question2.questionId);
    }

    @Test(expected = VoteIdNonNulle.class)
    public void creerVoteIdNonNulle() throws MauvaisVote, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait, QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante {

        Service service = new impl();

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
    public void creerVoteDejatFait() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisVote, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait {
        Service service = new impl();

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
    public void creerVoteDejatFaitMajuscule() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisVote, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait {
        Service service = new impl();

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
    public void creerVoteMauvaisIndicePetit() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisVote, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait {
        Service service = new impl();

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
    public void creerVoteMauvaisIndiceGrand() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisVote, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait {
        Service service = new impl();

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
    public void creerVoteQuestionIdNulle() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisVote, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait {
        Service service = new impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.indice = 4;

        service.ajoutVote(vote);
    }

    @Test(expected = VoteNonAssocierAQuestion.class)
    public void creerVoteQuestionIdInexistante() throws QuestionIdNonNulle, MauvaiseQuestion, QuestionExistante, MauvaisVote, MauvaisIndice, VoteNonAssocierAQuestion, VoteIdNonNulle, VoteDejaFait {
        Service service = new impl();

        VDQuestion question = new VDQuestion();
        question.contenu = "Ma question";

        service.ajoutQuestion(question);

        VDVote vote = new VDVote();
        vote.personne = "Gab";
        vote.questionId = 289;
        vote.indice = 4;

        service.ajoutVote(vote);
    }
}
