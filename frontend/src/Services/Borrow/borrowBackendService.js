import Axios from "axios";
import {
    BORROW_GETALL,
    BORROW_INQUIRY,
    BORROW_GET_AVAILABILITYLIST
} from "../urlConstants";

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

export async function getArticleAvailabilityListBackend(
    id,
    token,
    url = BORROW_GET_AVAILABILITYLIST
) {
    return await Axios.post(
        url,
        { articleId: id },
        {
            headers: {
                Authorization: token
            }
        }
    );
}
