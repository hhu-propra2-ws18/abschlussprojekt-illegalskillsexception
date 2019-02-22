import {
    ADD_INQUIRY_ITEM,
    SET_INQUIRY_ITEMS,
    REMOVE_INQUIRY_ITEM
} from "./InquiryActions";

export default function inquirystore(
    state = { borrowList: [], lendList: [] },
    action
) {
    switch (action.type) {
        case ADD_INQUIRY_ITEM: {
            return [...state, action.data];
        }
        case SET_INQUIRY_ITEMS: {
            return { borrowList: action.borrowList, lendList: action.lendList };
        }
        case REMOVE_INQUIRY_ITEM: {
            let copyBorrow = state.borrowList.filter(
                element => element.id !== action.inquiryId
            );

            let copyLend = state.lendList.filter(
                element => element.id !== action.inquiryId
            );

            return { borrowList: copyBorrow, lendList: copyLend };
        }
        default: {
            return state;
        }
    }
}
