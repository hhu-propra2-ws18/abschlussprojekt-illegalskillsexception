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

export async function postTransactionFinishedBackend(id, token) {
    let data = await Axios.post(
        url,
        {
            transactionId: id,
            isFaulty: false,
            itemReturn: true
        },
        {
            headers: {
                Authorization: token
            }
        }
    );

    return data;
}

export async function postTransactionProblemBackend(
    id,
    token,
    url = TRANSACTION_PROBLEM
) {
    let data = await Axios.post(
        url,
        {
            transactionId: id,
            isFaulty: true,
            itemReturn: true
        },
        {
            headers: {
                Authorization: token
            }
        }
    );

    return data;
}
