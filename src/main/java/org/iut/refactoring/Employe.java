package org.iut.refactoring;

import java.util.UUID;

public class Employe {
    String id;
    TypeEmploye type;
    String name;
    double salaireBase;
    int experience;
    String division;

    public Employe(TypeEmploye type, String name, double salaireBase, int experience, String division) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.name = name;
        this.salaireBase = salaireBase;
        this.experience = experience;
        this.division = division;
    }
}
