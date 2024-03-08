import logo from './logo.svg';
import './App.css';
import { Fragment, useEffect, useState } from 'react';
import TodoItem from './components/todoItem';

function App() {

  const [todoItems, setTodoItems] = useState(null);

  useEffect(() => {
    //do something on load
    console.log("loaded up");
    if(!todoItems){
    fetch("http://localhost:8080/api/todoItems")
    .then((response) => response.json())
    .then((data) => {
      console.log("The items list", data);
      setTodoItems(data);
    });
  }

    ///**** calling the async function
    // api_call()
  }, [todoItems]);
   
////*** THIS IS A async function which we can use to wait until api response and return the data
  // async function  api_call() {
  //   try {
  //      const result=await fetch('http://localhost:8080/api/todoItems')
  //      const data=result.json()
  //      console.log(data);
  //   } catch {
  //     console.log("Failed ot fetch the result")
  //   }


  // }
////////////////////////////////************************************** 
// ternary operator
// if(something)
//     do item 1
//  else
//     do item 2
// 
// something ? (do item 1): (do item 2)


function createnewItem(){
  fetch("http://localhost:8080/api/todoItems", {
    headers: {
      "content-type" : "application/json",
    },
    method: "POST",
  }).then(response => response.json())
  .then(aTodoItem =>{ 
    console.log(aTodoItem)
    setTodoItems([...todoItems, aTodoItem]);
  });
}
function handleDeleteitem(item){
  const updatedTodoitem = todoItems.filter((aTodoItem)=>aTodoItem.id !== item.id);
  console.log("updated todoItems",updatedTodoitem);
  setTodoItems([...updatedTodoitem
  ]);
}

  return (
    <>
    <div>
      <button onClick={createnewItem}>Add new todo-Item</button>
    </div>
  <div>
    {todoItems
    ? todoItems.map((todoItem) => {
       return <TodoItem key={todoItem.id} data={todoItem} emitDeleteTodoItem={handleDeleteitem}/>;
    
  
      })
    : "loading data...."}
    </div>
    </>
  );
}

export default App;
