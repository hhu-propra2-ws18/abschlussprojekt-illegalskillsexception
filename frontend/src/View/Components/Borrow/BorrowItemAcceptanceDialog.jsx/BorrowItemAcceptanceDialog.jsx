import React from "react";
import Button from "react-uwp/Button";

import "./BorrowItemAcceptanceDialog.css";

export default class BorrowItemAcceptanceDialog extends React.Component {
    render() {
        return (
            <div className="acceptance-dialog">
                <p>Do you really want to rent this?</p>
                <Button onClick={this.props.close}>Yes</Button>
                <Button onClick={this.props.close}>No</Button>
            </div>
        );
    }


}
