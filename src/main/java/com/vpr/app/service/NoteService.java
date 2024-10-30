package com.vpr.app.service;

import com.vpr.app.entity.Note;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface NoteService {
  Note findById(long id);

  List<Note> findAll();

  Note create(Note note);

  Note update(Note note);

  void delete(long id);
}
