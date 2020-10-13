package org.bouchard.impl;

import org.bouchard.interfaces.Service;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;

import java.util.List;
import java.util.Map;

public class impl implements Service {
    
    @Override
    public void ajoutQuestion(VDQuestion question) {

    }

    @Override
    public void ajoutVote(VDVote vote) {

    }

    @Override
    public List<VDQuestion> questionsParNombreVotes() {
        return null;
    }

    @Override
    public Map<Integer, Integer> distributionPour(VDQuestion question) {
        return null;
    }

    @Override
    public double moyennePour(VDQuestion question) {
        return 0;
    }

    @Override
    public double ecartTypePour(VDQuestion question) {
        return 0;
    }

    @Override
    public String nomEtudiant() {
        return null;
    }
}
