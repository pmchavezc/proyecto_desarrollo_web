import React, {useEffect, useState} from 'react'
import { Link } from 'react-router-dom'

export default function HeaderLogin({ homePath = '/Home' }) {
    const [open, setOpen] = useState(false)
    const [theme, setTheme] = useState(() => localStorage.getItem('fa_theme') || 'light')

    useEffect(() => {
        document.body.classList.toggle('dark', theme === 'dark')
        localStorage.setItem('fa_theme', theme)
    }, [theme])

    return (
        <header className="header">
            <div className="header__brand">
                <h1>Future Academy</h1>
            </div>
            <div className="header__right">
                <button className="theme-toggle" onClick={() => setTheme(t => t === 'dark' ? 'light' : 'dark')}>
                    {theme === 'dark' ? '☀️' : '🌙'}
                </button>
                <button className="header__burger" onClick={() => setOpen(o => !o)}>☰</button>
            </div>
            <div className="header__right">
                <Link to={homePath} className="btn">
                    <h1>Inicio</h1>
                </Link>
            </div>
        </header>
    )
}