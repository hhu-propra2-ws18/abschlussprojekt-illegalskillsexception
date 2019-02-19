import { ADD_INQUIRY_ITEM, SET_INQUIRY_ITEMS, UPDATE_INQUIRY_ITEM } from "./InquiryActions";

export default function inquirystore(state = [], action) {
    switch (action.type) {
        case ADD_INQUIRY_ITEM: {
            return [...state, action.data];
        }
        case SET_INQUIRY_ITEMS: {
            return action["list"];
        }
        case UPDATE_INQUIRY_ITEM: {
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
