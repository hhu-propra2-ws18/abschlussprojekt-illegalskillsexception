export const BASEURL = "http://localhost:8080/";

export const USER = "user";

export const SIGN_UP = `${BASEURL}${USER}/sign-up`;
export const LOGINURL = `${BASEURL}login`;
export const USER_PROFILE = `${BASEURL}${USER}/`;
export const ADD_AMOUNT= `${BASEURL}${USER}/charge`;
export const OVERDUE_NOTIFICATION = `${BASEURL}${USER}/notifications`;

export const BORROW = "borrow/";

export const BORROW_GETALL = `${BASEURL}${BORROW}article/`;
export const BORROW_INQUIRY = `${BASEURL}${BORROW}inquiry/create`;

export const INQUIRY_GETALL_BORROW = `${BASEURL}${BORROW}inquiry/`;

export const BUY = "buy/"
export const BUY_GETALL = `${BASEURL}${BUY}`
export const BUY_CREATEITEN = `${BASEURL}${BUY}buyItem`
export const BUY_BUYITEM = `${BASEURL}${BUY}buyItem`

export const SELL = "sell/"
export const SELL_GETALL = `${BASEURL}${SELL}`;
export const SELL_CREATE_ITEM = `${BASEURL}${SELL}create`;
export const SELL_UPDATE_ITEM = `${BASEURL}${SELL}update`;


export const LEND = "lend/";

export const LEND_GETALL = `${BASEURL}${LEND}article/`;
export const LEND_CREATE = `${BASEURL}${LEND}article/create`;
export const LEND_UPDATE = `${BASEURL}${LEND}article/update`;

export const INQUIRY_GETALL_LEND = `${BASEURL}${LEND}inquiry/`;
export const INQUIRY_ACCEPT = `${BASEURL}${LEND}inquiry/accept`;
export const INQUIRY_DECLINE = `${BASEURL}${LEND}inquiry/decline`;

export const TRANSACTION_GETALL_BORROW = `${BASEURL}${BORROW}transaction/`;
export const TRANSACTION_GETALL_LEND = `${BASEURL}${LEND}transaction/`;
export const TRANSACTION_FINISHED_BORROWER = `${BASEURL}${BORROW}transaction/return`;
export const TRANSACTION_FINISHED_LENDER = `${BASEURL}${LEND}transaction/update`;
export const TRANSACTION_PROBLEM = `${BASEURL}${LEND}transaction/update`;

export const CONFLICT = "conflict/";
export const CONFLICT_GETALL = `${BASEURL}${CONFLICT}`;
export const CONFLICT_RESOLVE = `${BASEURL}${CONFLICT}release`;
export const CONFLICT_PUNISH = `${BASEURL}${CONFLICT}punish`;