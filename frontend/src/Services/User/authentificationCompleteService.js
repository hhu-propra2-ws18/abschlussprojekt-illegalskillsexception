import axios from "axios";

import { store } from "../../Store/reduxInit";
import { getLoginUserAction } from "../../Store/UserStore/UserActions";

import {
    loginUserBackend,
    registerUserBackend
} from "./authentificationBackendService";

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
