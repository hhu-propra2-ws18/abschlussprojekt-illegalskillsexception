import {
    SET_SELL_ACTIONS,
    ADD_SELL_ITEM,
    UPDATE_SELL_ITEM
} from "./SellActions";

export default function sellstore(state = [], action) {
    console.log(action,state);
    switch (action.type) {
        case SET_SELL_ACTIONS: {
            return action["list"];
        }
        case ADD_SELL_ITEM: {
            let copy = Object.assign([], state);
            copy.push(action.data);
            return copy;
        }
        case UPDATE_SELL_ITEM: {
            let copy = Object.assign([], state);
            copy.forEach(item => {
                if (item.id === action.data.id) {
                    Object.assign(item, action.data);
                }
            });
            return copy;
        }

        default: {
            return state;
        }
    }
}
