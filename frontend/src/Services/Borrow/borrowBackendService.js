import Axios from "axios";
import { BORROW_GETALL, BORROW_INQUIRY } from "../urlConstants";

export async function getAllBorrowItemsBackend(token, url = BORROW_GETALL) {
    return await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
}

export async function borrowItemBackend(data, token, url = BORROW_INQUIRY) {
    return await Axios.post(url, data, {
        headers: {
            Authorization: token
        }
    });
}
