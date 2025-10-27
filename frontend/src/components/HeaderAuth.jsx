import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

export default function HeaderAuth() {
  const [open, setOpen] = useState(false)
  const [theme, setTheme] = useState(() => localStorage.getItem('fa_theme') || 'light')

  useEffect(() => {
    document.body.classList.toggle('dark', theme === 'dark')
    localStorage.setItem('fa_theme', theme)
  }, [theme])

  const logout = () => {
    localStorage.removeItem('fa_basic')
    // redirect to login
    window.location.href = '/login'
  }

  return (
    <header className="header">
      <div className="header__brand"><h1>Future Academy</h1></div>
      <div className="header__right">
        <button className="theme-toggle" onClick={() => setTheme(t => t === 'dark' ? 'light' : 'dark')}>
          {theme === 'dark' ? '☀️' : '🌙'}
        </button>
        <button className="header__burger" onClick={() => setOpen(o => !o)}>☰</button>
      </div>

      <nav className={open ? 'header__nav header__nav--open' : 'header__nav'} onClick={() => setOpen(false)}>
        <Link to="/mis-tareas">Mis tareas</Link>
        <Link to="/tareas">Tareas Pendientes</Link>
        <button className="btn" onClick={logout}>Cerrar sesión</button>
      </nav>
    </header>
  )
}

