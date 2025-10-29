import React, { useState } from 'react'
import Header from '../components/Header'
import Footer from '../components/Footer'
import '../styles/home.css'
import api from '../api/api'

export default function Home() {
  const [form, setForm] = useState({ nombre:'', email:'', telefono:'', mensaje:'' })
  const [enviado, setEnviado] = useState(false)
  const [error, setError] = useState('')
  const onChange = (e) => setForm({ ...form, [e.target.name]: e.target.value })
  const onSubmit = async (e) => {
    e.preventDefault(); setError('')
    try { await api.post('/api/contactos', form); setEnviado(true); setForm({ nombre:'', email:'', telefono:'', mensaje:'' }) }
    catch (err) { setError(err?.response?.data?.message || 'Ocurrió un error') }
  }

  return (
    <div className="page">
      <Header />

      <section className="hero">
        <h1>Future Academy</h1>
        <p>Formando estudiantes con propósito.</p>
      </section>
      <section id="misionvision" className="mv">
        {/* Tarjetas de Misión y Visión con imagen */}
        <div className="mv__box card">
          <img src="/img/mision.jpg" alt="Misión" />
          <h2>Misión</h2>
          <p><h3>En Future Academy educamos de forma integral y con excelencia académica,
            potenciando los talentos de cada estudiante mediante metodologías activas,
            acompañamiento socioemocional y uso responsable de la tecnología. Promovemos valores como el
            respeto, la responsabilidad y la honestidad; fomentamos el pensamiento crítico,
            el trabajo colaborativo y la comunicación efectiva; y vinculamos el aprendizaje con proyectos
            de impacto comunitario. Nuestro equipo docente se forma de manera continua y evalúa para
            aprender (no solo para calificar), garantizando trayectorias educativas flexibles y pertinentes.
            En alianza con las familias, aseguramos ambientes protectores, equitativos e inclusivos,
            donde cada estudiante encuentra propósito, desarrolla su vocación y se prepara para la vida,
            el estudio superior y el mundo del trabajo.
             </h3>
            </p>
        </div>
        <div className="mv__box card">
          <img src="/img/vision.jpg" alt="Visión" />
          <h2>Visión</h2>
          <p><h3>Ser una comunidad educativa referente en la región que inspira a cada estudiante a convertirse en una persona íntegra,
            curiosa y solidaria, capaz de pensar críticamente, comunicar con empatía y resolver problemas reales con creatividad.
            Soñamos con una escuela que aprende continuamente, que integra ciencia, artes,
            deporte y ciudadanía digital, y que trabaja codo a codo con las familias y la comunidad para construir entornos seguros,
            inclusivos y sostenibles. Aspiramos a egresar jóvenes con identidad, raíces y proyección global, que valoren su cultura,
            respeten la diversidad y lideren con servicio para transformar positivamente su entorno.
          </h3></p>
          </div>
      </section>
    

      <section className="conocenos">
        <h2>Conócenos</h2>
        <div className="cards">
          <article className="mv__box card">
            <img src="/img/conocenos1.jpg" alt="Conócenos 1" />
            <h3>Metodologías activas</h3>
            <p><h3>Aprendizaje basado en proyectos.</h3></p>
          </article>
          <article className="mv__box card">
            <img src="/img/conocenos2.jpg" alt="Conócenos 2" />
            <h3>Docentes capacitados</h3>
            <p><h3>Equipo con experiencia y compromiso.</h3></p>
          </article>
          <article className="mv__box card">
            <img src="/img/conocenos3.jpg" alt="Conócenos 3" />
            <h3>Ambiente seguro</h3>
            <p><h3>Espacios saludables y acompañamiento.</h3></p>
          </article>
        </div>
      </section>

      <section id="contacto" className="contacto">
        <h2>Contáctanos</h2>
        <div>
        <p><h3>info@futureacademy.edu.gt</h3> </p>
          <p><h3>Tel. +502 5555-1234 </h3></p>
        </div>
        <div className="contact-card">
          {enviado ? <div className="ok">¡Gracias! Te responderemos pronto.</div> : null}
          {error ? <div className="err">{error}</div> : null}
          <form onSubmit={onSubmit}>
            <div className="row"><label><h2>Nombre</h2></label><input name="nombre" value={form.nombre} onChange={onChange} required /></div>
            <div className="row"><label><h2>Email</h2></label><input name="email" type="email" value={form.email} onChange={onChange} required /></div>
            <div className="row"><label><h2>Teléfono</h2></label><input name="telefono" value={form.telefono} onChange={onChange} required /></div>
            <div className="row"><label><h2>Mensaje</h2></label><textarea name="mensaje" value={form.mensaje} onChange={onChange} required /></div>
            <button type="submit" className="btn">Enviar</button>
          </form>
        </div>
      </section>
      <Footer />
    </div>
  )
}
