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

export const checkIfAdmin = async (token, url = CONFLICT_GETALL) => {

    const data = getAllConflictsBackend(token);
    console.log("data", data);
    const err = data.data.error;

    return !err;
};