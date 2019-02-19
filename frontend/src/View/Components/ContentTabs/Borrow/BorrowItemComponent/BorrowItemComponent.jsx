import React from "react";

import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import BorrowItemDetailComponent from "../BorrowItemDetailComponent/BorrowItemDetailComponent";

export default class BorrowItemComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showDialog: false };
    }

    render() {
        return (
            <article>
                <h1>{this.props.data.title}</h1>
                <h6>Daily rate:</h6>
                <p>{this.props.data.dailyRate}</p>
                <h6>Safety deposit:</h6>
                <p>{this.props.data.deposit}</p>
                <h6>Location:</h6>
                <p>{this.props.data.location}</p>
                <Button onClick={() => this.onClickDetails()}>Details</Button>
                <Dialog
                    defaultShow={this.state.showDialog}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog: false })}
                > 
                    <BorrowItemDetailComponent
                        close={() => this.closeDialog()}
                        data={this.props.data}
                    />
                </Dialog>
            </article>
        );
    }

    closeDialog() {
        this.setState({ showDialog: false });
    }

    onClickDetails() {
        this.setState({ showDialog: true });
    }
}
