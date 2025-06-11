package com.vpr.app.controller;

import com.vpr.app.dto.request.NoteRequestDto;
import com.vpr.app.dto.request.mappers.NoteConverter;
import com.vpr.app.entity.Note;
import com.vpr.app.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest {

    private static final long NOTE_ID = 1L;

    @Mock
    private NoteService noteService;

    @Mock
    private NoteConverter noteConverter;

    @InjectMocks
    private NoteController controller;

    private Note note;

    @BeforeEach
    void init() {
        note = new Note();
        note.setId(NOTE_ID);
        note.setNote("Test Note");
    }

    @Test
    void getNotes_shouldReturnAll() {
        when(noteService.findAll()).thenReturn(List.of(note));

        ResponseEntity<List<Note>> response = controller.getNotes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(note.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getNoteById_shouldReturnSingle() {
        when(noteService.findById(NOTE_ID)).thenReturn(note);

        ResponseEntity<Note> response = controller.getNoteById(NOTE_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(NOTE_ID, response.getBody().getId());
    }

    @Test
    void createNote_shouldReturnCreated() {
        NoteRequestDto dto = new NoteRequestDto();
        when(noteConverter.toEntity(dto)).thenReturn(note);
        when(noteService.create(note)).thenReturn(note);

        ResponseEntity<Note> response = controller.createNote(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(note.getId(), response.getBody().getId());
    }

    @Test
    void updateNote_shouldReturnUpdated() {
        NoteRequestDto dto = new NoteRequestDto();
        when(noteConverter.toEntity(dto)).thenReturn(note);
        when(noteService.update(note)).thenReturn(note);

        ResponseEntity<Note> response = controller.updateNote(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(note.getId(), response.getBody().getId());
    }

    @Test
    void deleteNoteById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteNoteById(NOTE_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}