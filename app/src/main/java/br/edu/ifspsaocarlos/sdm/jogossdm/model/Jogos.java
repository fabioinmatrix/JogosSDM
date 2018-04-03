package br.edu.ifspsaocarlos.sdm.jogossdm.model;

import java.io.Serializable;

/**
 * Created by fabio on 20/11/2017.
 */

    //_________________Classe Jogos (implementa Serializable para que possa ser utilizada como
    // parâmetros da activity e do fragment de detalhes)
public class Jogos implements Serializable {
    //_________________Atributos da classe Jogos
    public String name;
    public String description;
    public float stars;

    //_________________Construtor
    public Jogos(String name, String description, float stars) {
        this.name = name;
        this.description = description;
        this.stars = stars;
    }

    @Override
    public String toString() {
        return name;
    }
}
