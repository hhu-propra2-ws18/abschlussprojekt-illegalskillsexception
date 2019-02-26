import React from "react";

import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import LendItemComponentEditDialog from "../LendItemComponentEditDialog/LendItemComponentEditDialog";

export default class LendItemComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showDialog: false };
    }

    render() {
        return (
            <article>
                <h3>{this.props.data.title}</h3>
                <h5>Location:</h5>
                <p>{this.props.data.location}</p>
                <h5>Daily rate:</h5>
                <p>{this.props.data.dailyRate}</p>
                <h5>Safety deposit:</h5>
                <p>{this.props.data.deposit}</p>
                <Button onClick={() => this.showEditDialog()}>Edit</Button>
                <Dialog
                    defaultShow={this.state.showDialog}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog: false })}
                >
                    <LendItemComponentEditDialog
                        data={this.props.data}
                        close={() => this.hideEditDialog()}
                    />
                </Dialog>
            </article>
        );
    }

    showEditDialog() {
        this.setState({ showDialog: true });
    }

    hideEditDialog() {
        this.setState({ showDialog: false });
    }
}
/*

*/
