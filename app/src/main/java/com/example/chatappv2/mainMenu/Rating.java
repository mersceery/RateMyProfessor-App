package com.example.chatappv2.mainMenu;

public class Rating {
    private String module;
    private String professor;
    private String ratingId;
    private float klausurRating;
    private float vorlesungRating;
    private float praktikumRating;

    public Rating(String module, String professor, String ratingId, float klausurRating, float vorlesungRating, float praktikumRating) {
        this.module = module;
        this.professor = professor;
        this.ratingId = ratingId;
        this.klausurRating = klausurRating;
        this.vorlesungRating = vorlesungRating;
        this.praktikumRating = praktikumRating;
    }

    public String getModule() {
        return module;
    }

    public String getProfessor() {
        return professor;
    }

    public String getRatingId() {
        return ratingId;
    }

    public float getKlausurRating() {
        return klausurRating;
    }

    public float getVorlesungRating() {
        return vorlesungRating;
    }

    public float getPraktikumRating() {
        return praktikumRating;
    }

    public float getAverageRating() {
        return (klausurRating + vorlesungRating + praktikumRating) / 3;
    }
}

