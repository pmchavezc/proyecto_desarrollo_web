import axios from 'axios'
/** Cliente Axios con Basic Auth desde localStorage. */
const api = axios.create({})
api.interceptors.request.use((config)=>{
  const basic = localStorage.getItem('fa_basic')
  if (basic) config.headers['Authorization'] = `Basic ${basic}`
  return config
})
export default api
