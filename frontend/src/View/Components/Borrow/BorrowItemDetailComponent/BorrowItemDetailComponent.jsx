import React from "react";

import CalendarView from "react-uwp/CalendarView";
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
                <h6>Place:</h6>
                <p>{this.props.data.place}</p>
                <CalendarView />
                <h6>Daily rate:</h6>
                <p>{this.props.data.dailyRate}</p>
                <h6>Safety deposit:</h6>
                <p>{this.props.data.deposit}</p>
                <Button onClick={() => this.borrowDialog()}>Borrow</Button>
                <Button onClick={() => this.props.close()}>Back</Button>
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
