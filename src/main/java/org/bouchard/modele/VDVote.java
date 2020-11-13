package org.bouchard.modele;

public class VDVote {
    public Integer indice;
    public String personne;
    public Integer voteId;
    public Integer questionId;

    public  VDVote(){
        indice = null;
        personne = null;
        voteId = null;
        questionId = null;
    }

    public VDVote(Integer pIndice,String pPersonne, Integer pVoteId, Integer pQuestion){
        indice = pIndice;
        personne = pPersonne;
        voteId = pVoteId;
        questionId = pQuestion;
    }

}
