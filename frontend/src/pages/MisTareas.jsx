import React, { useEffect, useState } from 'react'
import HeaderAuth from '../components/HeaderAuth'
import Footer from '../components/Footer'
import api from '../api/api'
import {Link} from "react-router-dom";

export default function MisTareas(){
  const [items, setItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const load = async ()=>{
    try{
      setLoading(true); setError('')
      const { data } = await api.get('/api/tareas/mias')
      setItems(data)
    }catch(err){
      setError(err?.response?.data?.message || 'Error al cargar tus tareas')
    }finally{ setLoading(false) }
  }
  useEffect(()=>{ load() },[])
  return (
    <div className="page">
      <HeaderAuth />
      <section className="tareas">
        <h1>Mis tareas y notas</h1>
        {error ? <div className="err">{error}</div> : null}
        {loading ? <div>Cargando...</div> : (
          <div className="grid">
            {items.map(it => (
              <article key={it.entregaId} className="tarea card" style={{display:'grid',gridTemplateColumns:'1fr 160px',gap:'12px',alignItems:'center'}}>
                <div>
                  <h3 style={{margin:'0 0 4px'}}>{it.tituloTarea}</h3>
                  <div style={{opacity:.8}}>Clase: {it.clase}</div>
                  <div style={{opacity:.8}}>Entregada: {it.entregadaEn?.replace('T',' ')}</div>
                  <a href={it.enlace} target="_blank" rel="noreferrer">Ver enlace</a>
                </div>
                <div className="card" style={{textAlign:'center', padding:'12px', borderRadius:'12px'}}>
                  <div style={{fontSize:12, opacity:.7}}>Nota</div>
                  <div style={{fontSize:28, fontWeight:700}}>{it.nota ?? '—'}</div>
                  {it.observaciones ? <div style={{fontSize:12, opacity:.7, marginTop:4}}>{it.observaciones}</div> : null}
                </div>
              </article>
            ))}
            {!items.length ? <div>No tienes entregas aún.</div> : null}
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
