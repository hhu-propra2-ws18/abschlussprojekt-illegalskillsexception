import axios from "axios";

export const SET_BORROW_ITEMS = "SET_BORROW_ITEMS";

export function getSetBorrowListAction(list) {
    return {
        type: SET_BORROW_ITEMS,
        list: list
    };
}
