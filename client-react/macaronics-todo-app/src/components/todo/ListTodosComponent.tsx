import React from 'react';
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

const ListTodosComponent: React.FC = () => {
  const today = new Date();
  const targetDate = new Date(today.getFullYear() + 12, today.getMonth(), today.getDate());

  const todos = [
    { id: 1, description: "test", done: false, targetDate },
    { id: 2, description: "Learn Full Stack Dev", done: false, targetDate },
    { id: 3, description: "Learn DevOps", done: false, targetDate },
  ];

  return (
    <div className="mt-20 flex md:justify-center">
      <div className="w-96  md:w-full md:max-w-6xl px-4">
        <Table className='w-full'>
          <TableCaption>할 일 목록</TableCaption>
          <TableHeader className="bg-slate-200">
            <TableRow>
              <TableHead className="w-[100px]">ID</TableHead>
              <TableHead className="text-center">내용</TableHead>
              <TableHead className="text-center">완료 여부</TableHead>
              <TableHead className="text-center">마감일</TableHead>
            </TableRow>
          </TableHeader>

          <TableBody>
            {todos.map((todo) => (
              <TableRow key={todo.id} className="border-b">
                <TableCell className="font-medium">{todo.id}</TableCell>
                <TableCell>{todo.description}</TableCell>
                <TableCell>{todo.done.toString()}</TableCell>
                <TableCell>{todo.targetDate.toDateString()}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
}

export default ListTodosComponent;
