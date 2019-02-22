import axios from "axios";

import {
    OVERDUE_NOTIFICATION
} from "../urlConstants";

export async function getAllOverdueTransactions(token, url = OVERDUE_NOTIFICATION) {
    let data = await axios.get(url, {
        headers: {
            Authorization: token
        }
    });
    return data;
}