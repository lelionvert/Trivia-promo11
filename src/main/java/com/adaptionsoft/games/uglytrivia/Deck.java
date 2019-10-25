package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.Map;

public class Deck {
    Map<QuestionCategory, QuestionStack> questionCategoryStack;

    private Deck(Map<QuestionCategory, QuestionStack> questionCategoryStack) {
        this.questionCategoryStack = questionCategoryStack;
    }

    private static Map<QuestionCategory, QuestionStack> create() {
        Map<QuestionCategory, QuestionStack> questionCategoryStack = new HashMap<>();
        for (QuestionCategory category : QuestionCategory.values()){
            QuestionStack questionStack = QuestionStack.initializeStack(category);
            questionCategoryStack.put(category, questionStack);
        }
        return questionCategoryStack;
    }

    public static Deck initializeDeck(){
        return new Deck(create());
    }

    public void printQuestionCategory(QuestionCategory category){
        questionCategoryStack.get(category).printFirstQuestion();
    }

}
