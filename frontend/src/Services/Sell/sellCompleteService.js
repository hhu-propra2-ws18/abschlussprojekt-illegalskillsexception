import { store } from "../../Store/reduxInit";
import { getAllSellItemsOfUserBackend, createSellItemBackend, udpateSellItemBackend } from "./sellBackendService";
import { getSetSellListAction, getAddSellItemAction, getUpdateSellItemAction } from "../../Store/SellStore/SellActions";

export async function getAllSellItemsOfUser() {
    let data = await getAllSellItemsOfUserBackend(store.getState().user.token);

    let list = data.data.data;

    let action = getSetSellListAction(list);
    store.dispatch(action);
}

export async function createSellItem(item) {
    let serverId = await createSellItemBackend(item, store.getState().user.token);
    item.id = serverId.data.data.id;
    let action = getAddSellItemAction(item);
    store.dispatch(action);
}


export async function updateSellItem(item) {
    let serverId = await udpateSellItemBackend(item, store.getState().user.token);
    item.id = serverId.data.data.id;
    let action = getUpdateSellItemAction(item);
    store.dispatch(action);
}

