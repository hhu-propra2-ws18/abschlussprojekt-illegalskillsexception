import { getAddLendItemAction, getSetLendItemListAction } from "../../Store/LendStore/LendActions";
import { store } from "../../Store/reduxInit";
import { createLend, getAllLendItemsBackend } from "./lendBackendService";

export async function createLendItem(item) {
    await createLend(item, store.getState().user.token);

    let action = getAddLendItemAction(item);
    store.dispatch(action);
}

export async function getAllLendItems(){
    let data = await getAllLendItemsBackend(store.getState().user.token);

    let list = data.data.data;
    console.log(list);

    let action = getSetLendItemListAction(list)
    store.dispatch(action);
}
