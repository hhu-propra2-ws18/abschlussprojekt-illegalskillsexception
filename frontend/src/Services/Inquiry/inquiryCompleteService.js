import {
    getAllInquiriesBackend,
    inquiryDeclineBackend,
    inquiryAcceptBackend
} from "./inquiryBackendService";
import { store } from "../../Store/reduxInit";
import {
    getSetInquiryItemListAction,
    getRemoveInquiryItemAction
} from "../../Store/InquiryStore/InquiryActions";

export async function getAllInquiries() {
    let data = await getAllInquiriesBackend(store.getState().user.token);

    let action = getSetInquiryItemListAction(data.borrowList, data.lendList);
    console.log(action);
    store.dispatch(action);
}

export async function acceptInquiry(id) {
    let data = await inquiryAcceptBackend(id, store.getState().user.token);

    if (data.data.error) {
        console.log(data);
        return data;
    }
    let action = getRemoveInquiryItemAction(id);
    store.dispatch(action);
    return data;
}

export async function declineInquiry(id) {
    let data = await inquiryDeclineBackend(id, store.getState().user.token);

    if (data.data.error) {
        console.log(data);
        return data;
    }
    let action = getRemoveInquiryItemAction(id);
    store.dispatch(action);
}
