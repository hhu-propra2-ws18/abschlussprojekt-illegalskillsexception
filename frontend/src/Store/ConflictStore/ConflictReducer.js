import {SET_CONFLICT_ITEMS} from "./ConflictActions";

export default function conflictstore(
    state = {conflictList: []},
    action) {

    switch (action.type) {
        case SET_CONFLICT_ITEMS: {
            return {conflictList: action.conflict};
        }
        default: {
            return state;
        }
    }
}