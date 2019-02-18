import React from "react";

import DatePicker from "react-uwp/DatePicker";
import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import BorrowItemAcceptanceDialog from "../BorrowItemAcceptanceDialog.jsx/BorrowItemAcceptanceDialog";

export default class BorrowItemDetailComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showDialog: false };
    }

    render() {
        return (
            <article>
                <h1>{this.props.data.title}</h1>
                <p>{this.props.data.description}</p>
                <h5>Location:</h5>
                <p>{this.props.data.location}</p>
                <DatePicker />
                <h5>Daily rate:</h5>
                <p>{this.props.data.dailyRate}</p>
                <h5>Safety deposit:</h5>
                <p>{this.props.data.deposit}</p>
                <div className="dialog-buttons-div">
                <Button onClick={() => this.borrowDialog()}>Borrow</Button>
                <Button onClick={() => this.props.close()}>Back</Button>
                </div>
                <Dialog
                    defaultShow={this.state.showDialog}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog: false })}
                >
                    <BorrowItemAcceptanceDialog
                        close={() => this.hideBorrowDialog()}
                        data={this.props.data}
                    />
                </Dialog>
            </article>
        );
    }

    hideBorrowDialog() {
        this.setState({ showDialog: false });
    }

    borrowDialog() {
        this.setState({ showDialog: true });
    }
}
