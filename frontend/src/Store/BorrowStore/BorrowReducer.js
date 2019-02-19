import { SET_BORROW_ITEMS } from "./BorrowActions";

export default function borrowstore(state = [], action) {
    switch (action.type) {
        case SET_BORROW_ITEMS:{
            return action["list"];
        }
        default: {
            return state;
        }
    }
}
