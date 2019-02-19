import { getAllTransactionsBackend, postTransactionProblem } from "./transactionBackendService";
import { store } from "../../Store/reduxInit";
import { getSetTransactionItemListAction } from "../../Store/TransactionStore/TransactionActions";

export async function getAllTransaction() {
    let data = await getAllTransactionsBackend(store.getState().user.token);

    let action = getSetTransactionItemListAction(data);
    store.dispatch(action);
}


export async function createTransactionProblem(id){
    let result = await postTransactionProblem(id,store.getState().user.token);

    return result;
}
