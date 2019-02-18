import React from "react";

import Button from "react-uwp/Button";

export default class InquiriesViewItem extends React.Component {
    render() {
        return (
            <article>
                <h3>{this.props.data.title}</h3>
                <p>{this.props.data.description}</p>
                <h5>Lendtime:</h5>
                <p>{this.props.data.lendTime}</p>
                {this.props.data.isLendingInquirie ? (
                    <div>
                        <h5>Borrower:</h5>
                        <p>{this.props.data.borrower}</p>
                        <div className="dialog-buttons-div">
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
                        <p>{this.props.data.lender}</p>
                        <h5>Status: </h5>
                        <p>{this.props.data.status}</p>
                    </div>
                )}
            </article>
        );
    }

    accept() {}
    decline() {}
}
