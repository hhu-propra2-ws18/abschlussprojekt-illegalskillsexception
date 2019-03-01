import React from "react";

import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import InquiryItemErrorDialog from "../InquiryItemComponent/InquiryItemErrorDialog";

import {
    declineInquiry,
    acceptInquiry
} from "../../../../../Services/Inquiry/inquiryCompleteService";
import ErrorDialog from "../../../../App/ErrorDialog/ErrorDialog";

export default class InquiriesViewItem extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showDialog: false,
            showError: false,
            errorMessage: ""
        };
    }

    render() {
        return (
            <article>
                <h3>
                    <span>{this.props.data.borrowArticle.title}</span>
                </h3>
                <h5>Lending period:</h5>
                <p>
                    {this.props.data.startDate} to {this.props.data.endDate}{" "}
                </p>
                {this.props.isLendingInquiry ? (
                    <div>
                        <h5>Borrower:</h5>
                        <p>{this.props.data.borrower.username} </p>
                        <div className="two-buttons-bottom">
                            <Button onClick={() => this.accept()}>
                                Accept
                            </Button>
                            <Button onClick={() => this.decline()}>
                                Decline
                            </Button>
                        </div>
                    </div>
                ) : (
                    <div>
                        <h5>Lender:</h5>
                        <p>{this.props.data.borrowArticle.owner.username} </p>
                        <h5>Status:</h5>
                        <p>{this.props.data.status} </p>
                    </div>
                )}

                <ErrorDialog
                    showDialog={this.state.showError}
                    description={this.state.errorMessage}
                    closeDialog={this.closeErrorDialog}
                />

            </article>
        );
    }

    async accept() {
        let result = await acceptInquiry(this.props.data.id);
        if (result.data.error) {
            this.setState({
                showError: true,
                errorMessage: result.data.error.errorMessage
            });
        }
    }

    async decline() {
        await declineInquiry(this.props.data.id);
    }

    closeErrorDialog = () => {
        this.setState({ showError: false, showDialog: false });
    };
}
