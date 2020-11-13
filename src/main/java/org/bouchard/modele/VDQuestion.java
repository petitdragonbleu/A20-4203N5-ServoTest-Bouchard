package org.bouchard.modele;

import java.util.List;

public class VDQuestion {
    public Integer questionId;
    public String contenu;
    public Integer nbVote;

    public VDQuestion(){
        questionId = null;
        contenu = null;
        nbVote = 0;
    }

    public VDQuestion(Integer pQuestionId, String pQuestion, Integer pNbVote){
        questionId = pQuestionId;
        contenu = pQuestion;
        nbVote = pNbVote;
    }

}
