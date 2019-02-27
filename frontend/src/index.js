import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./View/App/App";
import * as serviceWorker from "./serviceWorker";

import "./Store/reduxInit";
import { store } from "./Store/reduxInit";
import { Provider } from "react-redux";
import {getAllConflicts} from "./Services/Conflict/conflictBackendService";



ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
