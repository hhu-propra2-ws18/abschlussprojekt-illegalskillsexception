import { ADD_LEND_ITEM, SET_LEND_ITEMS } from "./LendActions";

export default function lendstore(state = [], action) {
    switch (action.type) {
        case ADD_LEND_ITEM: {
            return [...state,action.data];
        }
        case SET_LEND_ITEMS:{
            return action["list"];
        }
        default: {
            return [];
        }
    }
}
