package org.bouchard.impl;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bouchard.exceptions.*;
import org.bouchard.interfaces.Service;
import org.bouchard.modele.VDQuestion;
import org.bouchard.modele.VDVote;

import java.util.*;

public class Impl implements Service {

    public List<VDQuestion> questions = new ArrayList<VDQuestion>();
    public int qId =0;
    public List<VDVote> votes = new ArrayList<VDVote>();
    public int vId =0;

    @Override
    public void ajoutQuestion(VDQuestion question) throws QuestionIdNonNulle, QuestionExistante, QuestionPlusPetiteQue5, QuestionPlusGrandeQue255, QuestionContenuNulle {
        if(question.contenu == null) throw new QuestionContenuNulle();
        if(question.contenu.length() < 5) throw new QuestionPlusPetiteQue5();
        if(question.contenu.length() > 255) throw new QuestionPlusGrandeQue255();
        for(VDQuestion q : questions){
            String contenu1 = q.contenu.toLowerCase();
            String contenu2 = question.contenu.toLowerCase();
            if(contenu1.equals(contenu2)) throw  new QuestionExistante();
        }
        if(question.questionId != null) throw  new QuestionIdNonNulle();
        question.nbVote =0;
        question.questionId = qId;
        qId++;
        questions.add(question);

    }

    @Override
    public void ajoutVote(VDVote vote) throws VoteIdNonNulle, MauvaisIndice, VoteDejaFait, VoteNonAssocierAQuestion {
        if(vote.indice < 0 || vote.indice > 5) throw new MauvaisIndice();
        if(vote.questionId == null) throw new VoteNonAssocierAQuestion();
        Boolean questionIdExiste = false;
        for(VDQuestion q : questions) {
            if(q.questionId == vote.questionId) {
                questionIdExiste = true;
                break;
            }
        }

        if(questionIdExiste == false) throw new VoteNonAssocierAQuestion();
        for(VDVote v : listeVotesPourUneQuestion(questions.get(vote.questionId)))
            if (v.personne.toLowerCase().equals(vote.personne.toLowerCase())) {
                throw new VoteDejaFait();
            }
        if(vote.voteId != null) throw new VoteIdNonNulle();
        vote.voteId = vId;
        vId++;
        questionParId(vote.questionId).nbVote++;
        votes.add(vote);
    }


    @Override
    public List<VDQuestion> questionsParNombreVotes() {
        List<VDQuestion> questionEnOrdre = new ArrayList<>(questions);
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

        int zero =0, un=0, deux=0, trois=0, quatre=0, cinq=0;

        for(VDVote v : listeVotesPourUneQuestion(question)){
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
        for(VDVote v : listeVotesPourUneQuestion(question)){
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
        for(VDVote v : listeVotesPourUneQuestion(question)){
            variance += Math.pow(v.indice - moyenne,2);
        }
        variance = variance/listeVotesPourUneQuestion(question).size();
        ecartType = Math.sqrt(variance);
        return ecartType;
    }

    @Override
    public String nomEtudiant() {
        return "Bouchard Gabriel";
    }

    private List<VDVote> listeVotesPourUneQuestion(VDQuestion question){
        List<VDVote> listeV = new ArrayList<>();

        for(VDVote v : votes){
            if(v.questionId == question.questionId)
                listeV.add(v);
        }

        return listeV;
    }

    private VDQuestion questionParId(int qId){

        for(VDQuestion q  : questions){
            if(q.questionId == qId){
                return q;
            }
        }
        return null;
    }

}
