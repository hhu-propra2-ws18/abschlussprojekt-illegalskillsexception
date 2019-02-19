import React from "react";
import InquiriesViewItem from "./InquiriesViewItem/InquiriesViewItem";

import { connect } from "react-redux";

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

const mapStateToProps = state => {
    return { items: state.inquirystore };
};

export class InquiriesView extends React.Component {
    render() {
        return (
            <div className="grid-article-view">
                {this.props.items.map(dataItem => (
                    <InquiriesViewItem key={dataItem.id} data={dataItem} />
                ))}
            </div>
        );
    }
}

let inquiryViewEXport = connect(
    mapStateToProps,
    null,
    null
)(InquiriesView);
export default inquiryViewEXport;
