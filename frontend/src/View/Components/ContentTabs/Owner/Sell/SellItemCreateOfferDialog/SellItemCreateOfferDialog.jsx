import React from "react";
import TextBox from "react-uwp/TextBox";
import Button from "react-uwp/Button";
import { createSellItem } from "../../../../../../Services/Sell/sellCompleteService";

export default class SellItemCreateOfferDialog extends React.Component {
    constructor(props) {
        super(props);

        this.titleRef = React.createRef();
        this.descRef = React.createRef();
        this.profitRef = React.createRef();
        this.locationRef = React.createRef();
    }

    render() {
        return (
            <article>
                <form
                    onSubmit={e => {
                        e.preventDefault();
                        this.create();
                    }}
                >
                    <label>Title</label>
                    <TextBox ref={this.titleRef} required={true} />
                    <label>Description</label>
                    <TextBox ref={this.descRef} />
                    <label>Price</label>
                    <TextBox
                        type="number"
                        ref={this.profitRef}
                        required={true}
                    />
                    <label>Location</label>
                    <TextBox ref={this.locationRef} />
                    <Button type="submit">Confirm</Button>
                    <Button type="button" onClick={() => this.props.close()}>Cancel</Button>
                </form>
            </article>
        );
    }
    async create() {
        let data = {
            title: this.titleRef.current.getValue(),
            description: this.descRef.current.getValue(),
            price: this.profitRef.current.getValue(),
            location: this.locationRef.current.getValue()
        };

        await createSellItem(data);

        this.props.close();
    }
}
