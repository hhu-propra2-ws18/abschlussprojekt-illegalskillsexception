import React from "react";
import TextBox from "react-uwp/TextBox";
import Button from "react-uwp/Button";
import { createLendItem } from "../../../../Services/Lend/lendCompleteService";

export default class LendItemCreateOfferDialog extends React.Component {
    render() {
        return (
            <div>
                <label>Title:</label>
                <TextBox />
                <label>Description</label>
                <TextBox />
                <label>Safety Deposit</label>
                <TextBox type="number" />
                <label>Daily rate</label>
                <TextBox type="number" />
                <label>location</label>
                <TextBox />
                <Button onClick={()=> this.createLendItem()}>Confirm</Button>
                <Button onClick={() => this.props.close()}>Cancel</Button>
            </div>
        );
    }
                                                            
    createLendItem(){

        let data={"title":"Studdsadsa "};

        createLendItem(data);
        
        this.props.close();
    }
}
