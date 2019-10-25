package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class QuestionStack {
    private QuestionCategory questionCategory;
    private LinkedList<String> questions;

    private QuestionStack(QuestionCategory questionCategory, LinkedList<String> questions) {
        this.questionCategory = questionCategory;
        this.questions = questions;
    }

    public static QuestionStack initializeStack(QuestionCategory questionCategory) {
        LinkedList<String> questions = createQuestions(questionCategory);
        return new QuestionStack(questionCategory, questions);
    }

    private static LinkedList<String> createQuestions(QuestionCategory questionCategory) {
        LinkedList<String> questions = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            questions.addLast(questionCategory + " Question " + i);
        }
        return questions;
    }

    public void printFirstQuestion() { // one responsability, extract variable currentcategory()
        System.out.println(questions.removeFirst());
    }
}
