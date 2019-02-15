import React from "react";

import Button from "react-uwp/Button";

export default class InquiriesViewItem extends React.Component {
    render() {
        return (
            <article>
                <h1>{this.props.data.title}</h1>
                <p>{this.props.data.description}</p>

                {this.props.data.isLendingInquirie ? (
                    <div>
                        <p>Borrower:</p>

                        <p>{this.props.data.borrower}</p>
                        <p>{this.props.data.lendTime}</p>

                        <Button onClick={() => this.accept()}>Accept</Button>
                        <Button onClick={() => this.decline()}>Decline</Button>
                    </div>
                ) : (
                    <div>
                        <p>Lender:</p>
                        <p>{this.props.data.lender}</p>
                        <p>Status: {this.props.data.status}</p>

                        <p>{this.props.data.lendTime}</p>
                    </div>
                )}
            </article>
        );
    }

    accept() {}
    decline() {}
}
