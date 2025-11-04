package org.iut.refactoring;

class GestionApp {
    public static void main(String[] args) {
        GestionPersonnel app = new GestionPersonnel();

        app.ajouteSalarie("DEVELOPPEUR", "Alice", 50_000, 6, "IT");
        app.ajouteSalarie("CHEF DE PROJET", "Bob", 60_000, 4, "RH");
        app.ajouteSalarie("STAGIAIRE", "Charlie", 20_000, 0, "IT");
        app.ajouteSalarie("DEVELOPPEUR", "Dan", 55_000, 12, "IT");

        // Récupération de l'id via le modèle objet
        String aliceId = app.getEmployes().get(0).id;

        System.out.println("Salaire de Alice: " + app.calculSalaire(aliceId) + " €");
        System.out.println("Bonus de Alice: " + app.calculBonusAnnuel(aliceId) + " €");

        // Types valides: SALAIRE | EXPERIENCE | DIVISION
        app.generationRapport("SALAIRE", "IT");     // filtré sur division IT
        app.generationRapport("DIVISION", null);    // répartition par division

        app.avancementEmploye(aliceId, "CHEF DE PROJET");

        app.printLogs();
    }
}
