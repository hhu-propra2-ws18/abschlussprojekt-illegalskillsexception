import {store} from "../../Store/reduxInit";
import {getLoginUserAction, getLogOutUserAction} from "../../Store/UserStore/UserActions";

import {
    addAmountBackend,
    getUserDetailsBackend,
    loginUserBackend,
    registerUserBackend
} from "./authentificationBackendService";
import {OVERDUE_NOTIFICATION} from "../urlConstants";
import axios from "axios";

export async function registerAndLoginUser(
    nameInner,
    emailInner,
    passwordInner
) {
    await registerUserBackend(nameInner, emailInner, passwordInner);

    await loginUser(nameInner, passwordInner);
}

export async function loginUser(nameInner, passwordInner) {
    let token = await loginUserBackend(nameInner, passwordInner);

    let action = getLoginUserAction({ token: token });
    store.dispatch(action);
}

export async function getUserDetails() {
    return (await getUserDetailsBackend(store.getState().user.token)).data;
}

export async function addMoneyBalanceToUserAccount(){
    return await addAmountBackend(store.getState().user.token);
}

export async function logOutUser(){
    let action = getLogOutUserAction();
    store.dispatch(action);
}

export async function getAllOverdueTransactions(token, url = OVERDUE_NOTIFICATION) {
    return await axios.get(url, {
        headers: {
            Authorization: token
        }
    });
}