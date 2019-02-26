import {getAllConflictsBackend} from "./conflictBackendService";
import {getSetConflictItemListAction} from "../../Store/ConflictStore/ConflictActions";
import {store} from "../../Store/reduxInit";

export async function getAllTransaction() {
    const token = store.getState().user.token;
    let data = await getAllConflictsBackend(token);
    console.log("Conflicts:", data); //Todo
    console.log("Conflicts:", data.data.data);
    let action = getSetConflictItemListAction(data.data.data);
    store.dispatch(action);
}