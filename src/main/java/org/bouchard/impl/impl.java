package org.bouchard.impl;

import org.bouchard.exceptions.MauvaisVote;
import org.bouchard.exceptions.MauvaiseQuestion;
import org.bouchard.interfaces.Service;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class impl implements Service {

    public List<VDQuestion> questions = new ArrayList<VDQuestion>();
    public int qId =0;
    public List<VDVote> votes = new ArrayList<VDVote>();
    public int vId =0;

    @Override
    public void ajoutQuestion(VDQuestion question) throws MauvaiseQuestion {
        if(question.questionId != null) throw  new MauvaiseQuestion();
        if(question.contenu == null || question.contenu.length() < 5 || question.contenu.length() > 255) throw new MauvaiseQuestion();
        for(VDQuestion q : questions){
            if(q.contenu.contains(question.contenu)) throw  new MauvaiseQuestion();
        }

        question.questionId = qId;
        qId++;
        List<VDVote> listVotes = new ArrayList<VDVote>();
        question.tousLesVotes = listVotes;
        questions.add(question);

    }

    @Override
    public void ajoutVote(VDVote vote) throws MauvaisVote {
        if(vote.voteId != null) throw new MauvaisVote();
        if(vote.indice < 0 || vote.indice > 5) throw new MauvaisVote();
        if(vote.questionId == null) throw new MauvaisVote();
        for(VDVote v : votes){
            if(v.questionId == vote.questionId && v.personne.toLowerCase() == vote.personne.toLowerCase())
                throw new MauvaisVote();
        }

        vote.voteId = vId;
        vId++;
        votes.add(vote);
    }

    @Override
    public List<VDQuestion> questionsParNombreVotes() {
        List<VDQuestion> questionEnOrdre = new ArrayList<>(questions);
        int n = questionEnOrdre.size();
        for(int i = 0; i < n-1 ;i++){
            for(int j = 0; j < n -i-1;j++){
                if(questionEnOrdre.get(j).tousLesVotes.size() > questionEnOrdre.get(j+1).tousLesVotes.size()){
                    VDQuestion temp = questionEnOrdre.get(j);
                    questionEnOrdre.set(j, questionEnOrdre.get(j+1));
                    questionEnOrdre.set(j+1, temp);
                }
            }
        }
        return questionEnOrdre;
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
        return "Bouchard Gabriel";
    }
}
