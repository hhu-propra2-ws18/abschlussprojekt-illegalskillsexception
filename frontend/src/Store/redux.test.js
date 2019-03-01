import { reducers } from "./reduxInit";

test("user login -log out reducer", () => {
    let state;
    state = reducers(
        {
            borrowstore: [],
            buystore: [],
            lendstore: [],
            inquirystore: { borrowList: [], lendList: [] },
            sellstore: [],
            transactionstore: { borrowList: [], lendList: [] },
            conflictstore: { conflictList: [] },
            user: { isLoggedIn: false, admin: false }
        },
        {
            type: "LOGIN_USER",
            token:
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTUyMjk5MjczfQ.9GKqnVodyWnRe7lm8-HaHU0e5YtaX6RLNi4iL_wUfuilaP4OqyCLdzOAOXjuGrPCcHCDRllXgA8bGBKuN2c-uQ",
            admin: false
        }
    );
    expect(state).toEqual({
        borrowstore: [],
        buystore: [],
        lendstore: [],
        inquirystore: { borrowList: [], lendList: [] },
        sellstore: [],
        transactionstore: { borrowList: [], lendList: [] },
        conflictstore: { conflictList: [] },
        user: {
            isLoggedIn: true,
            token:
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTUyMjk5MjczfQ.9GKqnVodyWnRe7lm8-HaHU0e5YtaX6RLNi4iL_wUfuilaP4OqyCLdzOAOXjuGrPCcHCDRllXgA8bGBKuN2c-uQ",
            admin: false
        }
    });
    state = reducers(
        {
            borrowstore: [],
            buystore: [],
            lendstore: [],
            inquirystore: { borrowList: [], lendList: [] },
            sellstore: [],
            transactionstore: { borrowList: [], lendList: [] },
            conflictstore: { conflictList: [] },
            user: { isLoggedIn: true, admin: false }
        },
        {
            type: "LOGOUT_USER",
            token: "",
            admin: false
        }
    );
    expect(state).toEqual({
        borrowstore: [],
        buystore: [],
        lendstore: [],
        inquirystore: { borrowList: [], lendList: [] },
        sellstore: [],
        transactionstore: { borrowList: [], lendList: [] },
        conflictstore: { conflictList: [] },
        user: {
            isLoggedIn: false,
            token: "",
            admin: false
        }
    });
    state = reducers(
        {
            borrowstore: [],
            buystore: [],
            lendstore: [],
            inquirystore: { borrowList: [], lendList: [] },
            sellstore: [],
            transactionstore: { borrowList: [], lendList: [] },
            conflictstore: { conflictList: [] },
            user: { isLoggedIn: true, admin: false }
        },
        {
            type: "SET_ADMIN",
            token:
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTUyMjk5MjczfQ.9GKqnVodyWnRe7lm8-HaHU0e5YtaX6RLNi4iL_wUfuilaP4OqyCLdzOAOXjuGrPCcHCDRllXgA8bGBKuN2c-uQ",
            admin: true
        }
    );
    expect(state).toEqual({
        borrowstore: [],
        buystore: [],
        lendstore: [],
        inquirystore: { borrowList: [], lendList: [] },
        sellstore: [],
        transactionstore: { borrowList: [], lendList: [] },
        conflictstore: { conflictList: [] },
        user: {
            isLoggedIn: true,
            token:
                undefined,
            admin: true
        }
    });
    state = reducers(
        {
            borrowstore: [],
            buystore: [],
            lendstore: [],
            inquirystore: { borrowList: [], lendList: [] },
            sellstore: [],
            transactionstore: { borrowList: [], lendList: [] },
            conflictstore: { conflictList: [] },
            user: { isLoggedIn: true, admin: false }
        },
        {
            type: "SET_THEME",
            token:
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTUyMjk5MjczfQ.9GKqnVodyWnRe7lm8-HaHU0e5YtaX6RLNi4iL_wUfuilaP4OqyCLdzOAOXjuGrPCcHCDRllXgA8bGBKuN2c-uQ",
            admin: false,
            theme: "dark"
        }
    );
    expect(state).toEqual({
        borrowstore: [],
        buystore: [],
        lendstore: [],
        inquirystore: { borrowList: [], lendList: [] },
        sellstore: [],
        transactionstore: { borrowList: [], lendList: [] },
        conflictstore: { conflictList: [] },
        user: {
            isLoggedIn: true,
            token:
               undefined,
            admin: false,
            theme: "dark"
        }
    });
});
