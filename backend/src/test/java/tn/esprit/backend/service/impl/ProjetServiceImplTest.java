package tn.esprit.backend.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.repository.ProjetRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetServiceImplTest {

    @Mock
    private ProjetRepository projetRepository;

    @InjectMocks
    private ProjetServiceImpl service;

    @Test
    void addProjet_saveEtRetourne() {
        Projet p = new Projet();
        p.setSujet("Gestion de projets");
        when(projetRepository.save(p)).thenReturn(p);

        assertSame(p, service.addProjet(p));
        verify(projetRepository).save(p);
    }

    @Test
    void updateProjet_saveEtRetourne() {
        Projet p = new Projet();
        p.setId(1L);
        when(projetRepository.save(p)).thenReturn(p);

        assertSame(p, service.updateProjet(p));
        verify(projetRepository).save(p);
    }

    @Test
    void deleteProjet_appelleDeleteById() {
        service.deleteProjet(1L);

        verify(projetRepository).deleteById(1L);
    }

    @Test
    void getProjetById_trouve() {
        Projet p = new Projet();
        p.setId(1L);
        when(projetRepository.findById(1L)).thenReturn(Optional.of(p));

        assertSame(p, service.getProjetById(1L));
    }

    @Test
    void getProjetById_introuvableRetourneNull() {
        when(projetRepository.findById(99L)).thenReturn(Optional.empty());

        assertNull(service.getProjetById(99L));
    }

    @Test
    void getAllProjets_retourneLaListe() {
        when(projetRepository.findAll()).thenReturn(List.of(new Projet()));

        assertEquals(1, service.getAllProjets().size());
    }
}
