export const SET_CONFLICT_ITEMS = "SET_CONFLICT_ITEMS";

export function getSetConflictItemListAction(listConflict) {
    return {
        type: SET_CONFLICT_ITEMS,
        conflict: listConflict,
    };
}