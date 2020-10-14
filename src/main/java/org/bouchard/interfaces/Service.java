package org.bouchard.interfaces;

import org.bouchard.exceptions.MauvaiseQuestion;
import org.bouchard.exceptions.MauvaisVote;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;

import java.util.List;
import java.util.Map;

public interface Service {

    void ajoutQuestion(VDQuestion question) throws MauvaiseQuestion;

    void ajoutVote(VDVote vote) throws MauvaisVote;

    List<VDQuestion> questionsParNombreVotes();

    Map<Integer, Integer> distributionPour(VDQuestion question);

    double moyennePour(VDQuestion question);

    double ecartTypePour(VDQuestion question);

    String nomEtudiant();
}
