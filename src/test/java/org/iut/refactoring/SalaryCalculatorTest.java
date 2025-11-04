package org.iut.refactoring;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SalaryCalculatorTest {

    @Test
    void testCalculate_Developpeur_SansExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "Alice", 50000, 2, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 50000 * 1.2 = 60000
        assertEquals(60000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_Developpeur_Avec5AnsExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "Bob", 50000, 5, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 50000 * 1.2 = 60000 (expérience = 5, pas supérieure à 5)
        assertEquals(60000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_Developpeur_Avec6AnsExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "Charlie", 50000, 6, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 50000 * 1.2 * 1.15 = 69000
        assertEquals(69000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_Developpeur_Avec10AnsExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "David", 50000, 10, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 50000 * 1.2 * 1.15 = 69000 (expérience = 10, pas supérieure à 10)
        assertEquals(69000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_Developpeur_Avec11AnsExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "Eve", 50000, 11, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 50000 * 1.2 * 1.15 * 1.05 = 72450
        assertEquals(72450.0, salaire, 0.01);
    }

    @Test
    void testCalculate_Developpeur_Avec15AnsExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "Frank", 60000, 15, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 60000 * 1.2 * 1.15 * 1.05 = 86940
        assertEquals(86940.0, salaire, 0.01);
    }

    @Test
    void testCalculate_ChefDeProjet_SansExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.CHEF_DE_PROJET, "Grace", 60000, 1, "Management");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 60000 * 1.5 + 5000 = 95000
        assertEquals(95000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_ChefDeProjet_Avec3AnsExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.CHEF_DE_PROJET, "Henry", 60000, 3, "Management");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 60000 * 1.5 + 5000 = 95000 (expérience = 3, pas supérieure à 3)
        assertEquals(95000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_ChefDeProjet_Avec4AnsExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.CHEF_DE_PROJET, "Ivy", 60000, 4, "Management");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 60000 * 1.5 * 1.1 + 5000 = 104000
        assertEquals(104000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_ChefDeProjet_Avec10AnsExperience() {
        // Given
        Employe emp = new Employe(TypeEmploye.CHEF_DE_PROJET, "Jack", 70000, 10, "Management");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 70000 * 1.5 * 1.1 + 5000 = 120500
        assertEquals(120500.0, salaire, 0.01);
    }

    @Test
    void testCalculate_Stagiaire() {
        // Given
        Employe emp = new Employe(TypeEmploye.STAGIAIRE, "Kevin", 20000, 0, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 20000 * 0.6 = 12000
        assertEquals(12000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_Stagiaire_AvecExperience() {
        // Given - même un stagiaire avec de l'expérience garde le même multiplicateur
        Employe emp = new Employe(TypeEmploye.STAGIAIRE, "Laura", 25000, 2, "Marketing");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 25000 * 0.6 = 15000
        assertEquals(15000.0, salaire, 0.01);
    }

    @Test
    void testCalculate_SalaireBase_Zero() {
        // Given
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "Mike", 0, 5, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        assertEquals(0.0, salaire, 0.01);
    }

    @Test
    void testCalculate_SalaireBase_Negatif() {
        // Given - cas limite, même si cela ne devrait pas arriver
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "Nina", -1000, 5, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // -1000 * 1.2 = -1200 (le calcul s'applique même si négatif)
        assertEquals(-1200.0, salaire, 0.01);
    }

    @Test
    void testCalculate_ExperienceNegative() {
        // Given - cas limite
        Employe emp = new Employe(TypeEmploye.DEVELOPPEUR, "Oscar", 50000, -1, "IT");

        // When
        double salaire = SalaryCalculator.calculate(emp);

        // Then
        // 50000 * 1.2 = 60000 (pas de bonus car expérience < 5)
        assertEquals(60000.0, salaire, 0.01);
    }
}

