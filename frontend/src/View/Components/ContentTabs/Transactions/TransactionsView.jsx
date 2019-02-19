import React from "react";
import TransactionsItem from "./Transactions/TransactionsItem";

import { connect } from "react-redux";
import Button from "react-uwp/Button";
import {
    createTransactionProblem,
    getAllTransaction
} from "../../../../Services/Transaction/transactionCompleteService";

const mapStateToProps = state => {
    return { items: state.transactionstore };
};

export class TransactionsView extends React.Component {
    async componentDidMount() {
        await getAllTransaction();
    }

    render() {
        console.log(this.props.items);
        return (
            <>
                <div id="testtext" className="grid-article-view">
                    {this.props.items.map(dataItem => (
                        <TransactionsItem key={dataItem.id} data={dataItem} />
                    ))}
                </div>
            </>
        );
    }
}

let transactionView = connect(
    mapStateToProps,
    null,
    null
)(TransactionsView);
export default transactionView;
