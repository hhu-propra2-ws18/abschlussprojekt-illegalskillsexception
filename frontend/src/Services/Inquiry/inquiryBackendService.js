import Axios from "axios";
import {
    INQUIRY_GETALL,
    INQUIRY_ACCEPT,
    INQUIRY_DECLINE
} from "../urlConstants";

export async function getAllInquiriesBackend(token, url = INQUIRY_GETALL) {
    let data = await Axios.get(url, {
        headers: {
            Authorization: token
        }
    });
    return data.data.data;
}

export async function inquiryAcceptBackendd(id, token, url = INQUIRY_ACCEPT) {
    let data = await Axios.post(
        url,
        { id: id },
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
        { id: id },
        {
            headers: {
                Authorization: token
            }
        }
    );

    return data;
}