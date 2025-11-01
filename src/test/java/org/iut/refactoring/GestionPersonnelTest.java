package org.iut.refactoring;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestionPersonnelTest {

    private GestionPersonnel gestionPersonnel;

    @BeforeEach
    void setUp() {
        gestionPersonnel = new GestionPersonnel();
    }

    @Test
    void testAjouteSalarie_Developpeur_SansExperience() {
        // Given
        String type = "DEVELOPPEUR";
        String nom = "Alice";
        double salaireDeBase = 50000;
        int experience = 3;
        String equipe = "IT";

        // When
        gestionPersonnel.ajouteSalarie(type, nom, salaireDeBase, experience, equipe);

        // Then
        assertEquals(1, gestionPersonnel.employes.size());
        Object[] employe = gestionPersonnel.employes.get(0);
        assertEquals(type, employe[1]);
        assertEquals(nom, employe[2]);
        assertEquals(salaireDeBase, employe[3]);
        assertEquals(experience, employe[4]);
        assertEquals(equipe, employe[5]);

        // Vérifier le salaire final calculé (50000 * 1.2 = 60000)
        String employeId = (String) employe[0];
        assertEquals(60000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }

    @Test
    void testAjouteSalarie_Developpeur_AvecExperienceSuperieure5() {
        // Given
        String type = "DEVELOPPEUR";
        String nom = "Bob";
        double salaireDeBase = 50000;
        int experience = 7;
        String equipe = "IT";

        // When
        gestionPersonnel.ajouteSalarie(type, nom, salaireDeBase, experience, equipe);

        // Then
        assertEquals(1, gestionPersonnel.employes.size());

        // Vérifier le salaire final calculé (50000 * 1.2 * 1.15 = 69000)
        String employeId = (String) gestionPersonnel.employes.get(0)[0];
        assertEquals(69000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }

    @Test
    void testAjouteSalarie_ChefDeProjet_SansExperienceSuperieure3() {
        // Given
        String type = "CHEF DE PROJET";
        String nom = "Charlie";
        double salaireDeBase = 60000;
        int experience = 2;
        String equipe = "RH";

        // When
        gestionPersonnel.ajouteSalarie(type, nom, salaireDeBase, experience, equipe);

        // Then
        assertEquals(1, gestionPersonnel.employes.size());
        Object[] employe = gestionPersonnel.employes.get(0);
        assertEquals(type, employe[1]);
        assertEquals(nom, employe[2]);

        // Vérifier le salaire final calculé (60000 * 1.5 = 90000)
        String employeId = (String) employe[0];
        assertEquals(90000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }

    @Test
    void testAjouteSalarie_ChefDeProjet_AvecExperienceSuperieure3() {
        // Given
        String type = "CHEF DE PROJET";
        String nom = "David";
        double salaireDeBase = 60000;
        int experience = 5;
        String equipe = "RH";

        // When
        gestionPersonnel.ajouteSalarie(type, nom, salaireDeBase, experience, equipe);

        // Then
        assertEquals(1, gestionPersonnel.employes.size());

        // Vérifier le salaire final calculé (60000 * 1.5 * 1.1 = 99000)
        String employeId = (String) gestionPersonnel.employes.get(0)[0];
        assertEquals(99000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }

    @Test
    void testAjouteSalarie_Stagiaire() {
        // Given
        String type = "STAGIAIRE";
        String nom = "Eve";
        double salaireDeBase = 20000;
        int experience = 0;
        String equipe = "IT";

        // When
        gestionPersonnel.ajouteSalarie(type, nom, salaireDeBase, experience, equipe);

        // Then
        assertEquals(1, gestionPersonnel.employes.size());
        Object[] employe = gestionPersonnel.employes.get(0);
        assertEquals(type, employe[1]);
        assertEquals(nom, employe[2]);

        // Vérifier le salaire final calculé (20000 * 0.6 = 12000)
        String employeId = (String) employe[0];
        assertEquals(12000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }

    @Test
    void testAjouteSalarie_PlusieursEmployes() {
        // When
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 3, "IT");
        gestionPersonnel.ajouteSalarie("CHEF DE PROJET", "Bob", 60000, 4, "RH");
        gestionPersonnel.ajouteSalarie("STAGIAIRE", "Charlie", 20000, 0, "IT");

        // Then
        assertEquals(3, gestionPersonnel.employes.size());
        assertEquals(3, gestionPersonnel.salairesEmployes.size());
        assertEquals(3, gestionPersonnel.logs.size());
    }

    @Test
    void testAjouteSalarie_GenerationLog() {
        // Given
        String nom = "Frank";

        // When
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", nom, 50000, 3, "IT");

        // Then
        assertEquals(1, gestionPersonnel.logs.size());
        assertTrue(gestionPersonnel.logs.get(0).contains("Ajout de l'employé: " + nom));
    }

    @Test
    void testAjouteSalarie_GenerationIdUnique() {
        // When
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Alice", 50000, 3, "IT");
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Bob", 50000, 3, "IT");

        // Then
        String id1 = (String) gestionPersonnel.employes.get(0)[0];
        String id2 = (String) gestionPersonnel.employes.get(1)[0];

        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2, "Les IDs doivent être uniques");
    }

    @Test
    void testAjouteSalarie_Developpeur_ExperienceExactement5() {
        // Given - test de la limite à experience = 5 (ne devrait pas avoir le bonus)
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Test", 50000, 5, "IT");

        // Then
        String employeId = (String) gestionPersonnel.employes.get(0)[0];
        // Avec experience = 5, pas de bonus (condition > 5)
        assertEquals(60000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }

    @Test
    void testAjouteSalarie_Developpeur_ExperienceExactement6() {
        // Given - test de la limite à experience = 6 (devrait avoir le bonus)
        gestionPersonnel.ajouteSalarie("DEVELOPPEUR", "Test", 50000, 6, "IT");

        // Then
        String employeId = (String) gestionPersonnel.employes.get(0)[0];
        // Avec experience = 6, bonus appliqué (condition > 5)
        assertEquals(69000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }

    @Test
    void testAjouteSalarie_ChefDeProjet_ExperienceExactement3() {
        // Given - test de la limite à experience = 3 (ne devrait pas avoir le bonus)
        gestionPersonnel.ajouteSalarie("CHEF DE PROJET", "Test", 60000, 3, "RH");

        // Then
        String employeId = (String) gestionPersonnel.employes.get(0)[0];
        // Avec experience = 3, pas de bonus (condition > 3)
        assertEquals(90000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }

    @Test
    void testAjouteSalarie_ChefDeProjet_ExperienceExactement4() {
        // Given - test de la limite à experience = 4 (devrait avoir le bonus)
        gestionPersonnel.ajouteSalarie("CHEF DE PROJET", "Test", 60000, 4, "RH");

        // Then
        String employeId = (String) gestionPersonnel.employes.get(0)[0];
        // Avec experience = 4, bonus appliqué (condition > 3)
        assertEquals(99000.0, gestionPersonnel.salairesEmployes.get(employeId), 0.01);
    }
}