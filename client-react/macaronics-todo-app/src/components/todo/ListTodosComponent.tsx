import React from 'react'
import {
    Table,
    TableBody,
    TableCaption,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
  } from "@/components/ui/table"

  
const ListTodosComponent:React.FC = () => {
  
  const todos=[
     {id:1, description:"test"},
     {id:2, description:"Learn Full Statck Dev"},
     {id:3, description:"Learn DevOps"},
  ]


  return (
    <div className='ListTodosComponent'>       
       

        <div className='mt-20  flex flex-row items-center justify-center'>
            <div className='w-3/12'></div>

            <Table className='w-9/12'>
                <TableCaption></TableCaption>
                <TableHeader className='bg-slate-200'>
                    <TableRow>
                    <TableHead className="w-[100px]">id</TableHead>                
                    <TableHead className='text-center'>내용</TableHead>
                    
                    </TableRow>
                </TableHeader>
               
                <TableBody>

                    {todos.map((todo) => (
                         <TableRow>
                            <TableCell className="font-medium">{todo.id}</TableCell>
                            <TableCell>{todo.description}</TableCell>                    
                        </TableRow>

                    ))}
                    

                </TableBody>
        </Table>


        </div>

    </div>
  )
}

export default ListTodosComponent
