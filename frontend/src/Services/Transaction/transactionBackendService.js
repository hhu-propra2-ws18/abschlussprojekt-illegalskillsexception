import Axios from "axios";
import { TRANSACTION_GETALL, TRANSACTION_PROBLEM } from "../urlConstants";

export async function getAllTransactionsBackend(
    token,
    url = TRANSACTION_GETALL
) {
    let data = await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
    return data.data.data;
}

export async function postTransactionProblem(
    id,
    token,
    url = TRANSACTION_PROBLEM
) {
    let data = await Axios.post(
        url,
        { id: id },
        {
            headers: {
                Authorization: token
            }
        }
    );

    return data;
}
