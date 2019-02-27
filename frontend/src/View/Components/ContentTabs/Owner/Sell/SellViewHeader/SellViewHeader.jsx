import React from "react";

import Dialog from "react-uwp/Dialog";
import LendItemCreateOfferDialog from "../../Lend/LendItemCreateOfferDialog/LendItemCreateOfferDialog";

import CommandBar from "react-uwp/CommandBar";
import AppBarButton from "react-uwp/AppBarButton";

import "./SellViewHeader.css";
import SellItemCreateOfferDialog from "../SellItemCreateOfferDialog/SellItemCreateOfferDialog";

export default class SellViewHeader extends React.Component {
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
                            label="Sell item"
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
                    <SellItemCreateOfferDialog
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
