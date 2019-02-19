import axios from "axios";
import { SIGN_UP } from "../urlConstants";

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
