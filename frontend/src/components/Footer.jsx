import React from 'react'
export default function Footer(){
  return (
    <footer className="footer">
      <div className="footer__social">
        <a href="https://facebook.com" target="_blank" rel="noreferrer">Facebook</a>
        <a href="https://instagram.com" target="_blank" rel="noreferrer">Instagram</a>
        <a href="https://x.com" target="_blank" rel="noreferrer">X</a>
      </div>
      <div className="footer__copy">© {new Date().getFullYear()} Future Academy — Todos los derechos reservados.</div>
    </footer>
  )
}
