import Axios from "axios";
import {  SELL_GETALL, SELL_CREATE_ITEM, SELL_UPDATE_ITEM } from "../urlConstants";

export async function getAllSellItemsOfUserBackend(token, url = SELL_GETALL) {
    return await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
}

export async function createSellItemBackend(data, token, url = SELL_CREATE_ITEM) {
    return await Axios.post(url, data, {
        headers: {
            Authorization: token
        }
    });
}

export async function udpateSellItemBackend(data, token, url = SELL_UPDATE_ITEM) {
    return await Axios.post(url, data, {
        headers: {
            Authorization: token
        }
    });
}
