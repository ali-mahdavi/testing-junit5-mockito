package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VisitSDJpaServiceTest {
    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> founVisits = service.findAll();
        verify(visitRepository).findAll();
        assertThat(founVisits).hasSize(1);

    }

    @Test
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        verify(visitRepository).findById(anyLong());
        assertThat(foundVisit).isNotNull();
    }

    @Test
    void save() {
        Visit visit=new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit= service.save(new Visit());
        verify(visitRepository).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @Test
    void delete() {
        Visit visit=new Visit();
        service.delete(visit);
        verify(visitRepository).delete(any(Visit.class));

    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }
}
