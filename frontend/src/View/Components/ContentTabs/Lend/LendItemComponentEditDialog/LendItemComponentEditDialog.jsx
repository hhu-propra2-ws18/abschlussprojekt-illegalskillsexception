import React from "react";

import TextBox from "react-uwp/TextBox";
import Button from "react-uwp/Button";
import Dialog from "react-uwp/Dialog";
import { updateLendItem } from "../../../../../../Services/Lend/lendCompleteService";
import LendItemEditErrorDialog from "../LendItemComponent/LendItemEditErrorDialog";

export default class LendItemComponentEditDialog extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            showDialog: false,
            showError: false,
            errorMessage: "",
        };
        this.titleRef = React.createRef();
        this.descRef = React.createRef();
        this.depositRef = React.createRef();
        this.rateRef = React.createRef();
        this.locationRef = React.createRef();
    }

    render() {
        return (
            <article>
                <label>Title:</label>
                <TextBox
                    ref={this.titleRef}
                    defaultValue={this.props.data.title}
                />
                <label>Description</label>
                <TextBox acceptsReturn={true}
                    ref={this.descRef}
                    defaultValue={this.props.data.description}
                    height=""
                />
                <label>Safety Deposit</label>
                <TextBox
                    ref={this.depositRef}
                    defaultValue={this.props.data.deposit}
                    type="number"
                />
                <label>Daily rate</label>
                <TextBox
                    ref={this.rateRef}
                    defaultValue={this.props.data.dailyRate}
                    type="number"
                />
                <label>location</label>
                <TextBox
                    ref={this.locationRef}
                    defaultValue={this.props.data.location}
                />
                <div className="dialog-buttons-div">
                    <Button onClick={() => this.saveChanges()}>Confirm</Button>
                    <Button onClick={() => this.props.close()}>Cancel</Button>
                </div>
                <Dialog
                    defaultShow={this.state.showError}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog: false })}
                >
                    <LendItemEditErrorDialog errorMessage={this.state.errorMessage} closeDialog={this.closeErrorDialog}/>
                </Dialog>

            </article>
        );
    }

    async saveChanges() {
        let data = {
            articleId: this.props.data.id,
            title: this.titleRef.current.getValue(),
            description: this.descRef.current.getValue(),
            deposit: this.depositRef.current.getValue(),
            dailyRate: this.rateRef.current.getValue(),
            location: this.locationRef.current.getValue()
        };

        let result = await updateLendItem(data);
        if (result.data.error) {
            this.setState( {
                showError:true,
                errorMessage: result.data.error.errorMessage
            })
        }

        this.props.close();
    }

    closeErrorDialog = () => {
        this.setState({showError: false, showDialog: false});
    };

}
