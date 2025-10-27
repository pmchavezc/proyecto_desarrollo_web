import React, { useEffect, useState } from 'react'
import HeaderAuth from '../components/HeaderAuth'
import Footer from '../components/Footer'
import './tareas.css'
import api from '../api/api'
import {Link} from "react-router-dom";

export default function Tareas() {
  const [tareas, setTareas] = useState([])
  const [links, setLinks] = useState({})
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  const load = async () => {
    setLoading(true); setError('')
    try { const a = await api.get('/api/tareas/pendientes'); setTareas(a.data) }
    catch (e) { setError('Error cargando tareas') }
    finally { setLoading(false) }
  }
  useEffect(()=>{ load() }, [])

  const entregar = async (tareaId) => {
    const enlace = links[tareaId] || ''
    if (!enlace.startsWith('http://') && !enlace.startsWith('https://')) { alert('El enlace debe iniciar con http:// o https://'); return }
    try { await api.post('/api/tareas/entregar', { tareaId, enlace }); setLinks({ ...links, [tareaId]: '' }); await load() }
    catch (e) { alert('No se pudo entregar') }
  }

  return (
      <div className="page">
        <HeaderAuth />
        <section className="tareas">
          <h1>Tareas pendientes</h1>
          {loading ? <div>Cargando...</div> : error ? <div className="err">{error}</div> : (
              <div className="grid">
                {tareas.map(t => (
                    <article key={t.id} className="tarea">
                      <h3>{t.titulo}</h3>
                      <p><strong>Clase:</strong> {t.clase?.nombre}</p>
                      <p>{t.descripcion}</p>
                      <div className="entrega">
                        <input placeholder="Pega aquí el enlace de tu entrega"
                               value={links[t.id] || ''}
                               onChange={e => setLinks({ ...links, [t.id]: e.target.value })} />
                        <button className="btn" onClick={() => entregar(t.id)}>Entregar</button>
                      </div>
                    </article>
                ))}
                {!tareas.length ? <div>No tienes tareas pendientes.</div> : null}
              </div>
          )}
        </section>
        <div className="actions">
          <Link to="/dashboard" className="btn">Regresar</Link>
        </div>
        <Footer />
      </div>
  )
}
