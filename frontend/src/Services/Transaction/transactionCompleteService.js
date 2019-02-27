import {
    getAllTransactionsBackend,
    postTransactionProblem,
    postTransactionFinishedBackend,
    postTransactionProblemBackend,
    transactionItemReturnedBorrowerBackend
} from "./transactionBackendService";
import { store } from "../../Store/reduxInit";
import {
    getSetTransactionItemListAction,
    getRemoveTransactionItemAction
} from "../../Store/TransactionStore/TransactionActions";

export async function getAllTransaction() {
    let data = await getAllTransactionsBackend(store.getState().user.token);

    let action = getSetTransactionItemListAction(data.borrow, data.lend);
    store.dispatch(action);
}

export async function transactionItemReturnedBorrower(id) {
    let data = await transactionItemReturnedBorrowerBackend(
        id,
        store.getState().user.token
    );

    if (data.data.error) {
        return data;
    } else {
        await getAllTransaction();
    }

    return data;
}

export async function transactionItemReturnedLender(id) {
    let result = await postTransactionFinishedBackend(
        id,
        store.getState().user.token
    );

    if (result.data.error) {
        return result;
    }

    await getAllTransaction();
    return result;
}

export async function createTransactionProblem(id) {
    let result = await postTransactionProblemBackend(
        id,
        store.getState().user.token
    );

    if (result.data.error) {
        return result;
    }

    await getAllTransaction();
    return result;
}
