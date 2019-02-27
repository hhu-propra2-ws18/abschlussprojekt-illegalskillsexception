import Axios from "axios";
import {  BUY_GETALL, BUY_BUYITEM } from "../urlConstants";

export async function getAllBuyItemsBackend(token, url = BUY_GETALL) {
    return await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
}

export async function buyItemBackend(id, token, url=BUY_BUYITEM) {
    return await Axios.post(
        url,
        { buyArticleId: id },
        {
            headers: {
                Authorization: token
            }
        }
    );
}
