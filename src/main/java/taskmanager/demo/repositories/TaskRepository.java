package taskmanager.demo.repositories;

import org.springframework.stereotype.Repository;
import taskmanager.demo.entities.TaskEntity;

import java.util.ArrayList;

@Repository
public class TaskRepository {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
}
