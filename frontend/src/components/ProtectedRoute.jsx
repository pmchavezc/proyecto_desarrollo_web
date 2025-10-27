import React from 'react'
import { Navigate } from 'react-router-dom'
export default function ProtectedRoute({ children }){
  const ok = !!localStorage.getItem('fa_basic')
  return ok ? children : <Navigate to="/login" />
}
