import { store } from "../../Store/reduxInit";
import { getLoginUserAction } from "../../Store/UserStore/UserActions";

import {
    loginUserBackend,
    registerUserBackend,
    getUserDetailsBackend
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

export async function getUserDetails() {
    return await getUserDetailsBackend(store.getState().user.token);
}
