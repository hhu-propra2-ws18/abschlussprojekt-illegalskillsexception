import React from "react";

import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import LendItemCreateOfferDialog from "../LendItemCreateOfferDialog/LendItemCreateOfferDialog";

export default class LendViewHeader extends React.Component {
    constructor(props) {
        super(props);
        this.state = { showDialog: false };
    }

    render() {
        return (
            <div>
                <Button onClick={()=>this.createOfferDialog()}>Create Offer</Button>
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
