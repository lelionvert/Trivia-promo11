package com.adaptionsoft.games.uglytrivia;

public enum QuestionCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORT("Sports"),
    ROCK("Rock");
    private String category;
    private static QuestionCategory[] values = QuestionCategory.values();

    QuestionCategory(String category) {
        this.category = category;
    }

    public static String getCategoryBy(int position) {
        return values[position % values.length].category;
    }
}
