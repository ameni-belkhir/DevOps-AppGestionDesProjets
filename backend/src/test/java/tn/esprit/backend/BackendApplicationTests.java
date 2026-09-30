package tn.esprit.backend;

import org.junit.jupiter.api.Test;
import tn.esprit.backend.entity.Entreprise;
import tn.esprit.backend.entity.Equipe;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BackendApplicationTests {

    @Test
    void creation_dUneEquipe_viaConstructeur() {
        Entreprise entreprise = Entreprise.builder().nom("ESPRIT").adresse("Tunis").build();

        Equipe equipe = new Equipe(1L, "Team Alpha", "Backend", entreprise, new ArrayList<>());

        assertNotNull(equipe);
        assertEquals(1L, equipe.getId());
        assertEquals("Team Alpha", equipe.getNom());
        assertEquals("Backend", equipe.getSpecialite());
        assertEquals(entreprise, equipe.getEntreprise());
    }

    @Test
    void setters_et_getters_de_lEquipe() {
        Equipe equipe = new Equipe();
        assertNotNull(equipe);
        assertNotNull(equipe.getProjets());
        assertTrue(equipe.getProjets().isEmpty());

        equipe.setId(2L);
        equipe.setNom("Team Beta");
        equipe.setSpecialite("DevOps");

        assertEquals(2L, equipe.getId());
        assertEquals("Team Beta", equipe.getNom());
        assertEquals("DevOps", equipe.getSpecialite());
    }

    @Test
    void builder_de_lEquipe() {
        Entreprise entreprise = Entreprise.builder().nom("Vermeg").adresse("Ariana").build();

        Equipe equipe = Equipe.builder()
                .id(3L)
                .nom("Team Gamma")
                .specialite("Fullstack")
                .entreprise(entreprise)
                .projets(new ArrayList<>())
                .build();

        assertNotNull(equipe);
        assertEquals(3L, equipe.getId());
        assertEquals("Team Gamma", equipe.getNom());
        assertEquals("Fullstack", equipe.getSpecialite());
        assertEquals(entreprise, equipe.getEntreprise());
        assertNotNull(equipe.getProjets());
        assertTrue(equipe.getProjets().isEmpty());
    }
}
