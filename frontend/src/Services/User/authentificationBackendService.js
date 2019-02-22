import axios from "axios";
import {
    SIGN_UP,
    LOGINURL,
    USER_PROFILE,
    CHARGE_AMOUNT
} from "../urlConstants";

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
    return await axios.get(url, { headers: { Authorization: token } });
}

export async function chargeAmountBackend(token, url = CHARGE_AMOUNT) {
    return await axios.post(
        url,
        { amount: 50 },
        { headers: { Authorization: token } }
    );
}
