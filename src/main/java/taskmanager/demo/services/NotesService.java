package taskmanager.demo.services;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import taskmanager.demo.entities.NoteEntity;
import taskmanager.demo.entities.TaskEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NotesService {
    private TaskService taskService;

    private HashMap<Integer, TaskNotesHolder> taskNoteHolders = new HashMap<>();
    public NotesService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNotesHolder{
        protected   int noteId =1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    public List<NoteEntity> getNodesForTask(int taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null)
            return  null;

        if (taskNoteHolders.get(taskId) == null)
            taskNoteHolders.put(taskId, new TaskNotesHolder());

        return taskNoteHolders.get(taskId).notes;
    }

    public NoteEntity addNoteForTask(int taskId, String title, String body){
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null)
            return null;
        if (taskNoteHolders.get(taskId) == null)
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        TaskNotesHolder taskNotesHolder = taskNoteHolders.get(taskId);
        NoteEntity note = new NoteEntity();
        note.setId(taskId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;

        return note;
    }

}
