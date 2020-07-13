package com.example.projetandroidbinome.entities;

public class post {


    private int urlMap;
    private String titre;
    private String contenu;
    private int urlImage;
    private String Nom;


    public post(int urlMap, String titre, String contenu, int urlImage, String nom) {
        this.urlMap = urlMap;
        this.titre = titre;
        this.contenu = contenu;
        this.urlImage = urlImage;
        Nom = nom;
    }

    public int getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(int urlMap) {
        this.urlMap = urlMap;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(int urlImage) {
        this.urlImage = urlImage;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }
}
