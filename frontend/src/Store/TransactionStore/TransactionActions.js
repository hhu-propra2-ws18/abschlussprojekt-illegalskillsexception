export const ADD_TRANSACTION_ITEM = "ADD_TRANSACTION_ITEM";
export const SET_TRANSACTION_ITEMS = "SET_TRANSACTION_ITEMS";
export const UPDATE_TRANSACTION_ITEM = "UPDATE_TRANSACTION_ITEMS";
export const REMOVE_TRANSACTION_ITEM = "REMOVE_TRANSACTION_ITEM";

export function getAddTransactionItemAction(item) {
    return {
        type: ADD_TRANSACTION_ITEM,
        data: item
    };
}

export function getRemoveTransactionItemAction(item) {
    return {
        type: REMOVE_TRANSACTION_ITEM,
        data: item
    };
}

export function getSetTransactionItemListAction(listBorrow, listLend) {
    return {
        type: SET_TRANSACTION_ITEMS,
        borrow: listBorrow,
        lend: listLend
    };
}

export function getUpdateTransactionItemAction(item) {
    return {
        type: UPDATE_TRANSACTION_ITEM,
        data: item
    };
}
