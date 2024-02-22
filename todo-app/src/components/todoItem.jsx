import React, { useEffect, useState } from "react";


const TodoItem = (props) => {
    const [todoItem, setTodoItem]=useState(props.data);
    ///isDirty is used to stop recursive putrequest we want it will change only one time for the user reaction on check box
    const [isDirty, setDirty] = useState(false);
//    function updateIsdone (){
//     setTodoItem({...TodoItem,isDone: !TodoItem.isDone})
//    }
////////////////////////////////////////////////////////////////////////
//to view the changes that happend in react app frontend create useEffect hook

    useEffect(() => {
        if (isDirty){
        fetch(`http://localhost:8080/api/todoItems/${todoItem.id}`, {
            method : "PUT",
            headers:{
                "content-type" : "application/json",
            },
             body : JSON.stringify(todoItem),
    
        })
        .then((response) => response.json())
        .then((data) => {
            setDirty(false);
         setTodoItem(data);
    });

}



    // console.log("Hey the todo item just changed", TodoItem);
}, [todoItem, isDirty]);



    return (
        // *****   <> this is fragmentation which is used to wrap the object in react********
    <> 
      <input type="checkbox" checked={todoItem.isDone}
       onChange={() =>  {
        setDirty(true);
        setTodoItem({...todoItem,isDone: !todoItem.isDone})}}/> {" "}
      <span>{todoItem.task}</span>
      </>
    );
};
export default TodoItem;