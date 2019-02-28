import { store } from "../../Store/reduxInit";

import { getAllBorrowItemsBackend, borrowItemBackend, getArticleAvailabilityListBackend } from "./borrowBackendService";
import { getSetBorrowListAction } from "../../Store/BorrowStore/BorrowActions";


export async function getAllBorrowItems(){
    let data = await getAllBorrowItemsBackend(store.getState().user.token);

    let list = data.data.data;

    let action = getSetBorrowListAction(list);
    store.dispatch(action);
}

export async function borrowItem(data){
    return await borrowItemBackend(data, store.getState().user.token);
}

export async function getArticleAvailabilityList(id){
    return await getArticleAvailabilityListBackend(id,store.getState().user.token);
}