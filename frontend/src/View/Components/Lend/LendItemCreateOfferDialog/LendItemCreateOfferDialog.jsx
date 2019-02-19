import React from "react";
import TextBox from "react-uwp/TextBox";
import Button from "react-uwp/Button";
import { createLendItem } from "../../../../Services/Lend/lendCompleteService";

export default class LendItemCreateOfferDialog extends React.Component {

    constructor(props){
        super(props);

        this.titleRef = React.createRef();
        this.descRef = React.createRef();
        this.depositRef = React.createRef();
        this.rateRef = React.createRef();
        this.locationRef = React.createRef();
    }


    render() {
        return (
            <div>
                <label>Title:</label>
                <TextBox ref={this.titleRef}/>
                <label>Description</label>
                <TextBox ref={this.descRef}/>
                <label>Safety Deposit</label>
                <TextBox type="number" ref={this.depositRef}/>
                <label>Daily rate</label>
                <TextBox type="number" ref={this.rateRef}/>
                <label>location</label>
                <TextBox ref={this.locationRef}/>
                <Button onClick={()=> this.createLendItem()}>Confirm</Button>
                <Button onClick={() => this.props.close()}>Cancel</Button>
            </div>
        );
    }
    createLendItem(){

        let data = {
            "title": this.titleRef.current.getValue(),
            "description": this.descRef.current.getValue(),
            "deposit": this.depositRef.current.getValue(),
            "dailyRate": this.rateRef.current.getValue(),
            "location": this.locationRef.current.getValue(),
        }

        createLendItem(data);
        
        this.props.close();
    }
}
