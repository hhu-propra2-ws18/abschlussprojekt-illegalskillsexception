import React from "react";

import DatePicker from "react-uwp/DatePicker";
import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import BorrowItemAcceptanceDialog from "../BorrowItemAcceptanceDialog/BorrowItemAcceptanceDialog";
import {
    borrowItem,
    getArticleAvailabilityList
} from "../../../../../../Services/Borrow/borrowCompleteService";
import ErrorDialog from "../../../../../App/ErrorDialog/ErrorDialog";

export default class BorrowItemDetailComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showDialog: false,
            showError: false,
            errorMessage: "",
            startDate: new Date(),
            endDate: new Date()
        };

        getArticleAvailabilityList(this.props.data.id).then(item => {
            console.log("item", item);
            this.setState({
                timeSpans: item.data.data.blockedTimespans
                    ? item.data.data.blockedTimespans
                    : []
            });
        });

        this.startRef = React.createRef();
        this.endRef = React.createRef();
    }

    render() {
        return (
            <article className="max-width-article">
                <h1>{this.props.data.title}</h1>
                <h5>Lender:</h5>
                <p>Username: {this.props.data.owner.username}</p>
                <h5>Description:</h5>
                <p>{this.props.data.description}</p>
                <h5>Location:</h5>
                <p>{this.props.data.location}</p>
                <h5 className="no-underline">Start date:</h5>
                <DatePicker
                    ref={this.startRef}
                    onChangeDate={this.setStartDate}
                    defaultDate={this.state.startDate}
                />
                <h5 className="no-underline">End date:</h5>
                <DatePicker
                    ref={this.endRef}
                    onChangeDate={this.setEndDate}
                    defaultDate={this.state.endDate}
                />
                <div className="two-column-display">
                    <h5>Daily rate:</h5>
                    <p>{this.props.data.dailyRate}</p>
                    <h5>Safety deposit:</h5>
                    <p>{this.props.data.deposit}</p>
                </div>

                {this.state.timeSpans && this.state.timeSpans !== 0 ? (
                    <div>
                        <h5>Periods the item is not available</h5>
                        {this.state.timeSpans.map(item => (
                            <p>{item}</p>
                        ))}
                    </div>
                ) : null}
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
                </Dialog>
                <ErrorDialog
                    showDialog={this.state.showError}
                    description={this.state.errorMessage}
                    closeDialog={() => this.closeErrorDialog()}
                />
            </article>
        );
    }

    setStartDate = () => {
        this.setState({ startDate: this.startRef.current.state.currDate });
    };

    setEndDate = () => {
        this.setState({ endDate: this.endRef.current.state.currDate });
    };

    closeErrorDialog = () => {
        this.setState({ showError: false, showDialog: false });
    };

    transformDate = date => {
        const da = `${date.getDate() < 10 ? "0" : ""}${date.getDate()}`;
        const mo = `${date.getMonth() + 1 < 10 ? "0" : ""}${date.getMonth() +
            1}`;
        return `${date.getFullYear()}-${mo}-${da}`;
    };

    createInquiry = async () => {
        let startString = this.transformDate(this.state.startDate);
        let endString = this.transformDate(this.state.endDate);

        let data = {
            articleId: this.props.data.id,
            startDate: startString,
            endDate: endString
        };

        let result = await borrowItem(data);
        if (!result.data.error) {
            this.hideBorrowDialog();
            this.props.close();
        } else {
            this.setState({
                showError: true,
                errorMessage: result.data.error.errorMessage
            });
        }
    };

    hideBorrowDialog() {
        this.setState({ showDialog: false });
    }

    showBorrowDialog() {
        this.setState({ showDialog: true });
    }
}
