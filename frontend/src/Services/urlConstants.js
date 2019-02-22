export const BASEURL = "http://localhost:8080/";

export const USER = "user";

export const SIGN_UP = `${BASEURL}user/sign-up`;
export const LOGINURL = `${BASEURL}login`;
export const USER_PROFILE = `${BASEURL}${USER}/`;

export const BORROW = "borrow/";

export const BORROW_GETALL = `${BASEURL}${BORROW}article/`;
export const BORROW_INQUIRY = `${BASEURL}${BORROW}inquiry/create`;

export const INQUIRY_GETALL_BORROW = `${BASEURL}${BORROW}inquiry/`;

export const LEND = "lend/";

export const LEND_GETALL = `${BASEURL}${LEND}article/`;
export const LEND_CREATE = `${BASEURL}${LEND}article/create`;
export const LEND_UPDATE = `${BASEURL}${LEND}article/update`;


export const INQUIRY_GETALL_LEND = `${BASEURL}${LEND}inquiry/`;
export const INQUIRY_ACCEPT = `${BASEURL}inquiry/accept`;
export const INQUIRY_DECLINE = `${BASEURL}${LEND}inquiry/decline`;

export const TRANSACTION_GETALL = `${BASEURL}transaction/getAll`;
export const TRANSACTION_PROBLEM = `${BASEURL}transaction/problem`;

/*

export const BASEURL = "http://localhost:8080/api/";

export const USER_PROFILE = `${BASEURL}${USER}/`;
export const SIGN_UP = `${BASEURL}${USER}/sign-up`;
export const LOGINURL = `http://localhost:8080/login`;

export const INQUIRY_GETALL_BORROW = `${BASEURL}${BORROW}inquiry/`;

export const TRANSACTION_GETALL = `${BASEURL}${BORROW}transaction/`;

export const INQUIRY_ACCEPT = `${BASEURL}${LEND}inquiry/accept`;

export const TRANSACTION_PROBLEM = `${BASEURL}${LEND}transaction/problem`;

export const TRANSACTION_RETURNED = `${BASEURL}${LEND}transaction/update`;

*/
