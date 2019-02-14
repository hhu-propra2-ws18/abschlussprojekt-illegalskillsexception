import { LOGIN_USER, LOGOUT_USER } from "./UserActions";

export default function user(state = { isLoggedIn: true }, action) {
    switch (action.type) {
        case LOGIN_USER: {
            return {
                isLoggedIn: true,
                token: action.token
            };
        }
        case LOGOUT_USER: { 
            state = {
                isLoggedIn: false,
                token: ""
            };
        }
        default: {
            state = {
                isLoggedIn: true
            };
        }
    }
    return state;
}
