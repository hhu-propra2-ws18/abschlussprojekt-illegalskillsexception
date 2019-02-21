import React from "react";
import Button from "react-uwp/Button";

export default class TransactionsItem extends React.Component {
    render() {
        return (
            <article>
                <h3>{this.props.data.title}</h3>
                <h5>Status</h5>
                <p> {this.props.data.status}</p>
                <h5>Return Date</h5>
                <p> 
                {
                    this.props.isLender ? 
                    <div>
                        <Button >Item was returned</Button>
                        <Button>
                            Problem occured
                        </Button>
                    </div> : <div>
                        <Button>Item returned</Button>
                    </div>
                }{this.props.data.returnDate}</p>
            </article>
        );
    }

    
}
