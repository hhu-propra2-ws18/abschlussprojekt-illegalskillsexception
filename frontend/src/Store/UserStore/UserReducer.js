import { LOGIN_USER, LOGOUT_USER } from "./UserActions";

export default function user(state = { isLoggedIn: false }, action) {
    switch (action.type) {
        case LOGIN_USER: {
            return {
                isLoggedIn: true,
                token: action.token
            };
        }
        case LOGOUT_USER: {
            return {
                isLoggedIn: false,
                token: ""
            };
        }
        default: {
            return {
                isLoggedIn: false,
                token: ""
            };
        }
    }
}
