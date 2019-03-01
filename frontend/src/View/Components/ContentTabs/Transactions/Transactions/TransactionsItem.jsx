import React from "react";
import Button from "react-uwp/Button";
import {
    transactionItemReturnedLender,
    transactionItemReturnedBorrower,
    createTransactionProblem
} from "../../../../../Services/Transaction/transactionCompleteService";
import ErrorDialog from "../../../../App/ErrorDialog/ErrorDialog";

export default class TransactionsItem extends React.Component {
    constructor(props) {
        super(props);

        this.getButtons = this.getButtons.bind(this);

        this.state = { showError: false };
    }

    render() {
        return (
            <article>
                <h2>{this.props.data.inquiry.borrowArticle.title}</h2>
                <h5>Description:</h5>
                <p>{this.props.data.inquiry.borrowArticle.description}</p>
                <h5>Status:</h5>
                <p> {this.props.data.status}</p>
                <h5>Return Date:</h5>
                <p>{this.props.data.returnDate}</p>
                <span>
                    {this.props.data.returnDate}
                    {(this.props.data.status === "OPEN" &&
                        !this.props.data.isLender) ||
                    (this.props.data.status === "RETURNED" &&
                        this.props.isLender)
                        ? this.getButtons()
                        : null}
                </span>
                <ErrorDialog
                    showDialog={this.state.showError}
                    closeDialog={() => this.setState({ showError: false })}
                    description={
                        this.state.error ? this.state.error.errorMessage : null
                    }
                />
            </article>
        );
    }

    getButtons() {
        return this.props.isLender ? (
            <div className="two-buttons-stack">
                <Button
                    onClick={() =>
                        transactionItemReturnedLender(this.props.data.id)
                    }
                >
                    Item was returned in good condition
                </Button>
                <Button
                    onClick={() => createTransactionProblem(this.props.data.id)}
                >
                    Item returned in bad condition or not returned
                </Button>
            </div>
        ) : (
            <div>
                <Button onClick={() => this.returnItemBorrower()}>
                    Item returned
                </Button>
            </div>
        );
    }

    async returnItemBorrower() {
        let data = await transactionItemReturnedBorrower(this.props.data.id);

        if (data.data.error) {
            this.setState({ showError: true, error: data.data.error });
        }
    }
}
