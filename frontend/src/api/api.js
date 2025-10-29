import axios from 'axios'

const api = axios.create({})
api.interceptors.request.use((config)=>{
  const basic = localStorage.getItem('fa_basic')
  if (basic) config.headers['Authorization'] = `Basic ${basic}`
  return config
})
export default api
