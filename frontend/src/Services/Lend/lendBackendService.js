import Axios from "axios";
import { LEND_GETALL, LEND_CREATE } from "../urlConstants";

export async function getAllLend(token, url = LEND_GETALL) {
    let data = await Axios.get(url, {
        header: {
            Authorization: token
        }
    });
    return data;
}

export async function createLend(data, token, url = LEND_CREATE) {
    let data = await Axios.post(url, data, {
        header: {
            Authorization: token
        }
    });
    return data;
}
