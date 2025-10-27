import React, { useEffect, useState } from 'react'
import HeaderAuth from '../components/HeaderAuth'
import Footer from '../components/Footer'
import './dashboard.css'
import api from '../api/api'
import { Link } from 'react-router-dom'

export default function Dashboard() {
  const [misClases, setMisClases] = useState([])
  const [noInscritas, setNoInscritas] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [toast] = useState('')

  const load = async () => {
    setLoading(true); setError('')
    try {
      const a = await api.get('/api/mis-clases')
      const b = await api.get('/api/clases/no-inscritas')
      setMisClases(a.data); setNoInscritas(b.data)
    } catch (e) { setError('Error cargando datos') }
    finally { setLoading(false) }
  }
  useEffect(()=>{ load() }, [])

  const inscribir = async (id) => {
    try { await api.post(`/api/inscripciones/${id}`); await load() }
    catch(e){ alert('No se pudo inscribir') }
  }

  return (
    <div className="page">
      <HeaderAuth />
      <section className="dash">
        {toast ? <div className="ok">{toast}</div> : null}
        <div className="dash__head">
          <h1>Mis clases</h1>
        </div>
        {loading ? <div>Cargando...</div> : error ? <div className="err">{error}</div> : (
          <>
            <div className="grid">
              {misClases.map(c => (
                <article key={c.id} className="curso">
                  <h3>{c.nombre}</h3>
                  <p><strong>Maestro:</strong> {c.maestro?.nombres} {c.maestro?.apellidos}</p>
                </article>
              ))}
              {!misClases.length ? <div>No tienes clases inscritas.</div> : null}
            </div>
            <h2>Clases disponibles</h2>
            <div className="grid">
              {noInscritas.map(c => (
                <article key={c.id} className="curso">
                  <h3>{c.nombre}</h3>
                  <p><strong>Maestro:</strong> {c.maestro?.nombres} {c.maestro?.apellidos}</p>
                  <button className="btn" onClick={() => inscribir(c.id)}>Inscribirme</button>
                </article>
              ))}
              {!noInscritas.length ? <div>No hay clases disponibles.</div> : null}
            </div>
          </>
        )}
      </section>
      <Footer />
    </div>
  )
}
