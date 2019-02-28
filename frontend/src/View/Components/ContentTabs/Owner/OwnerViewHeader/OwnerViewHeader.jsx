import React from "react";

import Dialog from "react-uwp/Dialog";

import CommandBar from "react-uwp/CommandBar";
import AppBarButton from "react-uwp/AppBarButton";

import "./OwnerViewHeader.css";
import SellItemCreateOfferDialog from "../Sell/SellItemCreateOfferDialog/SellItemCreateOfferDialog";
import LendItemCreateOfferDialog from "../Lend/LendItemCreateOfferDialog/LendItemCreateOfferDialog";

export default class OwnerViewHeader extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showDialogLend: false, showDialogSell: false };
    }

    render() {
        return (
            <div>
                <CommandBar
                    primaryCommands={[
                        <AppBarButton
                            label="List for rent"
                            onClick={() => this.createOfferDialogLend()}
                            icon={"\uE710"}
                            labelPosition="right"
                        />,
                        <AppBarButton
                            label="List for sale"
                            onClick={() => this.createOfferDialogLend()}
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
                    defaultShow={this.state.showDialogLend}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() =>
                        this.setState({ showDialogLend: false })
                    }
                >
                    <LendItemCreateOfferDialog
                        close={() => this.hideCreateOfferDialogLend()}
                        data={this.props.data}
                    />
                </Dialog>
                <Dialog
                    defaultShow={this.state.showDialogSell}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialogSell: false })}
                >
                    <SellItemCreateOfferDialog
                        close={() => this.hideCreateOfferDialogSell()}
                        data={this.props.data}
                    />
                </Dialog>
            </div>
        );
    }

    hideCreateOfferDialogLend() {
        this.setState({ showDialogLend: false });
    }

    createOfferDialogLend() {
        this.setState({ showDialogLend: true });
    }

    hideCreateOfferDialogSell() {
        this.setState({ showDialogSell: false });
    }

    createOfferDialogSell() {
        this.setState({ showDialogSell: true });
    }
}
