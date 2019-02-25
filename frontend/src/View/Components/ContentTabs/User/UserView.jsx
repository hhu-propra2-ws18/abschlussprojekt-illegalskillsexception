import React from "react";
import {
    getUserDetails,
    chargeUserAccount
} from "../../../../Services/User/authentificationCompleteService";
import Button from "react-uwp/Button";

export default class UserView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            profile: {
                accountBalance: "Number",
                username: "String",
                completedTransactions: [
                    { sender: "null", receiver: "null", amount: 0 }
                ]
            }
        };
    }

    async loadProfile() {
        this.setState({
            profile: {
                accountBalance: "Number",
                username: "String",
                completedTransactions: [
                    { sender: "null", receiver: "null", amount: 0 }
                ]
            }
        });
        let data = await getUserDetails();
        this.setState({ profile: data.data });
    }

    async componentDidMount() {
        await this.loadProfile();
    }

    render() {
        console.log(this.state.profile.completedTransactions);
        return (
            <article>
                <h3>Username</h3>
                <p>{this.state.profile.username}</p>
                <h3>Balance</h3>
                <p>{this.state.profile.accountBalance}</p>
                <h3>Charge credit</h3>
                <Button
                    onClick={async () => {
                        chargeUserAccount();
                        await this.loadProfile();
                    }}
                >
                    Charge 50€
                </Button>
                <div>
                    {this.state.profile.completedTransactions.map(element => (
                        <div
                            key={this.state.profile.completedTransactions.indexOf(
                                element
                            )}
                        >
                            <h5>
                                Transaction #
                                {this.state.profile.completedTransactions.indexOf(
                                    element
                                )}
                            </h5>
                            <p>Sender: {element.sender}</p>
                            <p>Receiver: {element.receiver}</p>

                            <p>Amount: {element.amount}</p>
                        </div>
                    ))}
                </div>
            </article>
        );
    }
}
