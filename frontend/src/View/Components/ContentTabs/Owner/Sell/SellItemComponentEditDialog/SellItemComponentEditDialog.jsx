import React from "react";

import TextBox from "react-uwp/TextBox";
import Button from "react-uwp/Button";
import { updateSellItem } from "../../../../../../Services/Sell/sellCompleteService";

export default class SellItemComponentEditDialog extends React.Component {
    constructor(props) {
        super(props);

        this.titleRef = React.createRef();
        this.descRef = React.createRef();
        this.priceRef = React.createRef();
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
                <label>Price</label>
                <TextBox
                    ref={this.priceRef}
                    defaultValue={this.props.data.price}
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
            buyArticleId: this.props.data.id,
            title: this.titleRef.current.getValue(),
            description: this.descRef.current.getValue(),
            price: this.priceRef.current.getValue(),
            location: this.locationRef.current.getValue()
        };

        await updateSellItem(data);

        this.props.close();
    }
}
