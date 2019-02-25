export const BASEURL = "http://localhost:8080/";

export const USER = "user";

export const SIGN_UP = `${BASEURL}${USER}/sign-up`;
export const LOGINURL = `${BASEURL}login`;
export const USER_PROFILE = `${BASEURL}${USER}/`;
export const CHARGE_AMOUNT = `${BASEURL}${USER}/charge`;
export const OVERDUE_NOTIFICATION = `${BASEURL}${USER}/notifications`;

export const BORROW = "borrow/";

export const BORROW_GETALL = `${BASEURL}${BORROW}article/`;
export const BORROW_INQUIRY = `${BASEURL}${BORROW}inquiry/create`;

export const INQUIRY_GETALL_BORROW = `${BASEURL}${BORROW}inquiry/`;

export const LEND = "lend/";

export const LEND_GETALL = `${BASEURL}${LEND}article/`;
export const LEND_CREATE = `${BASEURL}${LEND}article/create`;
export const LEND_UPDATE = `${BASEURL}${LEND}article/update`;

export const INQUIRY_GETALL_LEND = `${BASEURL}${LEND}inquiry/`;
export const INQUIRY_ACCEPT = `${BASEURL}${LEND}inquiry/accept`;
export const INQUIRY_DECLINE = `${BASEURL}${LEND}inquiry/decline`;

export const TRANSACTION_GETALL_BORROW = `${BASEURL}${BORROW}transaction/`;
export const TRANSACTION_GETALL_LEND = `${BASEURL}${LEND}transaction/`;
export const TRANSACTION_FINISHED = `${BASEURL}${LEND}transaction/update`
export const TRANSACTION_PROBLEM = `${BASEURL}${LEND}transaction/problem`;