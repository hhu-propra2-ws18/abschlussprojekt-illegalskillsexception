export const SET_BUY_ITEMS = "SET_BUY_ITEMS";
export const REMOVE_BUY_ITEM = "REMOVE_BUY_ITEM";

export function getSetBuyListAction(list) {
    return {
        type: SET_BUY_ITEMS,
        list: list
    };
}

export function getRemoveBuyItemAction(id) {
    return {
        type: REMOVE_BUY_ITEM,
        id: id
    };
}
