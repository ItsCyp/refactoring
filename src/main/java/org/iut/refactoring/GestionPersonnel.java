package org.iut.refactoring;

import java.util.*;
import java.time.*;

public class GestionPersonnel {

    private final ArrayList<Employe> employes = new ArrayList<>();
    private final HashMap<String, Double> salairesEmployes = new HashMap<>();
    private final LogService logService = new LogService();

    public void ajouteSalarie(String typeStr, String nom, double salaireDeBase, int experience, String equipe) {
        TypeEmploye type = TypeEmploye.fromString(typeStr);

        Employe emp = new Employe(type, nom, salaireDeBase, experience, equipe);
        employes.add(emp);

        double salaireFinal = calculSalaire(emp.id);
        salairesEmployes.put(emp.id, salaireFinal);

        logService.add("Ajout de l'employé: " + nom);
    }

    public double calculSalaire(String employeId) {
        Employe emp = null;
        for (Employe e : employes) {
            if (e.id.equals(employeId)) {
                emp = e;
                break;
            }
        }
        if (emp == null) return 0;

        return SalaryCalculator.calculate(emp);
    }

    public void generationRapport(String typeRapport, String filtre) {
        TypeRapport type = TypeRapport.fromString(typeRapport);
        RapportService rapportService = new RapportService();
        String rapport = rapportService.generate(type, employes, filtre);

        System.out.println(rapport);

        logService.add("Rapport généré: " + typeRapport);
    }

    public void avancementEmploye(String employeId, String newTypeStr) {
        TypeEmploye newType = TypeEmploye.fromString(newTypeStr);

        for (Employe emp : employes) {
            if (emp.id.equals(employeId)) {
                emp.type = newType;

                double nouveauSalaire = calculSalaire(employeId);
                salairesEmployes.put(employeId, nouveauSalaire);

                logService.add("Employé promu: " + emp.name);
                System.out.println("Employé promu avec succès!");
                return;
            }
        }

        System.out.println("ERREUR: impossible de trouver l'employé");

    }

    public List<Employe>  getEmployesParDivision(String division) {
        List<Employe> resultat = new ArrayList<>();
        for (Employe emp : employes) {
            if (emp.division.equals(division)) {
                resultat.add(emp);
            }
        }
        return resultat;
    }

    public void printLogs() {
        logService.print();
    }

    public double calculBonusAnnuel(String employeId) {
        for (Employe emp : employes) {
            if (emp.id.equals(employeId)) {

                double salaireDeBase = emp.salaireBase;
                int experience = emp.experience;

                switch (emp.type) {
                    case DEVELOPPEUR:
                        double bonus = salaireDeBase * 0.1;
                        if (experience > 5) {
                            bonus *= 1.5;
                        }
                        return bonus;

                    case CHEF_DE_PROJET:
                        bonus = salaireDeBase * 0.2;
                        if (experience > 3) {
                            bonus *= 1.3;
                        }
                        return bonus;

                    case STAGIAIRE:
                        return 0;
                }
            }
        }
        return 0;
    }

    public ArrayList<Employe> getEmployes() {
        return employes;
    }

    public HashMap<String, Double> getSalairesEmployes() {
        return salairesEmployes;
    }

    public List<String> getLogs() {
        return logService.getLogs();
    }
}



