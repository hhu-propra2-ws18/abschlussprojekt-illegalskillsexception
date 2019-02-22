import React from "react";
import TransactionsItem from "./Transactions/TransactionsItem";

import { connect } from "react-redux";
import { getAllTransaction } from "../../../../Services/Transaction/transactionCompleteService";

const mapStateToProps = state => {
    return {
        itemsBorrow: state.transactionstore.borrowList,
        itemsLend: state.transactionstore.lendList
    };
};

export class TransactionsView extends React.Component {
    async componentDidMount() {
        await getAllTransaction();
        console.log("Transaction onload list - borrow", this.props.itemsBorrow);
        console.log("Transaction onload list - lend", this.props.itemsLend);
    }

    render() {
        return (
            <>
                <div id="testtext" className="grid-article-view">
                    {this.props.itemsLend.map(dataItem => (
                        <TransactionsItem isLender={true} key={dataItem.id} data={dataItem} />
                    ))}
                    {this.props.itemsBorrow.map(dataItem => (
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
