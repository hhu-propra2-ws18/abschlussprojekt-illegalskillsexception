import {LOGIN_USER, LOGOUT_USER, SET_ADMIN} from "./UserActions";

export default function user(state = {
    isLoggedIn: false,
    admin: false,
    }, action) {
    switch (action.type) {
        case LOGIN_USER: {
            return {
                isLoggedIn: true,
                token: action.token,
                admin: action.admin
            };
        }
        case LOGOUT_USER: {
            return {
                isLoggedIn: false,
                token: "",
                admin: false
            };
        }
        case SET_ADMIN: {
            return {
                isLoggedIn: state.isLoggedIn,
                token: state.token,
                admin: action.admin,
            };
        }
        default:{
            return state;
        }
    }
}
