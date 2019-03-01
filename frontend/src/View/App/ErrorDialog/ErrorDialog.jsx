import React, { Component } from "react";
import ContentDialog from "react-uwp/ContentDialog";

import "./ErrorDialog.css"

export default class ErrorDialog extends Component {
    render() {
        return this.props.showDialog ? (
            <ContentDialog
                defaultShow={this.props.showDialog}
                style={{ zIndex: 400 }}
                onCloseDialog={() => this.props.closeDialog()}
                primaryButtonAction={() => this.props.closeDialog()}
                primaryButtonText="Close"
                secondaryButtonText=""
                title={
                    this.props.title
                        ? this.props.title
                        : "Sorry, an error occurred"
                }
                id="error-dialog"
                content={this.props.description}
            />
        ) : (
            <div />
        );
    }
}
