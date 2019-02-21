import { store } from "../../Store/reduxInit";

import { getAllBorrowItemsBackend, borrowItemBackend } from "./borrowBackendService";
import { getSetBorrowListAction } from "../../Store/BorrowStore/BorrowActions";

export async function inquiryBorrowItem(item) {
    //await createLend(item, store.getState().user.token);

    //let action = getAddLendItemAction(item);
    //store.dispatch(action);
}

export async function getAllBorrowItems(){
    let data = await getAllBorrowItemsBackend(store.getState().user.token);

    let list = data.data.data;
    console.log("Borrow onload list",list);

    let action = getSetBorrowListAction(list)
    store.dispatch(action);
}

export async function borrowItem(data){
    await borrowItemBackend(data,store.getState().user.token);
}