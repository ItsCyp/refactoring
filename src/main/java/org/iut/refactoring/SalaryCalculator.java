package org.iut.refactoring;

public class SalaryCalculator {
    public static double calculate(Employe emp){
        double salaire = emp.salaireBase;

        switch (emp.type) {
            case DEVELOPPEUR:
                salaire *= 1.2;
                if (emp.experience > 5) salaire *= 1.15;
                if (emp.experience > 10) salaire *= 1.05;
                break;

            case CHEF_DE_PROJET:
                salaire *= 1.5;
                if (emp.experience > 3) salaire *= 1.1;
                salaire += 5000;
                break;

            case STAGIAIRE:
                salaire *= 0.6;
                break;
        }

        return salaire;
    }
}
