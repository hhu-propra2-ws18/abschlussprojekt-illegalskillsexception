export const LOGIN_USER = "LOGIN_USER";
export const LOGOUT_USER = "LOGOUT_USER";
export const SET_ADMIN = "SET_ADMIN";

export function getLoginUserAction(userData, admin = false) {
    return {
        type: LOGIN_USER,
        token: userData.token ? userData.token : "test",
        admin
    };
}

export function setAdmin() {
    return {
        type: SET_ADMIN,
        admin: true,
    };
}


export function getLogOutUserAction(userData) {
    return {
        type: LOGOUT_USER,
        token: ""
    };
}
