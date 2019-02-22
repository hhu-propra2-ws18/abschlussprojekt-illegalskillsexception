import {
    getAllTransactionsBackend,
    postTransactionProblem,
    postTransactionFinishedBackend,
    postTransactionProblemBackend
} from "./transactionBackendService";
import { store } from "../../Store/reduxInit";
import { getSetTransactionItemListAction } from "../../Store/TransactionStore/TransactionActions";

export async function getAllTransaction() {
    let data = await getAllTransactionsBackend(store.getState().user.token);

    let action = getSetTransactionItemListAction(data.borrow, data.lend);
    store.dispatch(action);
}

export async function transactionItemReturned(id) {
    return await postTransactionFinishedBackend(
        id,
        store.getState().user.token
    );
}

export async function createTransactionProblem(id) {
    let result = await postTransactionProblemBackend(
        id,
        store.getState().user.token
    );

    return result;
}
