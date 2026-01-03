import React, { useEffect, useState } from "react";

interface Student {
  id?: number;
  name: string;
  email: string;
}

function App() {
  const [students, setStudents] = useState<Student[]>([]);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");

  useEffect(() => {
  fetchStudents();
}, []);
  const fetchStudents = () => {
  fetch("/students")
    .then(res => res.json())
    .then(data => setStudents(data));
};


  const addStudent = () => {
    fetch("/students", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, email })
    })
      .then(res => res.json())
      .then(newStudent => {
        setStudents([...students, newStudent]);
        setName("");
        setEmail("");
      });
  };

const deleteStudent = async (id?: number) => {
  if (!id) return;

  await fetch(`/students/${id}`, {
    method: "DELETE",
  });

  fetchStudents();
};


  return (
    <div style={{ padding: "20px" }}>
      <h2>Student Manager</h2>

      <input
        placeholder="Name"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <input
        placeholder="Email"
        value={email}
        onChange={e => setEmail(e.target.value)}
      />
      <button onClick={addStudent}>Add</button>

      <ul>
        {students.map(s => (
          <li key={s.id}>
            {s.name} ({s.email})
            <button onClick={() => deleteStudent(s.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
