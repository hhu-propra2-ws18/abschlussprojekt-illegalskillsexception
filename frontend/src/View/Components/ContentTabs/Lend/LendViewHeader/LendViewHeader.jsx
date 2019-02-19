import React from "react";

import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import LendItemCreateOfferDialog from "../LendItemCreateOfferDialog/LendItemCreateOfferDialog";

import CommandBar from "react-uwp/CommandBar";
import AppBarButton from "react-uwp/AppBarButton";
import AppBarSeparator from "react-uwp/AppBarSeparator";

import "./LendViewHeader.css";

export default class LendViewHeader extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showDialog: false };
    }

    render() {
        return (
            <div>
                <CommandBar
                    primaryCommands={[
                        <AppBarButton
                            label="List item"
                            onClick={() => this.createOfferDialog()}
                            icon={"\uE710"}
                            labelPosition="right"
                        />
                    ]}
                    labelPosition="right"
                    flowDirection="row"
                    expanded={true}
                    id="lend-command-bar"
                />
                <Dialog
                    defaultShow={this.state.showDialog}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog: false })}
                >
                    <LendItemCreateOfferDialog
                        close={() => this.hideCreateOfferDialog()}
                        data={this.props.data}
                    />
                </Dialog>
            </div>
        );
    }

    hideCreateOfferDialog() {
        this.setState({ showDialog: false });
    }

    createOfferDialog() {
        this.setState({ showDialog: true });
    }
}
