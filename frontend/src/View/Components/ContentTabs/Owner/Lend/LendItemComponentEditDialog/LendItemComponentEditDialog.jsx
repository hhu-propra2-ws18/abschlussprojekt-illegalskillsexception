import React from "react";

import TextBox from "react-uwp/TextBox";
import Button from "react-uwp/Button";
import { updateLendItem } from "../../../../../../Services/Lend/lendCompleteService";

export default class LendItemComponentEditDialog extends React.Component {
    constructor(props) {
        super(props);

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
                <TextBox
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

        await updateLendItem(data);

        this.props.close();
    }
}
