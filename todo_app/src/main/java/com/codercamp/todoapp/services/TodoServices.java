package com.codercamp.todoapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codercamp.todoapp.domain.TodoItem;
import com.codercamp.todoapp.repository.TodoRepository;

@Service
public class TodoServices {
	
	@Autowired
	private TodoRepository todoRepo;
		public List<TodoItem> fetchAllItmes() {
			return todoRepo.fetchAllTodoItems();
			
		}
		public TodoItem updateTodoItem(Integer id, TodoItem todoItem) {
			Optional<TodoItem> todoOpt = todoRepo.fetchAllTodoItems()
			                                     .stream()
			                                     .filter(item -> item.getId().equals(id))
			                                     .findAny();
			
			if(todoOpt.isPresent()) {
				TodoItem item = todoOpt.get();
				               //this is the  data or changed data from the front-end that we just passsed through the controller
				item.setIsDone(todoItem.getIsDone());
				item.setTask(todoItem.getTask());
				return item;
			}
			return null;
		}
		public TodoItem createNewTodoItem() {
			// TODO Auto-generated method stub
			TodoItem todoItem = new TodoItem();
			todoItem.setIsDone(false);
			todoItem = todoRepo.save(todoItem);
			todoItem.setTask("Task#"+todoItem.getId());
			return todoItem;
			
			
		}
		public TodoItem deleteTodoItem(Integer id) {
			TodoItem todoItem = new TodoItem();
			todoRepo.delet(id);
			return null;
		} 

}
