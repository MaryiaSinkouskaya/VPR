package com.vpr.app.controller;

import com.vpr.app.dto.request.NoteRequestDto;
import com.vpr.app.entity.Note;
import com.vpr.app.service.NoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Notes", description = "API for accessing probands notes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/note")
public class NoteController {
    private final NoteService noteService;

    @GetMapping()
    public List<Note> getNotes() {
        return noteService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Note getNoteById(@PathVariable(name = "id") long id) {
        return noteService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Note createNote(@Valid @RequestBody NoteRequestDto noteDto) {
        Note note = Note.builder().date(noteDto.getDate()).note(noteDto.getNote()).build();
        return noteService.create(note);
    }

    @PatchMapping()
    public Note updateNote(@Valid @RequestBody NoteRequestDto noteDto) {
        Note note = Note.builder().id(noteDto.getId()).date(noteDto.getDate()).note(noteDto.getNote()).build();
        return noteService.update(note);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteNoteById(@PathVariable(name = "id") long id) {
        noteService.delete(id);
    }
}
