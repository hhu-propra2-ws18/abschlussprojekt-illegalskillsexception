import { ADD_LEND_ITEM } from "./LendActions";

export default function lendstore(state = [], action) {
    switch (action.type) {
        case ADD_LEND_ITEM: {
            state.push(action.data);
            return state;
        }
        default: {
            return [];
        }
    }
}
