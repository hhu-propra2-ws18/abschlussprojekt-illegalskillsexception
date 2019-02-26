import {checkIfAdmin, getAllConflictsBackend} from "./conflictBackendService";
import {getSetConflictItemListAction} from "../../Store/ConflictStore/ConflictActions";
import {store} from "../../Store/reduxInit";
import {setAdminStore} from "../../Store/UserStore/UserActions";

export async function getAllTransaction() {
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