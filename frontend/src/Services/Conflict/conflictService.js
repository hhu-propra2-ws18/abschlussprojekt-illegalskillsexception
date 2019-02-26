import Axios from "axios";
import {CONFLICT_GETALL} from "../urlConstants";
import { store } from "../../Store/reduxInit";

export const getAllConflicts = async (token=store.getState().user.token, url=CONFLICT_GETALL) => {
    const conflicts = await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
    console.log("conflicts: ", conflicts);
};