import { store } from "../../Store/reduxInit";
import {
    getLoginUserAction,
    getLogOutUserAction
} from "../../Store/UserStore/UserActions";

import {
    addAmountBackend,
    getUserDetailsBackend,
    loginUserBackend,
    registerUserBackend
} from "./authentificationBackendService";
import { OVERDUE_NOTIFICATION } from "../urlConstants";
import axios from "axios";

export async function registerAndLoginUser(
    nameInner,
    emailInner,
    passwordInner
) {
    try {
        let result = await registerUserBackend(
            nameInner,
            emailInner,
            passwordInner
        );
        console.log('result', result)
        if (result.data.error) {
            return result;
        }
        let resultLogin = await loginUser(nameInner, passwordInner);
        if (resultLogin.error) {
            return resultLogin;
        } else {
            return { error: false };    
        }
    } catch (exc) {
        return {
            error: true,
            errorMessage: "An error occurred during processes"
        };
    }
}

export async function loginUser(nameInner, passwordInner) {
    try {
        let token = await loginUserBackend(nameInner, passwordInner);

        let action = getLoginUserAction({ token: token });
        store.dispatch(action);
        return {};
    } catch (exc) {
        return { error: true, errorMessage: "Wrong credentials" };
    }
}

export async function getUserDetails() {
    return (await getUserDetailsBackend(store.getState().user.token)).data;
}

export async function addMoneyBalanceToUserAccount() {
    return await addAmountBackend(store.getState().user.token);
}

export async function logOutUser() {
    let action = getLogOutUserAction();
    store.dispatch(action);
}

export async function getAllOverdueTransactions(
    token,
    url = OVERDUE_NOTIFICATION
) {
    return await axios.get(url, {
        headers: {
            Authorization: token
        }
    });
}
