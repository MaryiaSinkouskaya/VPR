package com.vpr.app.service;

import com.vpr.app.entity.Note;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.NoteRepository;
import com.vpr.app.service.impl.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteServiceImpl service;

    private Note note;

    @BeforeEach
    void setUp() {
        note = new Note();
        note.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // given
        when(noteRepository.findById(ID)).thenReturn(Optional.of(note));

        // when
        Note result = service.findById(ID);

        // then
        assertThat(result).isSameAs(note);
        verify(noteRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // given
        when(noteRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // given
        List<Note> list = List.of(new Note(), new Note());
        when(noteRepository.findAll()).thenReturn(list);

        // when
        List<Note> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(noteRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // given
        when(noteRepository.save(note)).thenReturn(note);

        // when
        Note result = service.create(note);

        // then
        assertThat(result).isSameAs(note);
        verify(noteRepository).save(note);
    }

    @Test
    void update_shouldSaveEntity() {
        // given
        when(noteRepository.save(note)).thenReturn(note);

        // when
        Note result = service.update(note);

        // then
        assertThat(result).isSameAs(note);
        verify(noteRepository).save(note);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // given
        when(noteRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(noteRepository).existsById(ID);
        verify(noteRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // given
        when(noteRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}
