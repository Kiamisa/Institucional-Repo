import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080',
    headers:{
        'Content-Type': 'aplication/json'
    }
});

export default apiClient;