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

        this.state = { showError: false };
    }

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
                            <Button onClick={() => this.returnItemBorrower()}>
                                Item returned
                            </Button>
                        </div>
                    )}
                    {this.props.data.returnDate}
                </p>
                {this.state.showError ? (
                    <Dialog
                        defaultShow={this.state.showError}
                        onCloseDialog={() =>
                            this.setState({ showError: false })
                        }
                    >
                        <article>
                            <h4>
                                Sorry, an error occured
                            </h4>
                            <p>{this.state.error.errorMessage}</p>
                            <Button
                                onClick={() =>
                                    this.setState({ showError: false })
                                }
                            >
                                Close
                            </Button>
                        </article>
                    </Dialog>
                ) : null}
            </article>
        );
    }

    async returnItemBorrower() {
        let data = await transactionItemReturnedBorrower(this.props.data.id);

        console.log(data);
        if (data.data.error) {
            this.setState({ showError: true, error: data.data.error });
        }
    }
}
