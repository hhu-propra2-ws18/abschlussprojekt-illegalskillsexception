import React from "react";
import InqueriesViewItem from "../InqueriesViewItem/InqueriesViewItem";

const testData = {
    title:"Text",
    borrower:"Test",
    lender:"test",
    isLendingInquerie:false,
    lendTime:"dsagusioagdsikafgsa",
    status:"statsa"
}

export default class InqueriesView extends React.Component {
    render() {
        return <div>
                <InqueriesViewItem data={testData}></InqueriesViewItem>
            </div>;
    }
}
