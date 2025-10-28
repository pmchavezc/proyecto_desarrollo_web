import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

export default function Header() {
  const [open, setOpen] = useState(false)
  const [theme, setTheme] = useState(() => localStorage.getItem('fa_theme') || 'light')

  useEffect(() => {
    document.body.classList.toggle('dark', theme === 'dark')
    localStorage.setItem('fa_theme', theme)
  }, [theme])

  return (
    <header className="header">
      <div className="header__brand"><h1>Future Academy</h1></div>
      <div className="header__right">
        <button className="theme-toggle" onClick={()=>setTheme(t=>t==='dark'?'light':'dark')}>
          {theme === 'dark' ? '☀️' : '🌙'}
        </button>
        <button className="header__burger" onClick={()=>setOpen(o=>!o)}>☰</button>
      </div>
      <nav className={open ? 'header__nav header__nav--open' : 'header__nav'} onClick={()=>setOpen(false)}>
        {/* Links principales */}
          <Link to="/Home"><h2>inicio</h2></Link>
        <a href="#contacto"><h2>Contáctanos</h2></a>
        <Link to="/login"><h2>Login</h2></Link>
        {/* Enlaces visibles solo si hay sesión */}
        {localStorage.getItem('fa_basic') ? <>
        </> : null}
    
      </nav>
    </header>
  )
}
