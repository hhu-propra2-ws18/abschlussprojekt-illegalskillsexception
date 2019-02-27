import {checkIfAdmin, getAllConflictsBackend, solveConflictBackend} from "./conflictBackendService";
import {getSetConflictItemListAction} from "../../Store/ConflictStore/ConflictActions";
import {store} from "../../Store/reduxInit";
import {setAdminStore} from "../../Store/UserStore/UserActions";
import {CONFLICT_PUNISH, CONFLICT_RESOLVE} from "../urlConstants";

export async function getAllConflicts() {
    const token = store.getState().user.token;
    let data = await getAllConflictsBackend(token);
    let action = getSetConflictItemListAction(data.data.data);
    store.dispatch(action);
}

export const setAdmin = async () => {
    if ( await checkIfAdmin()){
        const action = setAdminStore();
        store.dispatch(action);
    }
};

export const punishConflict = async (id) => {
    return solveConflictBackend(id, CONFLICT_PUNISH);
};

export const resolveConflict = async (id) => {
    return solveConflictBackend(id, CONFLICT_RESOLVE);
};