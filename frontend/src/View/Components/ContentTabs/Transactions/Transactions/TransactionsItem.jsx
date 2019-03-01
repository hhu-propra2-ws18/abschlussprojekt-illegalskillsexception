import React from "react";
import Button from "react-uwp/Button";
import {
    transactionItemReturnedLender,
    transactionItemReturnedBorrower,
    createTransactionProblem
} from "../../../../../Services/Transaction/transactionCompleteService";
import Dialog from "react-uwp/Dialog";

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
                <h5>Descriptiom</h5>
                <p>{this.props.data.inquiry.borrowArticle.description}</p>
                <h5>Status</h5>
                <p> {this.props.data.status}</p>
                <h5>Return Date</h5>
                <span>
                    {this.props.data.returnDate}
                    {(this.props.data.status === "OPEN" &&
                        !this.props.data.isLender) ||
                    (this.props.data.status === "RETURNED" &&
                        this.props.isLender)
                        ? this.getButtons()
                        : null}
                </span>
                {this.state.showError ? (
                    <Dialog
                        defaultShow={this.state.showError}
                        onCloseDialog={() =>
                            this.setState({ showError: false })
                        }
                    >
                        <h4>Sorry, an error occured</h4>
                        <p>{this.state.error.errorMessage}</p>
                    </Dialog>
                ) : null}
            </article>
        );
    }

    getButtons() {
        return this.props.isLender ? (
            <div>
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
                    Item in bad condition or not returned
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
