import axios from "axios";
import {LOGINURL} from '../urlConstants'

const getToken = async (username, password, url=LOGINURL) => {

    let response = await axios.post(
        url,
        {
            username: username,
            password: password
        });

    return response.headers.authorization;

};

export default getToken