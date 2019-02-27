export const SET_SELL_ACTIONS = "SET_SELL_ACTIONS";
export const ADD_SELL_ITEM = "ADD_SELL_ITEM";
export const UPDATE_SELL_ITEM = "UPDATE_SELL_ITEM";

export function getSetSellListAction(list) {
    return {
        type: SET_SELL_ACTIONS,
        list: list
    };
}

export function getAddSellItemAction(data) {
    return {
        type: ADD_SELL_ITEM,
        data
    };
}
export function getUpdateSellItemAction(data) {
    return {
        type: UPDATE_SELL_ITEM,
        data
    };
}
