import { ADD_LEND_ITEM, SET_LEND_ITEMS, UPDATE_LEND_ITEM } from "./LendActions";

export default function lendstore(state = [], action) {
    switch (action.type) {
        case ADD_LEND_ITEM: {
            return [...state, action.data];
        }
        case SET_LEND_ITEMS: {
            return action["list"];
        }
        case UPDATE_LEND_ITEM: {
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
