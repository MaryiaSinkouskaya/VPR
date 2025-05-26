package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.NoteRequestDto;
import com.vpr.app.dto.request.mappers.NoteConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Note;
import com.vpr.app.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Notes", description = "API for accessing probands' notes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/note")
public class NoteController {
  private final NoteService noteService;
  private final NoteConverter noteConverter;

  /**
   * Retrieves all notes.
   *
   * @return a list of all notes
   */
  @GetMapping
  @Operation(summary = "Get all notes", description = "Retrieves a list of all proband notes.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of notes")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<List<Note>> getNotes() {
    List<Note> notes = noteService.findAll();
    return ResponseEntity.ok(notes);
  }

  /**
   * Retrieves a note by its ID.
   *
   * @param id the ID of the note
   *
   * @return the note with the specified ID
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get a note by ID", description = "Retrieves the note with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the note")
  @ApiResponse(responseCode = "404", description = "Note not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<Note> getNoteById(@PathVariable(name = "id") long id) {
    Note note = noteService.findById(id);
    return ResponseEntity.ok(note);
  }

  /**
   * Creates a new note.
   *
   * @param noteDto the DTO for the note to create
   *
   * @return the created note
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new note", description = "Creates a new note with the provided details.")
  @ApiResponse(responseCode = "201", description = "Note successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Note> createNote(
      @Validated(OnCreate.class) @RequestBody NoteRequestDto noteDto) {
    Note note = noteConverter.toEntity(noteDto);
    Note createdNote = noteService.create(note);
    return ResponseEntity.status(CREATED).body(createdNote);
  }

  /**
   * Updates an existing note.
   *
   * @param noteDto the DTO for the note to update
   *
   * @return the updated note
   */
  @PatchMapping
  @Operation(summary = "Update a note", description = "Updates an existing note with the provided details.")
  @ApiResponse(responseCode = "200", description = "Note successfully updated")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @ApiResponse(responseCode = "404", description = "Note not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Note> updateNote(
      @Validated(OnUpdate.class) @RequestBody NoteRequestDto noteDto) {
    Note note = noteConverter.toEntity(noteDto);
    Note updatedNote = noteService.update(note);
    return ResponseEntity.ok(updatedNote);
  }

  /**
   * Deletes a note by its ID.
   *
   * @param id the ID of the note to delete
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a note", description = "Deletes the note with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Note successfully deleted")
  @ApiResponse(responseCode = "404", description = "Note not found")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteNoteById(@PathVariable(name = "id") long id) {
    noteService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

