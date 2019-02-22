import React from "react";
import Button from "react-uwp/Button";

import "./BorrowItemAcceptanceDialog.css";

export default class BorrowItemAcceptanceDialog extends React.Component {
    render() {
        return (
            <article className="acceptance-dialog">
                <p>Do you really want to request borrowing this
                    from {this.props.startDate} to {this.props.endDate} ?</p>
                <div className="dialog-buttons-div">
                    <Button onClick={this.props.accept}>Yes</Button>
                    <Button onClick={this.props.close}>No</Button>
                </div>
            </article>
        );
    }
}
