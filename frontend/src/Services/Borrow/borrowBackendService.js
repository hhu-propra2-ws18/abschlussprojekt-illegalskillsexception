import Axios from "axios";
import { BORROW_GETALL, BORROW_INQUIRY } from "../urlConstants";

export async function getAllBorrowItemsBackend(token, url = BORROW_GETALL) {
    let data = await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
    return data;
}

export async function borrowItemBackend(data, token, url = BORROW_INQUIRY) {
    let returnData = await Axios.post(url, data, {
        headers: {
            Authorization: token
        },
        params: {
            id: data.id,
            startDate: data.id,
            endData: data.id
        }
    });
    return returnData;
}
