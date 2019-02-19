import React from "react";
import InquiriesViewItem from "./InquiriesViewItem/InquiriesViewItem";

const items = [
    {
        title: "Jackhammer",
        borrower: "Antoine",
        lender: "Jens Bendisposto",
        isLendingInquirie: true,
        lendTime: "24.02.2019 - 28.02.2019",
        status: "open",
        id: 1
    },
    {
        title: "Text",
        borrower: "Test",
        lender: "test",
        isLendingInquirie: true,
        lendTime: "dsagusioagdsikafgsa",
        status: "statsa",
        id: 2
    },
    {
        title: "Text",
        borrower: "Test",
        lender: "test",
        isLendingInquirie: false,
        lendTime: "dsagusioagdsikafgsa",
        status: "statsa",
        id: 3
    }
];

export default class InquiriesView extends React.Component {
    render() {
        return (
            <div className="grid-article-view">
                {items.map(dataItem => (
                    <InquiriesViewItem key={dataItem.id} data={dataItem} />
                ))}
            </div>
        );
    }
}
