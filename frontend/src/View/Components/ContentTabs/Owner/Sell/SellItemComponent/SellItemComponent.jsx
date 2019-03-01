import React, { Component } from "react";
import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import SellItemComponentEditDialog from "../SellItemComponentEditDialog/SellItemComponentEditDialog";

export default class SellItemComponent extends Component {
    constructor(props) {
        super(props);
        this.state = { showDialog: false };
    }

    render() {
        return (
            <article>
                <div className="bottom-button-aligndiv">
                    <h4>{this.props.data.title}</h4>
                    <h5>Description:</h5>
                    <p>{this.props.data.description}</p>
                    <h5>Price:</h5>
                    <p>{this.props.data.price}</p>
                    <div>
                        <Button
                            className="bottom-button"
                            onClick={() => this.setState({ showDialog: true })}
                        >
                            Edit
                        </Button>
                    </div>
                </div>
                {this.state.showDialog ? (
                    <Dialog
                        defaultShow={this.state.showDialog}
                        onClick={() => this.setState({ showDialog: false })}
                    >
                        <SellItemComponentEditDialog
                            close={() => this.closeDialog()}
                            data={this.props.data}
                        />
                    </Dialog>
                ) : null}
            </article>
        );
    }

    closeDialog() {
        this.setState({ showDialog: false });
    }
}
