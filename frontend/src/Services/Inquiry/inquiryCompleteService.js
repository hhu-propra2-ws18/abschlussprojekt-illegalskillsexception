import {
    getAllInquiriesBackend,
} from "./inquiryBackendService";
import { store } from "../../Store/reduxInit";
import { getSetTransactionItemListAction } from "../../Store/TransactionStore/TransactionActions";

export async function getAllInquiries() {
    let data = await getAllInquiriesBackend(store.getState().user.token);

    let action = getSetTransactionItemListAction(data.borrowList,data.lendList);
    console.log(action);
    store.dispatch(action);
}
