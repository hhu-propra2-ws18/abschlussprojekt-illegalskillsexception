import React from "react";
import InquiriesViewItem from "../InquiriesViewItem/InquiriesViewItem";

const testData = {
    title:"Text",
    borrower:"Test",
    lender:"test",
    isLendingInquirie:false,
    lendTime:"dsagusioagdsikafgsa",
    status:"statsa"
}

export default class InquiriesView extends React.Component {
    render() {
        return <div>
                <InquiriesViewItem data={testData}></InquiriesViewItem>
            </div>;
    }
}
