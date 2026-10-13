package tn.esprit.backend.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.backend.entity.Entreprise;
import tn.esprit.backend.entity.Equipe;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.repository.EntrepriseRepository;
import tn.esprit.backend.repository.EquipeRepository;
import tn.esprit.backend.repository.ProjetRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private EntrepriseRepository entrepriseRepository;

    @Mock
    private ProjetRepository projetRepository;

    @InjectMocks
    private EquipeServiceImpl service;

    @Test
    void addEquipe_saveEtRetourne() {
        Equipe e = new Equipe();
        e.setNom("DevOps");
        when(equipeRepository.save(e)).thenReturn(e);

        assertSame(e, service.addEquipe(e));
        verify(equipeRepository).save(e);
    }

    @Test
    void updateEquipe_saveEtRetourne() {
        Equipe e = new Equipe();
        e.setId(1L);
        when(equipeRepository.save(e)).thenReturn(e);

        assertSame(e, service.updateEquipe(e));
        verify(equipeRepository).save(e);
    }

    @Test
    void deleteEquipe_appelleDeleteById() {
        service.deleteEquipe(1L);

        verify(equipeRepository).deleteById(1L);
    }

    @Test
    void getEquipeById_trouve() {
        Equipe e = new Equipe();
        e.setId(1L);
        when(equipeRepository.findById(1L)).thenReturn(Optional.of(e));

        assertSame(e, service.getEquipeById(1L));
    }

    @Test
    void getEquipeById_introuvableRetourneNull() {
        when(equipeRepository.findById(99L)).thenReturn(Optional.empty());

        assertNull(service.getEquipeById(99L));
    }

    @Test
    void getAllEquipes_retourneLaListe() {
        when(equipeRepository.findAll()).thenReturn(List.of(new Equipe(), new Equipe()));

        assertEquals(2, service.getAllEquipes().size());
    }

    @Test
    void getEquipesByEntreprise_retourneLaListe() {
        when(equipeRepository.findByEntrepriseId(5L)).thenReturn(List.of(new Equipe()));

        assertEquals(1, service.getEquipesByEntreprise(5L).size());
        verify(equipeRepository).findByEntrepriseId(5L);
    }

    @Test
    void assignEquipeToEntreprise_associeEtSauvegarde() {
        Equipe equipe = new Equipe();
        equipe.setId(1L);
        Entreprise entreprise = new Entreprise();
        entreprise.setId(2L);
        when(equipeRepository.findById(1L)).thenReturn(Optional.of(equipe));
        when(entrepriseRepository.findById(2L)).thenReturn(Optional.of(entreprise));
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = service.assignEquipeToEntreprise(1L, 2L);

        assertSame(entreprise, result.getEntreprise());
        verify(equipeRepository).save(equipe);
    }

    @Test
    void assignEquipeToProjet_ajouteLeProjetEtSauvegarde() {
        Equipe equipe = new Equipe();
        equipe.setId(1L);
        Projet projet = new Projet();
        projet.setId(3L);
        when(equipeRepository.findById(1L)).thenReturn(Optional.of(equipe));
        when(projetRepository.findById(3L)).thenReturn(Optional.of(projet));
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = service.assignEquipeToProjet(1L, 3L);

        assertEquals(1, result.getProjets().size());
        assertSame(projet, result.getProjets().get(0));
        verify(equipeRepository).save(equipe);
    }
}
