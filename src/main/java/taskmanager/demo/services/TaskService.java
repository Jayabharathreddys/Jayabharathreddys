package taskmanager.demo.services;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import taskmanager.demo.entities.TaskEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {

    ArrayList<TaskEntity> tasks = new ArrayList<>();
    public static int taskID = 1;

    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");
    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(taskID);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineFormatter.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskID++;
        return task;
    }

    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }

    public TaskEntity getTaskById(int id){
        //tasks.stream().findAny().filter(task-> task.getId()==id).orElse(null);
        for(TaskEntity task: tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id, String description, String deadline, Boolean completed) throws ParseException {

        var task = getTaskById(id);
        if (task == null)
            return null;
        if (description!=null)
            task.setDescription(description);
        if (deadline!=null)
            task.setDeadline(deadlineFormatter.parse(deadline) );
        if(completed!=null)
            task.setCompleted(completed);

        return task;
    }
}
