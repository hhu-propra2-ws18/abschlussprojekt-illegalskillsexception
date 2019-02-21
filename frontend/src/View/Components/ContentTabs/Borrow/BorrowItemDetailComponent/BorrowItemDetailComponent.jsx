import React from "react";

import DatePicker from "react-uwp/DatePicker";
import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import BorrowItemAcceptanceDialog from "../BorrowItemAcceptanceDialog/BorrowItemAcceptanceDialog";
import { borrowItem } from "../../../../../Services/Borrow/borrowCompleteService";

export default class BorrowItemDetailComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showDialog: false };

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
                <Button onClick={() => this.showBorrowDialog()}>Borrow</Button>
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
                </Dialog>
            </article>
        );
    }

    createInquiry() {
        let data = {
            articleId: this.props.data.id,
            startDate: {
                day: this.startRef.current.dateIndex,
                month: this.startRef.current.monthIndex,
                year: this.startRef.current.yearIndex + 1969
            },
            endDate: {
                day: this.endRef.current.dateIndex,
                month: this.endRef.current.monthIndex,
                year: this.endRef.current.yearIndex + 1969
            }
        };
        console.log(data);


        borrowItem(data);
    }

    hideBorrowDialog() {
        this.setState({ showDialog: false });
    }

    showBorrowDialog() {
        this.setState({ showDialog: true });
    }
}
