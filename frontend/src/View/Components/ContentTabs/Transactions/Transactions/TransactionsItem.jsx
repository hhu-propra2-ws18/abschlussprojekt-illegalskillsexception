import React from "react";
import Button from "react-uwp/Button";
import { transactionItemReturned, createTransactionProblem } from "../../../../../Services/Transaction/transactionCompleteService";

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
                        <Button onClick={()=> transactionItemReturned(this.props.data.id)}>Item was returned</Button>
                        <Button onClick={()=>createTransactionProblem(this.props.data.id)}>
                            Problem occured
                        </Button>
                    </div> : null
                }{this.props.data.returnDate}</p>
            </article>
        );
    }

    
}
