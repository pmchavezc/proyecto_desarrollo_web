import React from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'
import Home from './Home'
import Login from './Login'
import Dashboard from './Dashboard'
import Tareas from './Tareas'
import MisTareas from './MisTareas'
import ProtectedRoute from '../components/ProtectedRoute'

export default function App(){
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/dashboard" element={<ProtectedRoute><Dashboard /></ProtectedRoute>} />
      <Route path="/tareas" element={<ProtectedRoute><Tareas /></ProtectedRoute>} />
      <Route path="/mis-tareas" element={<ProtectedRoute><MisTareas /></ProtectedRoute>} />
      <Route path="*" element={<Navigate to="/" />} />
    </Routes>
  )
}
