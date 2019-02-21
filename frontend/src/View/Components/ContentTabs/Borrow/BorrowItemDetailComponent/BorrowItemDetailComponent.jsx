import React from "react";

import DatePicker from "react-uwp/DatePicker";
import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import BorrowItemAcceptanceDialog from "../BorrowItemAcceptanceDialog/BorrowItemAcceptanceDialog";
import { borrowItem } from "../../../../../Services/Borrow/borrowCompleteService";

export default class BorrowItemDetailComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showDialog: false, showError: false, errorMessage: "" };

        this.startRef = React.createRef();
        this.endRef = React.createRef();
    }

    render() {
        return (
            <article>
                <h1>{this.props.data.title}</h1>
                <p>{this.props.data.description}</p>
                <h5>Location:</h5>
                <p>{this.props.data.location}</p>
                <h5>Start date:</h5>
                <DatePicker ref={this.startRef} />
                <h5>End date:</h5>
                <DatePicker ref={this.endRef} />
                <h5>Daily rate:</h5>
                <p>{this.props.data.dailyRate}</p>
                <h5>Safety deposit:</h5>
                <p>{this.props.data.deposit}</p>
                <div className="dialog-buttons-div">
                    <Button onClick={() => this.showBorrowDialog()}>
                        Borrow
                    </Button>
                    <Button onClick={() => this.props.close()}>Back</Button>
                </div>
                <Dialog
                    defaultShow={this.state.showDialog}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog: false })}
                >
                    <BorrowItemAcceptanceDialog
                        close={() => this.hideBorrowDialog()}
                        accept={() => this.createInquiry()}
                        data={this.props.data}
                    />
                </Dialog>{" "}
                <Dialog
                    defaultShow={this.state.showError}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog: false })}
                >
                    {this.state.errorMessage}
                </Dialog>
            </article>
        );
    }

    createInquiry() {
        let startString =
            this.startRef.current.yearIndex +
            1969 +
            "-" +
            (this.startRef.current.monthIndex < 10 ? "0" : "") +
            this.startRef.current.monthIndex +
            "-" +
            (this.startRef.current.dateIndex < 10 ? "0" : "") +
            this.startRef.current.dateIndex;

        let endString =
            this.endRef.current.yearIndex +
            1969 +
            "-" +
            (this.endRef.current.monthIndex < 10 ? "0" : "") +
            this.endRef.current.monthIndex +
            "-" +
            (this.endRef.current.dateIndex < 10 ? "0" : "") +
            this.endRef.current.dateIndex;

        let data = {
            articleId: this.props.data.id,
            startDate: startString,
            endDate: endString
        };
        console.log(data);

        let result = borrowItem(data);
        if (!result.error) {
            this.hideBorrowDialog();
            this.props.close();
        } else {
        }
    }

    hideBorrowDialog() {
        this.setState({ showDialog: false });
    }

    showBorrowDialog() {
        this.setState({ showDialog: true });
    }
}
