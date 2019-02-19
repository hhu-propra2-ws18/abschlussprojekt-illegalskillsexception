export const ADD_LEND_ITEM = "ADD_LEND_ITEM";
export const SET_LEND_ITEMS = "SET_LEND_ITEMS";
export const UPDATE_LEND_ITEM = "UPDATE_LEND_ITEMS";

export function getAddLendItemAction(item) {
    return {
        type: ADD_LEND_ITEM,
        data: item
    };
}

export function getSetLendItemListAction(list) {
    return {
        type: SET_LEND_ITEMS,
        list: list
    };
}

export function getUpdateLendItemAction(item) {
    return {
        type: UPDATE_LEND_ITEM,
        data: item
    };
}
