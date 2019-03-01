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
    }

    render() {
        return (
            <>
                <div id="testtext" className="grid-article-view">
                    {this.props.itemsLend.length === 0 &&
                    this.props.itemsBorrow.length === 0 ? (
                        <article>
                            <h4>No transactions to display</h4>
                        </article>
                    ) : null}
                    {this.props.itemsLend.map(dataItem => (
                        <TransactionsItem
                            isLender={true}
                            key={dataItem.id}
                            data={dataItem}
                        />
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
