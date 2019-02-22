import { store } from "../../Store/reduxInit";

import { getAllBorrowItemsBackend, borrowItemBackend } from "./borrowBackendService";
import { getSetBorrowListAction } from "../../Store/BorrowStore/BorrowActions";


export async function getAllBorrowItems(){
    let data = await getAllBorrowItemsBackend(store.getState().user.token);

    let list = data.data.data;

    let action = getSetBorrowListAction(list);
    store.dispatch(action);
}

export async function borrowItem(data){
    await borrowItemBackend(data,store.getState().user.token);
}