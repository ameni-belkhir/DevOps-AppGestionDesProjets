package tn.esprit.backend.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.entity.ProjetDetaille;
import tn.esprit.backend.repository.ProjetDetailleRepository;
import tn.esprit.backend.repository.ProjetRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetDetailleServiceImplTest {

    @Mock
    private ProjetDetailleRepository projetDetailleRepository;

    @Mock
    private ProjetRepository projetRepository;

    @InjectMocks
    private ProjetDetailleServiceImpl service;

    @Test
    void addProjetDetaille_saveEtRetourne() {
        ProjetDetaille pd = new ProjetDetaille();
        pd.setTechnologie("Spring Boot");
        when(projetDetailleRepository.save(pd)).thenReturn(pd);

        assertSame(pd, service.addProjetDetaille(pd));
        verify(projetDetailleRepository).save(pd);
    }

    @Test
    void updateProjetDetaille_saveEtRetourne() {
        ProjetDetaille pd = new ProjetDetaille();
        pd.setId(1L);
        when(projetDetailleRepository.save(pd)).thenReturn(pd);

        assertSame(pd, service.updateProjetDetaille(pd));
        verify(projetDetailleRepository).save(pd);
    }

    @Test
    void deleteProjetDetaille_appelleDeleteById() {
        service.deleteProjetDetaille(1L);

        verify(projetDetailleRepository).deleteById(1L);
    }

    @Test
    void getProjetDetailleById_trouve() {
        ProjetDetaille pd = new ProjetDetaille();
        pd.setId(1L);
        when(projetDetailleRepository.findById(1L)).thenReturn(Optional.of(pd));

        assertSame(pd, service.getProjetDetailleById(1L));
    }

    @Test
    void getProjetDetailleById_introuvableRetourneNull() {
        when(projetDetailleRepository.findById(99L)).thenReturn(Optional.empty());

        assertNull(service.getProjetDetailleById(99L));
    }

    @Test
    void getAllProjetsDetailles_retourneLaListe() {
        when(projetDetailleRepository.findAll()).thenReturn(List.of(new ProjetDetaille()));

        assertEquals(1, service.getAllProjetsDetailles().size());
    }

    @Test
    void getProjetDetaillesByProjet_retourneLaListe() {
        when(projetDetailleRepository.findByProjetId(4L))
                .thenReturn(List.of(new ProjetDetaille(), new ProjetDetaille()));

        assertEquals(2, service.getProjetDetaillesByProjet(4L).size());
        verify(projetDetailleRepository).findByProjetId(4L);
    }

    @Test
    void assignProjetDetailleToProjet_associeEtSauvegarde() {
        ProjetDetaille pd = new ProjetDetaille();
        pd.setId(1L);
        Projet projet = new Projet();
        projet.setId(2L);
        when(projetDetailleRepository.findById(1L)).thenReturn(Optional.of(pd));
        when(projetRepository.findById(2L)).thenReturn(Optional.of(projet));
        when(projetDetailleRepository.save(pd)).thenReturn(pd);

        ProjetDetaille result = service.assignProjetDetailleToProjet(1L, 2L);

        assertSame(projet, result.getProjet());
        verify(projetDetailleRepository).save(pd);
    }
}
