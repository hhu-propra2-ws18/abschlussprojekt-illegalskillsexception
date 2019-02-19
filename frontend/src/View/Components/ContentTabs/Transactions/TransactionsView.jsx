import React from "react";
import TransactionsItem from "./Transactions/TransactionsItem";

const items = [
    {
        title: "Der Gerät 9000",
        status: "Open",
        returnDate: "24.02.2019",
        id: 1
    }
];

export default class TransactionsView extends React.Component {
    render() {
        return (
            <>
                <div id="testtext" className="grid-article-view">
                    {items.map(dataItem => (
                        <TransactionsItem key={dataItem.id} data={dataItem} />
                    ))}
                </div>
            </>
        );
    }
}
