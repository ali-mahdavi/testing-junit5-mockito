package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class SpecialitySDJpaServiceTest {
    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deleteById() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete By Id At Most 5")
    void deleteByIdAtMost() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete By Id At Most 5")
    void deleteByIdNever() {
        // service.deleteById(1l);
        //  service.deleteById(1l);
        verify(specialtyRepository, never()).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }


    @Test
    void testDelete() {
        service.delete(new Speciality());
    }

    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality();
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
        Speciality foundSpacialty = service.findById(1L);
        assertThat(foundSpacialty).isNotNull();
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);
        verify(specialtyRepository).delete(any(speciality.getClass()));


    }
}
