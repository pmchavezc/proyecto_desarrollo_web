import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Header from '../components/Header'
import Footer from '../components/Footer'
import './login.css'
import api from '../api/api'

export default function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const navigate = useNavigate()

  const onSubmit = async (e) => {
    e.preventDefault(); setError('')
    try {
      const basic = btoa(`${email}:${password}`)
      const res = await api.get('/api/auth/me', { headers: { Authorization: `Basic ${basic}` } })
      localStorage.setItem('fa_basic', basic)
      localStorage.setItem('fa_user', JSON.stringify(res.data))
      navigate('/dashboard')
    } catch (err) {
      setError('Credenciales inválidas o usuario no existe')
    }
  }

  return (
    <div className="page">
      <Header />
      <section className="login">
        <h1>Iniciar sesión</h1>
        {error ? <div className="err">{error}</div> : null}
        <form onSubmit={onSubmit}>
          <div className="row"><label>Email</label><input value={email} onChange={e=>setEmail(e.target.value)} type="email" required /></div>
          <div className="row"><label>Contraseña</label><input value={password} onChange={e=>setPassword(e.target.value)} type="password" required /></div>
          <button type="submit" className="btn">Ingresar</button>
        </form>
      </section>
      <Footer />
    </div>
  )
}
