import {
    ADD_TRANSACTION_ITEM,
    SET_TRANSACTION_ITEMS,
    UPDATE_TRANSACTION_ITEM
} from "./TransactionActions";

export default function transactionstore(
    state = { borrowList: [], lendList: [] },
    action
) {
    switch (action.type) {
        case SET_TRANSACTION_ITEMS: {
            return { borrowList: action.borrow, lendList: action.lend };
        }
        case UPDATE_TRANSACTION_ITEM: {
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
