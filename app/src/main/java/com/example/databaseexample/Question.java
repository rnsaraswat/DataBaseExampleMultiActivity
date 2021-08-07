package com.example.databaseexample;

public class Question {

    // इसमे एक table Question की डिटेल रहीगी
    private int id;
    private String Question_Detail;
    private String Question_Option1;
    private String Question_Option2;
    private String Question_Option3;
    private String Question_Option4;
    private String Question_Answer;


    //  यहाँ इसके constructor बनाया जागेगा जो की ALT+INS से बनयेगे
    public Question(int id, String question_Detail, String question_Option1, String question_Option2, String question_Option3, String question_Option4, String question_Answer) {
        this.id = id;
        this.Question_Detail = question_Detail;
        this.Question_Option1 = question_Option1;
        this.Question_Option2 = question_Option2;
        this.Question_Option3 = question_Option3;
        this.Question_Option4 = question_Option4;
        this.Question_Answer = question_Answer;
    }

    //  यहाँ इसके constructor बनाया जागेगा जो की ALT+INS से बनयेगे
    public Question(String question_Detail, String question_Option1, String question_Option2, String question_Option3, String question_Option4, String question_Answer) {
        this.Question_Detail = question_Detail;
        this.Question_Option1 = question_Option1;
        this.Question_Option2 = question_Option2;
        this.Question_Option3 = question_Option3;
        this.Question_Option4 = question_Option4;
        this.Question_Answer = question_Answer;
    }
    public Question() {
    }

    //  यहाँ इसके gatter/setters बनाये जायेगे जो की ALT+INS से बनयेगे
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_Detail() {
        return Question_Detail;
    }

    public void setQuestion_Detail(String question_Detail) {
        Question_Detail = question_Detail;
    }

    public String getQuestion_Option1() {
        return Question_Option1;
    }

    public void setQuestion_Option1(String question_Option1) {
        Question_Option1 = question_Option1;
    }

    public String getQuestion_Option2() {
        return Question_Option2;
    }

    public void setQuestion_Option2(String question_Option2) {
        Question_Option2 = question_Option2;
    }

    public String getQuestion_Option3() {
        return Question_Option3;
    }

    public void setQuestion_Option3(String question_Option3) {
        Question_Option3 = question_Option3;
    }

    public String getQuestion_Option4() {
        return Question_Option4;
    }

    public void setQuestion_Option4(String question_Option4) {
        Question_Option4 = question_Option4;
    }

    public String getQuestion_Answer() {
        return Question_Answer;
    }

    public void setQuestion_Answer(String question_Answer) {
        Question_Answer = question_Answer;
    }

}

