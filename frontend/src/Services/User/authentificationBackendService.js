import axios from "axios";
import { SIGN_UP, LOGINURL, USER_PROFILE } from "../urlConstants";

export const loginUserBackend = async (username, password, url = LOGINURL) => {
    let response = await axios.post(url, {
        username: username,
        password: password
    });

    return response.headers.authorization;
};

export async function registerUserBackend(
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

export async function getUserDetailsBackend(token, url = USER_PROFILE) {
    await axios.get(url, { headers: { Authorization: token } });
}
