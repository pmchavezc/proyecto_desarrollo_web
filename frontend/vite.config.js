import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
export default defineConfig({
  plugins:[react()],
  //server:{ port:5173, proxy:{ '/api':'http://localhost:8080' } },
  server:{ port:8080, proxy:{ '/api':'http://93.127.139.74:8080/' } },
  build:{ outDir:'dist' }
})