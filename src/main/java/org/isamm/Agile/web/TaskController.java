package org.isamm.Agile.web;

import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.TaskDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.model.StatusTask;
import org.isamm.Agile.model.Task;
import org.isamm.Agile.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    public TaskDao taskDao;



    @GetMapping("allbyus/{id}")
    public List<Task> getByus(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return taskDao.findByUserStory(id);
    }
    @GetMapping("allbyTeam/{id}")
    public List<Task> getTaskByTeamMember(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return taskDao.findAllByUserId(id);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createNewtask(@RequestBody Task taskrequest) {
        if (taskDao.existsByTitleAndUserStoryId(taskrequest.getTitle(), taskrequest.getUserStory().getId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : le nom de tache est déjà existe dans la user story !"));
        }
        taskrequest.setStatus(StatusTask.TODO);
        taskrequest.setCreationDate(LocalDate.now());
        taskDao.save(taskrequest);
        return ResponseEntity.ok(new MessageResponse(taskrequest.getTitle()+""));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody Task taskrequest) throws ResourceNotFoundException {

        Task task = taskDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task not found for this id :: " +
                        id));
        if (taskrequest.getStatus()==null)
        { task.setStatus(task.getStatus());
        task.setEndDate(task.getEndDate());}
        else {
        task.setStatus(taskrequest.getStatus());
        if (task.getStatus() == StatusTask.DONE)
        {task.setEndDate(LocalDate.now());}
        else
        {task.setEndDate(null);}}
        if(taskrequest.getTitle()== null)
        {task.setTitle(task.getTitle());}
        else
        {task.setTitle(taskrequest.getTitle());}
        if(taskrequest.getContent()==null)
        {task.setContent(task.getContent());}
        else
        {task.setContent(taskrequest.getContent());}
        if(taskrequest.getCreationDate()==null)
        {task.setCreationDate(task.getCreationDate());}
        taskDao.save(task);
        return ResponseEntity.ok(new MessageResponse(task.getTitle()+"-"+task.getContent()+"-"+task.getEndDate()+"-"+task.getStatus()));

    }
    @GetMapping("/todo/{id}")
    public List<Task> getTaskToDo(@PathVariable(value = "id") Long id) {
        StatusTask status= StatusTask.TODO;
        return taskDao.findAllByUserStoryIdAndStatus(id , status);
    }
    @GetMapping("/doing/{id}") // list des tasks doing selon id de userstory
    public List<Task> getTaskdoing(@PathVariable(value = "id") Long id) {
        StatusTask status= StatusTask.DOING;
        return taskDao.findAllByUserStoryIdAndStatus(id , status);
    }
    @GetMapping("/done/{id}")
    public List<Task> getTaskdone(@PathVariable(value = "id") Long id) {
        StatusTask status= StatusTask.DONE;
        return taskDao.findAllByUserStoryIdAndStatus(id , status);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Task task = taskDao.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("userstory not found for this id :: " +
                        Id));

        taskDao.deleteById(Id);

        return ResponseEntity.ok(new MessageResponse(task.getTitle()+""+"supprimée !"));}
}




