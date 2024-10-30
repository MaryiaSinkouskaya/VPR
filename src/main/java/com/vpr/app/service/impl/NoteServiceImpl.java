package com.vpr.app.service.impl;

import com.vpr.app.entity.Note;
import com.vpr.app.repository.NoteRepository;
import com.vpr.app.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {

  private final NoteRepository noteRepository;

  @Override
  public Note findById(long id) {
    return noteRepository.findById(id).orElse(new Note());
  }

  @Override
  public List<Note> findAll() {
    return noteRepository.findAll();
  }

  @Override
  public Note create(Note note) {
    return noteRepository.save(note);
  }

  @Override
  public Note update(Note note) {
    return noteRepository.save(note);
  }

  @Override
  public void delete(long id) {
    noteRepository.deleteById(id);
  }
}
