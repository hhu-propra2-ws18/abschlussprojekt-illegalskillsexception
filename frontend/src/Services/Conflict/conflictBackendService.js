import axios from "axios";
import {CONFLICT_GETALL} from "../urlConstants";
import {store} from "../../Store/reduxInit";

export const getAllConflictsBackend = async (token, url = CONFLICT_GETALL) => {
    return await axios.get(url, {
        headers: {
            Authorization: token
        }
    });
};

export const checkIfAdmin = async (token=store.getState().user.token) => {

    const data = await getAllConflictsBackend(token);
    const err = data.data.error;
    return !err;
};

export const solveConflictBackend = async (id,url,token=store.getState().user.token) => {
    return await axios.post(
        url, {
        transactionId: id,
        isFaulty: false
    },{
        headers: {
            Authorization: token
        }
    });
};