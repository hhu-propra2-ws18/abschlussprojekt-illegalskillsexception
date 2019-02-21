import Axios from "axios";
import { LEND_GETALL, LEND_CREATE, LEND_UPDATE } from "../urlConstants";

export async function getAllLendItemsBackend(token, url = LEND_GETALL) {
    let data = await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
    return data;
}

export async function createLendBackend(data, token, url = LEND_CREATE) {
    let returnData = await Axios.post(url, data, {
        headers: {
            Authorization: token
        }
    });
    return returnData;
}

export async function updateLendBackend(data, token, url = LEND_UPDATE) {
    let returnData = await Axios.post(url, data, {
        headers: {
            Authorization: token,
        }
    });
    console.log(returnData);
    return returnData;
}
