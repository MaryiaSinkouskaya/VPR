package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.NoteRequestDto;
import com.vpr.app.entity.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteConverter {

    /**
     * Converts NoteRequestDto to Note entity.
     *
     * @param dto the NoteRequestDto object
     * @return the converted Note entity
     */
    public Note toEntity(NoteRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Note note = new Note();
        if (dto.getId() != null) {
            note.setId(dto.getId());
        }

        note.setDate(dto.getDate());
        note.setNote(dto.getNote());
        return note;
    }

    /**
     * Converts Note entity to NoteRequestDto.
     *
     * @param note the Note entity
     * @return the converted NoteRequestDto object
     */
    public NoteRequestDto toDto(Note note) {
        if (note == null) {
            return null;
        }

        NoteRequestDto dto = new NoteRequestDto();
        dto.setId(note.getId());
        dto.setDate(note.getDate());
        dto.setNote(note.getNote());
        return dto;
    }
}

