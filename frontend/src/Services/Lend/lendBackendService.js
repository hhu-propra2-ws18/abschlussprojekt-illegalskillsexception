import Axios from "axios";
import { LEND_GETALL, LEND_CREATE } from "../urlConstants";

export async function getAllLendItemsBackend(token, url = LEND_GETALL) {
    let data = await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
    return data;
}

export async function createLend(data, token, url = LEND_CREATE) {
    let returnData = await Axios.post(url, data, {
        headers: {
            Authorization: token
        }
    });
    return returnData;
}
