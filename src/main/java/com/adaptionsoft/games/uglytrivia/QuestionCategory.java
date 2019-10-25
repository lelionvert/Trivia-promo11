package com.adaptionsoft.games.uglytrivia;

public enum QuestionCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORT("Sports"),
    ROCK("Rock");
    private String category;

    QuestionCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
