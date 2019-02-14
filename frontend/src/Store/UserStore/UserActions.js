export const LOGIN_USER = "LOGIN_USER";
export const LOGOUT_USER = "LOGOUT_USER";

export function getLoginUserAction(userData) {
    return {
        type: LOGIN_USER,
        token: userData.token
    };
}


export function getLogOutUserAction(userData) {
    return {
        type: LOGOUT_USER,
        token: ""
    };
}
