package org.bouchard.modele;

import java.util.List;

public class VDQuestion {
    public Integer questionId;
    public String contenu;
    public List<VDVote> tousLesVotes;
    public Integer nbVote;

    public VDQuestion(){

    }

    public VDQuestion(int pQuestionId, String pQuestion, List<VDVote> pVote){
        questionId = pQuestionId;
        contenu = pQuestion;
        tousLesVotes = pVote;
    }

}
