package taskmanager.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskmanager.demo.dto.CreateNoteDTO;
import taskmanager.demo.dto.CreateNoteResponseDTO;
import taskmanager.demo.entities.NoteEntity;
import taskmanager.demo.services.NotesService;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes = notesService.getNodesForTask(taskId);

        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(
            @PathVariable("taskId") Integer taskId,
            @RequestBody CreateNoteDTO body
            ){
        var note = notesService.addNoteForTask(taskId,body.getTitle(),body.getBody());
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId,note));
    }

}
