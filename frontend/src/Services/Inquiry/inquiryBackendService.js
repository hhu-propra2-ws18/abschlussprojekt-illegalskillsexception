import Axios from "axios";
import {
    INQUIRY_GETALL_BORROW,
    INQUIRY_GETALL_LEND,
    INQUIRY_ACCEPT,
    INQUIRY_DECLINE,
} from "../urlConstants";

export async function getAllInquiriesBackend(
    token,
    urlBorrow = INQUIRY_GETALL_BORROW,
    urlLend = INQUIRY_GETALL_LEND
) {
    let dataBorrow = await Axios.get(urlBorrow, {
        headers: {
            Authorization: token
        }
    });
    let dataLend = await Axios.get(urlLend, {
        headers: {
            Authorization: token
        }
    });
    return {
        borrowList: dataBorrow.data.data,
        lendList: dataLend.data.data
    };
}

export async function inquiryAcceptBackend(id, token, url = INQUIRY_ACCEPT) {
    let data = await Axios.post(
        url,
        { inquiryId: id },
        {
            headers: {
                Authorization: token
            }
        }
    );

    return data;
}

export async function inquiryDeclineBackend(id, token, url = INQUIRY_DECLINE) {
    let data = await Axios.post(
        url,
        { inquiryId: id },
        {
            headers: {
                Authorization: token
            }
        }
    );

    return data;
}
