package com.vpr.app.controller;

import com.vpr.app.entity.Note;
import com.vpr.app.service.NoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
  public Note createNote(@RequestBody Note note) {
    return noteService.create(note);
  }

  @PutMapping()
  public Note updateNote(@RequestBody Note note) {
    return noteService.update(note);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteNoteById(@PathVariable(name = "id") long id) {
    noteService.delete(id);
  }
}
