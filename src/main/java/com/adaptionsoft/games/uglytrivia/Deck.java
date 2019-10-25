package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.Map;

public class Deck {
    Map<QuestionCategory, QuestionStack> questionCategoryStack;

    public Deck() {
        questionCategoryStack = new HashMap<>();
        for (QuestionCategory category : QuestionCategory.values()){
            QuestionStack questionStack = QuestionStack.initializeStack(category);
            questionCategoryStack.put(category, questionStack);

        }
    }

    public void printQuestionCategory(QuestionCategory category){
        questionCategoryStack.get(category).printFirstQuestion();
    }

}
