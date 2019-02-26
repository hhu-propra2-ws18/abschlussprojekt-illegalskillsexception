import React from "react";
import Button from "react-uwp/Button";
import {
    transactionItemReturnedLender,
    transactionItemReturnedBorrower,
    createTransactionProblem
} from "../../../../../Services/Transaction/transactionCompleteService";

export default class TransactionsItem extends React.Component {
    render() {
        return (
            <article>
                <h2>Title</h2>
                <p>{this.props.data.title}</p>
                <h3>{this.props.data.title}</h3>
                <h5>Status</h5>
                <p> {this.props.data.status}</p>
                <h5>Return Date</h5>
                <p>
                    {this.props.isLender ? (
                        <div>
                            <Button
                                onClick={() =>
                                    transactionItemReturnedLender(
                                        this.props.data.id
                                    )
                                }
                            >
                                Item was returned in good condition
                            </Button>
                            <Button
                                onClick={() =>
                                    createTransactionProblem(this.props.data.id)
                                }
                            >
                                Item was returned in bad condition
                            </Button>
                        </div>
                    ) : (
                        <div>
                            <Button
                                onClick={() =>
                                    transactionItemReturnedBorrower(
                                        this.props.data.id
                                    )
                                }
                            >
                                Item returned
                            </Button>
                        </div>
                    )}
                    {this.props.data.returnDate}
                </p>
            </article>
        );
    }
}
