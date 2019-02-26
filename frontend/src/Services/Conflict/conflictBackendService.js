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

export const punishConflict = async (id) => {
    const url = "punish";
    return solveConflictBackend(id)
};

export const resolveConflict = async (id) => {
    const url = "resolve";
    return solveConflictBackend(id)
};

const solveConflictBackend = async (id,url,token=store.getState().user.token) => {
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