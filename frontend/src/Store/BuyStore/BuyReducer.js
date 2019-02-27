import { SET_BUY_ITEMS, REMOVE_BUY_ITEM } from "./BuyActions";

export default function buystore(state = [], action) {
    switch (action.type) {
        case SET_BUY_ITEMS: {
            return action["list"];
        }
        case REMOVE_BUY_ITEM: {
            let copy = Object.assign([], state);
            copy = copy.filter(item => item.id !== action.id);
            return copy;
        }

        default: {
            return state;
        }
    }
}
