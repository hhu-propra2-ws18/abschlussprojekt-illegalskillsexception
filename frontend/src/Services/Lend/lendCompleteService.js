import { getAddLendItemAction, getSetLendItemListAction, getUpdateLendItemAction } from "../../Store/LendStore/LendActions";
import { store } from "../../Store/reduxInit";
import { createLendBackend, getAllLendItemsBackend, updateLendBackend } from "./lendBackendService";

export async function createLendItem(item) {
    await createLendBackend(item, store.getState().user.token);

    let action = getAddLendItemAction(item);
    store.dispatch(action);
}

export async function getAllLendItems(){
    let data = await getAllLendItemsBackend(store.getState().user.token);

    let list = data.data.data;
    console.log("Lend onload list",list);

    let action = getSetLendItemListAction(list)
    store.dispatch(action);
}


export async function updateLendItem(item){
    await updateLendBackend(item,store.getState().user.token);

    let updateLendItemAction = getUpdateLendItemAction(item);
    store.dispatch(updateLendItemAction);
}