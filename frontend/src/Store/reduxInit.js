import user from "./UserStore/UserReducer";
import lendstore from "./LendStore/LendReducer";
import { createStore,combineReducers } from "redux";

export const reducers = combineReducers({
    user,
    lendstore
});

export function getDevtoolsExtension(object) {
    if (object.__REDUX_DEVTOOLS_EXTENSION__) {
        return object.__REDUX_DEVTOOLS_EXTENSION__();
    }
    return undefined;
}

//export const store = createStore(reducers, getDevtoolsExtension(window));
export const store = createStore(reducers, getDevtoolsExtension(window));
