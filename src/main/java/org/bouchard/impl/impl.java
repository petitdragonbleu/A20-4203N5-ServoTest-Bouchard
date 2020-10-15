package org.bouchard.impl;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap;
import org.bouchard.exceptions.MauvaisVote;
import org.bouchard.exceptions.MauvaiseQuestion;
import org.bouchard.interfaces.Service;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;

import java.util.*;

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
        question.nbVote =0;
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
        for(VDVote v : questions.get(vote.questionId).tousLesVotes){
            if(v.personne.toLowerCase().equals(vote.personne.toLowerCase()))
                throw new MauvaisVote();
        }

        vote.voteId = vId;
        vId++;
        for(VDQuestion q : questions){
            if(vote.questionId == q.questionId){
                q.tousLesVotes.add(vote);
                q.nbVote++;
            }
        }
        votes.add(vote);
    }

    @Override
    public List<VDQuestion> questionsParNombreVotes() {
        List<VDQuestion> questionEnOrdre = new ArrayList<>(questions);
        /*
        int n = questionEnOrdre.size();
        for(int i = 0; i < n-1 ;i++){
            for(int j = 0; j < n -i-1;j++){
                if(questionEnOrdre.get(j).tousLesVotes.size() < questionEnOrdre.get(j+1).tousLesVotes.size()){
                    VDQuestion temp = questionEnOrdre.get(j);
                    questionEnOrdre.set(j, questionEnOrdre.get(j+1));
                    questionEnOrdre.set(j+1, temp);
                }
                if(questionEnOrdre.get(j).tousLesVotes.size() == questionEnOrdre.get(j+1).tousLesVotes.size()){
                    int debut = j;
                    int fin = j+1;
                    for(int k = j; k < n-j-1; j++){
                        if(questionEnOrdre.get(k).tousLesVotes.size() == questionEnOrdre.get(k+1).tousLesVotes.size()){
                            fin ++;
                        }
                        else break;
                    }
                    Collections.sort(questionEnOrdre.subList(debut,fin), VDQuestion.CASE_INSENSITIVE_ORDER);
                }
            }
        }*/
        Collections.sort(questionEnOrdre, new Comparator<VDQuestion>() {
            @Override
            public int compare(VDQuestion o1, VDQuestion o2) {
                if(o1.nbVote.compareTo(o2.nbVote) == 0){
                    return o1.contenu.compareToIgnoreCase(o2.contenu);
                }else {
                    return o2.nbVote.compareTo(o1.nbVote); }
            }
        });

        return questionEnOrdre;
    }

    @Override
    public Map<Integer, Integer> distributionPour(VDQuestion question) {
        Map<Integer, Integer> distrib = new HashMap<Integer, Integer>();
        int zero =0;
        int un=0;
        int deux=0;
        int trois=0;
        int quatre=0;
        int cinq=0;

        for(VDVote v : question.tousLesVotes){
            if(v.indice == 0)
                zero++;
            if(v.indice == 1)
                un++;
            if(v.indice == 2)
                deux++;
            if(v.indice == 3)
                trois++;
            if(v.indice == 4)
                quatre++;
            if(v.indice == 5)
                cinq++;
        }

        distrib.put(0,zero);
        distrib.put(1,un);
        distrib.put(2,deux);
        distrib.put(3,trois);
        distrib.put(4,quatre);
        distrib.put(5, cinq);

        return distrib;
    }

    @Override
    public double moyennePour(VDQuestion question) {
        double resultat =0;
        int index =0;
        for(VDVote v : question.tousLesVotes){
            resultat += v.indice;
            index++;
        }
        return resultat/index;
    }

    @Override
    public double ecartTypePour(VDQuestion question) {
        double ecartType = 0;
        double moyenne = moyennePour(question);
        double variance =0;
        for(VDVote v : question.tousLesVotes){
            variance += Math.pow(v.indice - moyenne,2);
        }
        variance = variance/question.tousLesVotes.size();
        ecartType = Math.sqrt(variance);
        return ecartType;
    }

    @Override
    public String nomEtudiant() {
        return "Bouchard Gabriel";
    }

}
