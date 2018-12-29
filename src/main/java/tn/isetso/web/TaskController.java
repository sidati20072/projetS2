package tn.isetso.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.isetso.dao.TaskRepository;
import tn.isetso.entities.Task;

@RestController
public class TaskController {

		@Autowired
		private TaskRepository taskRepository;
		
		@GetMapping("/tasks")
		public List<Task> listTasks(){
			return taskRepository.findAll();
		}
}
