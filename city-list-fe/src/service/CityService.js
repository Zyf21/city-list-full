import axios from 'axios'

const API_BASE_URL = "http://localhost:8080/api/cities";

class CityService {
    getSectors() {
        return axios.get(API_BASE_URL);
    }
}
export default new CityService();
