package org.bouchard.modele;

public class VDVote {
    public int indice;
    public String personne;
    public Integer voteId;
    public Integer questionId;

    public  VDVote(){

    }

    public VDVote(int pIndice,String pPersonne, Integer pVoteId, Integer pQuestion){
        indice = pIndice;
        personne = pPersonne;
        voteId = pVoteId;
        questionId = pQuestion;
    }

}
