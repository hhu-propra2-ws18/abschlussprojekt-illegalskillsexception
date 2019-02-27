import { store } from "../../Store/reduxInit";
import { buyItemBackend, getAllBuyItemsBackend } from "./buyBackendService";
import {
    getSetBuyListAction,
    getRemoveBuyItemAction
} from "../../Store/BuyStore/BuyActions";

export async function getAllBuyItems() {
    let data = await getAllBuyItemsBackend(store.getState().user.token);

    let list = data.data.data;

    let action = getSetBuyListAction(list);
    store.dispatch(action);
}

export async function buyItem(id) {
    let result = await buyItemBackend(id, store.getState().user.token);

    if (result.data.error) {
        return result;
    }

    let action = getRemoveBuyItemAction(id);
    store.dispatch(action);

    return result;
}
