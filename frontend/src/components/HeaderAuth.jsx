import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios' //  limpiar header Authorization

export default function HeaderAuth() {
    const [open, setOpen] = useState(false)
    const [theme, setTheme] = useState(() => localStorage.getItem('fa_theme') || 'light')

    useEffect(() => {
        document.body.classList.toggle('dark', theme === 'dark')
        localStorage.setItem('fa_theme', theme)
    }, [theme])

    //  limpia credenciales y hace hard redirect
    const logout = (e) => {
        e?.preventDefault?.()
        e?.stopPropagation?.() // evita que el onClick del <nav> interfiera

        try {
            localStorage.removeItem('fa_basic')
            if (axios?.defaults?.headers?.common?.Authorization) {
                delete axios.defaults.headers.common.Authorization
            }
        } finally {
            // recarga + redirige (sin dejar historial)
            window.location.replace('/login')
            // red de seguridad si algo bloquea replace
            setTimeout(() => window.location.assign('/login'), 25)
        }
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

            {/* Este onClick cierra el menú; con stopPropagation en logout no molesta */}
            <nav className={open ? 'header__nav header__nav--open' : 'header__nav'} onClick={() => setOpen(false)}>
                <Link to="/mis-tareas">Mis tareas</Link>
                <Link to="/tareas">Tareas Pendientes</Link>
                {/* importante: type="button" por si algún día esto cae dentro de un <form> */}
                <button type="button" className="btn" onClick={logout}>Cerrar sesión</button>
            </nav>
        </header>
    )
}