export const ADD_INQUIRY_ITEM = "ADD_INQUIRY_ITEM";
export const SET_INQUIRY_ITEMS = "SET_INQUIRY_ITEMS";
export const REMOVE_INQUIRY_ITEM = "REMOVE_INQUIRY_ITEM";

export function getAddInquiryItemAction(item) {
    return {
        type: ADD_INQUIRY_ITEM,
        data: item
    };
}

export function getSetInquiryItemListAction(borrowList, lendList) {
    return {
        type: SET_INQUIRY_ITEMS,
        borrowList: borrowList ? borrowList : [],
        lendList: lendList ? lendList : []
    };
}

export function getRemoveInquiryItemAction(id) {
    return {
        type: REMOVE_INQUIRY_ITEM,
        inquiryId: id
    };
}
