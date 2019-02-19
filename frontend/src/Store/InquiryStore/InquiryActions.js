export const ADD_INQUIRY_ITEM = "ADD_INQUIRY_ITEM";
export const SET_INQUIRY_ITEMS = "SET_INQUIRY_ITEMS";
export const UPDATE_INQUIRY_ITEM = "UPDATE_INQUIRY_ITEMS";

export function getAddInquiryItemAction(item) {
    return {
        type: ADD_INQUIRY_ITEM,
        data: item
    };
}

export function getSetInquiryItemListAction(list) {
    return {
        type: SET_INQUIRY_ITEMS,
        list: list
    };
}

export function getUpdateInquirytemAction(item) {
    return {
        type: UPDATE_INQUIRY_ITEM,
        data: item
    };
}
