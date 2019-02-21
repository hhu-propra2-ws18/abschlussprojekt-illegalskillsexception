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
            let copyBorrow = Object.assign([], state.borrowList);
            copyBorrow.splice(
                copyBorrow.findIndex(function(i) {
                    return i.inquiryId === action.inquiryId;
                }),
                1
            );

            let copyLend = Object.assign([], state.lendList);
            copyLend.splice(
                copyLend.findIndex(function(i) {
                    return i.inquiryId === action.inquiryId;
                }),
                1
            );

            return { borrowList: copyBorrow, lendList: copyLend };
        }
        default: {
            return state;
        }
    }
}
