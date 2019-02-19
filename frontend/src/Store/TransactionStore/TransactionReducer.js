import {
    ADD_TRANSACTION_ITEM,
    SET_TRANSACTION_ITEMS,
    UPDATE_TRANSACTION_ITEM
} from "./TransactionActions";

export default function transactionstore(state = [], action) {
    switch (action.type) {
        case ADD_TRANSACTION_ITEM: {
            return [...state, action.data];
        }
        case SET_TRANSACTION_ITEMS: {
            return action["list"];
        }
        case UPDATE_TRANSACTION_ITEM: {
            let copy = Object.assign([], state);
            copy.forEach(item => {
                if (item.id === action.data.id) {
                    Object.assign(item, action.data);
                }
            });
            return copy;
        }
        default: {
            return [];
        }
    }
}
