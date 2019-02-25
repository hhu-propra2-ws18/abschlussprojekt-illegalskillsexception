import React from "react";

import DatePicker from "react-uwp/DatePicker";
import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import BorrowItemAcceptanceDialog from "../BorrowItemAcceptanceDialog/BorrowItemAcceptanceDialog";
import BorrowItemErrorDialog from "../BorrowItemErrorDialog/BorrowItemErrorDialog";
import { borrowItem } from "../../../../../Services/Borrow/borrowCompleteService";

export default class BorrowItemDetailComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showDialog: false,
            showError: false,
            errorMessage: "",
            startDate: new Date(),
            endDate: new Date(),
        };

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
                <DatePicker
                    ref={this.startRef}
                    onChangeDate={this.setStartDate}
                    defaultDate={this.state.startDate}
                />
                <h5>End date:</h5>
                <DatePicker
                    ref={this.endRef}
                    onChangeDate={this.setEndDate}
                    defaultDate={this.state.endDate}
                />
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
                        startDate={this.transformDate(this.state.startDate)}
                        endDate={this.transformDate(this.state.endDate)}
                    />
                </Dialog>{" "}
                <Dialog
                    defaultShow={this.state.showError}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog: false })}
                >
                    <BorrowItemErrorDialog errorMessage={this.state.errorMessage} closeDialog={this.closeErrorDialog}/>
                </Dialog>
            </article>
        );
    }

    setStartDate = () => {
        this.setState({startDate: this.startRef.current.state.currDate})
    };

    setEndDate = () => {
        this.setState({endDate: this.endRef.current.state.currDate})
    };

    closeErrorDialog = () => {
        this.setState({showError: false, showDialog: false});
    };

    transformDate = (date) => {
        const da = `${(date.getDate()) < 10 ? "0" : ""}${date.getDate()}`;
        const mo = `${(date.getMonth() + 1) < 10 ? "0" : ""}${date.getMonth() + 1}`;
        return `${date.getFullYear()}-${mo}-${da}`;
    };

    createInquiry = async () => {
        let startString = this.transformDate(this.state.startDate);
        let endString = this.transformDate(this.state.endDate);

        let data = {
            articleId: this.props.data.id,
            startDate: startString,
            endDate: endString,
        };

        let result = await borrowItem(data);
        console.log("Result: ", result); //TODO Remove log
        if (!result.data.error) {
            this.hideBorrowDialog();
            this.props.close();
        } else {
            this.setState({
                showError:true,
                errorMessage: result.data.error.errorMessage
                }
            )
        }
    };

    hideBorrowDialog() {
        this.setState({ showDialog: false });
    }

    showBorrowDialog() {
        this.setState({ showDialog: true });
    }
}
