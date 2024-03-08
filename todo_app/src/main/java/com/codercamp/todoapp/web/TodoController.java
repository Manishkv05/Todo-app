package com.codercamp.todoapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codercamp.todoapp.domain.TodoItem;
import com.codercamp.todoapp.services.TodoServices;

//http://localhost/8080
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoController {
	
//***CRUD OPERATION MAPPING 
	
//***	create  =  Post   ='http://localhost/8080/api/domainObjectnames'
//***	read    =  Get    ='http://localhost/8080/api/domainObjectnames' or
//                          'http://localhost/8080/api/domainObjectnames/{id}'
//***   update  =  Put    = 'http://localhost/8080/api/domainObjectnames/{id}'
//***   delete  =  Delete ='http://localhost/8080/api/domainObjectnames/{id}'
	
	
	
	
	
	///front-end     java-server
	//  httpREquest --->Controller --->Services ---> Repository
	// front-end <---Controller <---Services <---
	@Autowired
	private TodoServices todoService;
	//Fetch all todo items (from database)
	@GetMapping("/api/todoItems")
	public ResponseEntity<?> fetchAllItmes (){
		List<TodoItem> todoItems = todoService.fetchAllItmes();
		
		// return ResponseEntity.status.status(HttpStatus.OK).body(todoItems); 
		//we can use above shorthand form to return OK status
		return ResponseEntity.ok(todoItems);
		
	}
	@PutMapping("/api/todoItems/{id}")
	public ResponseEntity<?> updateTOdoItem(@PathVariable Integer id,@RequestBody TodoItem todoItem){
		
	//call the service	
		//get the data back from the server
	TodoItem updatedTodoItem=todoService.updateTodoItem(id,todoItem);
	//send it (back to front-end)
	
	return ResponseEntity.ok(updatedTodoItem);
	
	}
	@PostMapping("/api/todoItems")
	public ResponseEntity<?> createNewTodoitem(){
		TodoItem todoitem = todoService.createNewTodoItem();
      return ResponseEntity.ok(todoitem);
}
	@DeleteMapping("api/todoItems/{id}")
	 public ResponseEntity<?> DelteTodoItem(@PathVariable Integer id){
		 todoService.deleteTodoItem(id);
		 return ResponseEntity.ok("ok");
		
	}
}














