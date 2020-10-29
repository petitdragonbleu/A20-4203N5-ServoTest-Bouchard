package org.bouchard.interfaces;

import org.bouchard.exceptions.*;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;

import java.util.List;
import java.util.Map;

public interface Service {

    void ajoutQuestion(VDQuestion question) throws QuestionIdNonNulle, QuestionExistante, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle;

    void ajoutVote(VDVote vote) throws VoteIdNonNulle, MauvaisIndice, VoteDejaFait, VoteNonAssocierAQuestion;

    List<VDQuestion> questionsParNombreVotes();

    Map<Integer, Integer> distributionPour(VDQuestion question);

    double moyennePour(VDQuestion question);

    double ecartTypePour(VDQuestion question);

    String nomEtudiant();
}
