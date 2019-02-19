import axios from "axios";

import {store} from "../../Store/reduxInit";
import {getLoginUserAction} from "../../Store/UserStore/UserActions";

import getToken from "./getToken";
import { SIGN_UP, LOGINURL } from "../urlConstants";

export async function registerUser(
    nameInner,
    emailInner,
    passwordInner,
    url = SIGN_UP
) {
    await axios.post(url, {
        username: nameInner,
        email: emailInner,
        password: passwordInner,
        bankAccount: emailInner
    });
}

export async function loginUser(
    nameInner,
    passwordInner,
) {
    let token = await getToken(
        nameInner,
        passwordInner,
        LOGINURL
    );

    let action = getLoginUserAction({ token: token });
    store.dispatch(action);
}
