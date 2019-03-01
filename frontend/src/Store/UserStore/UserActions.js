export const LOGIN_USER = "LOGIN_USER";
export const LOGOUT_USER = "LOGOUT_USER";
export const SET_ADMIN = "SET_ADMIN";
export const SET_THEME = "SET_THEME";

export function getLoginUserAction(userData, admin = false) {
    return {
        type: LOGIN_USER,
        token: userData.token ? userData.token : "test",
        admin
    };
}

export function setAdminStore(admin = true) {
    return {
        type: SET_ADMIN,
        admin
    };
}

export function getLogOutUserAction(userData) {
    return {
        type: LOGOUT_USER,
        token: ""
    };
}

export function setThemeAction(themeString) {
    return {
        type: SET_THEME,
        theme: themeString
    };
}
