# Future Academy — Fullstack (Spring Boot + React/Vite)

- POMs corregidos (raíz, backend, frontend)
- Sin Flyway
- Credenciales Neon en `backend/src/main/resources/application.properties`
- SPA con modo oscuro + navbar hamburguesa + landing con Misión/Visión + Conócenos + formulario de contacto centrado
- Login, Dashboard (clases), Tareas (entregar con link)

## Correr
```bash
# En la raíz
mvn -T 1C clean install
mvn -f backend/pom.xml spring-boot:run
# Dev frontend con hot reload
cd frontend && npm ci && npm run dev
```
