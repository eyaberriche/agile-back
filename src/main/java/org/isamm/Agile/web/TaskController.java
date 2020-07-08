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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    public TaskDao taskDao;


    @GetMapping("all")
    public List<Task> getAll()
            throws ResourceNotFoundException {
        return taskDao.findAll();
    }

    @GetMapping("allbyus/{id}")
    public List<Task> getByus(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return taskDao.findByUserStory(id);
    }

    @GetMapping("allbysprint/{id}")
    public List<Task> getBysprint(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return taskDao.findBySprint(id);
    }

    @GetMapping("allbyTeam/{id}")
    public List<Task> getTaskByTeamMember(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return taskDao.findAllByUserId(id);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createNewtask(@RequestBody Task taskrequest) {

        taskrequest.setStatus(StatusTask.TODO);
        taskrequest.setCreationDate(LocalDate.now().plusDays(1));
        taskrequest.setEstimationDate(taskrequest.getEstimationDate().plusDays(2));
        taskDao.save(taskrequest);
        return ResponseEntity.ok(new MessageResponse(taskrequest.getTitle()+""+LocalDate.now()));
    }
    @PostMapping("/taskIntimesheet")
    public ResponseEntity<?> newTaskInTimesheet(@RequestBody Task taskrequest) {

        taskrequest.setStatus(StatusTask.INDEFINED);
        taskrequest.setUserStory(null);
        taskrequest.setCreationDate(LocalDate.now().plusDays(1));
        taskrequest.setEstimationDate(taskrequest.getEstimationDate().plusDays(2));
        taskDao.save(taskrequest);

        return ResponseEntity.ok(new MessageResponse(taskrequest.getTitle()+""));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody Task taskrequest) throws ResourceNotFoundException {

        Task task = taskDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task not found for this id :: " +
                        id));
        task.setTitle(taskrequest.getTitle());
        task.setContent(taskrequest.getContent());
        if (taskrequest.getStatus()==null)
        { task.setStatus(task.getStatus());
        }
        else {
        task.setStatus(taskrequest.getStatus());}
        task.setCreationDate(task.getCreationDate().plusDays(1));
        if(task.getEstimationDate()==taskrequest.getEstimationDate())
        {task.setEstimationDate(task.getEstimationDate().minusDays(1));}
        else
        {task.setEstimationDate(taskrequest.getEstimationDate().plusDays(2));}
        taskDao.save(task);
        return ResponseEntity.ok(new MessageResponse(task.getTitle()+"-"+task.getContent()+"-"+task.getEndDate()+"-"+task.getStatus()+"-"+task.isCloture()));

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
    @PostMapping("/cloturer" )
    public  Task clotureTask(@RequestBody Task task) {

       Task tsk = taskDao.findById(task.getId()).orElse(null);
        tsk.setCloture(true);
        tsk.setEndDate(LocalDate.now().plusDays(1));
        return taskDao.save(tsk);
    }

}




