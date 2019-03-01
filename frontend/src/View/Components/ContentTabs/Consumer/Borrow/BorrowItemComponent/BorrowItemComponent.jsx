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
                <h3>{this.props.data.title}</h3>
                <div className="two-column-display">
                    <h5>Daily rate:</h5>
                    <p>{this.props.data.dailyRate}</p>
                    <h5>Safety deposit:</h5>
                    <p>{this.props.data.deposit}</p>
                </div>
                <h5>Location:</h5>
                <p>{this.props.data.location}</p>
                <Button className="bottom-button"onClick={() => this.onClickDetails()}>Details</Button>
                {this.state.showDialog && (
                    <Dialog
                        defaultShow={this.state.showDialog}
                        style={{ zIndex: 400 }}
                        onCloseDialog={() =>
                            this.setState({ showDialog: false })
                        }
                    >
                        <BorrowItemDetailComponent
                            close={() => this.closeDialog()}
                            data={this.props.data}
                        />
                    </Dialog>
                )}
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
