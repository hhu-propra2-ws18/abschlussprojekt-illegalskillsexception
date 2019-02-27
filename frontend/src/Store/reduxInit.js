import { createStore, combineReducers } from "redux";
import user from "./UserStore/UserReducer";
import borrowstore from "./BorrowStore/BorrowReducer";
import buystore from "./BuyStore/BuyReducer";
import lendstore from "./LendStore/LendReducer";
import inquirystore from "./InquiryStore/InquiryReducer";
import sellstore from "./SellStore/SellReducer";
import transactionstore from "./TransactionStore/TransactionReducer";

export const reducers = combineReducers({
    borrowstore,
    buystore,
    lendstore,
    inquirystore,
    sellstore,
    transactionstore,
    user
});

export function getDevtoolsExtension(object) {
    if (object.__REDUX_DEVTOOLS_EXTENSION__) {
        return object.__REDUX_DEVTOOLS_EXTENSION__();
    }
    return undefined;
}

//export const store = createStore(reducers, getDevtoolsExtension(window));
export const store = createStore(reducers, getDevtoolsExtension(window));
