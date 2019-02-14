import { LOGIN_USER, LOGOUT_USER } from "./UserActions";

export default function user(state = { isLoggedIn: false }, action) {
    switch (action.type) {
        case LOGIN_USER: {
            state = {
                isLoggedIn: true,
                token: action.token
            };
            break;
        }
        case LOGOUT_USER: {
            state = {
                isLoggedIn: false,
                token: ""
            };
        }
        default: {
            state = {
                isLoggedIn: false
            };
        }
    }
}
