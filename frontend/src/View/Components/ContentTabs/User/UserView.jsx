import React from "react";
import {
    getUserDetails,
    addMoneyBalanceToUserAccount
} from "../../../../Services/User/authentificationCompleteService";
import Button from "react-uwp/Button";

export default class UserView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            profile: {
                accountBalance: "Propay information currently not available",
                username: "",
                email: "",
                completedTransactions: []
            }
        };
    }

    async loadProfile() {
        this.setState({
            profile: {
                accountBalance: "Loading ...",
                username: "Loading ...",
                email: "Loading ...",
                completedTransactions: []
            }
        });
        let data = await getUserDetails();
        if (!data.error) {
            this.setState({ profile: data.data });
        } else {
            this.setState({
                profile: {
                    accountBalance:
                        "Propay information currently not available",
                    username: data.data.username,
                    email: data.data.email,
                    completedTransactions: data.data.completedTransactions
                }
            });
        }
    }

    async componentDidMount() {
        await this.loadProfile();
    }

    render() {
        return (
            <div>
                <article>
                    <h2>Profile</h2>
                    <div className="two-item-grid">
                        <p>
                            <span className="underline">Username:</span>{" "}
                            {this.state.profile.username}
                        </p>
                        <p>
                            <span className="underline">E-mail:</span>{" "}
                            {this.state.profile.email}
                        </p>
                    </div>
                </article>
                <article>
                    <h2>Propay</h2>
                    <div className="two-item-grid">
                        <p style={{ marginTop: 11 }}>
                                <span className="underline">
                                    Account balance:
                                </span>{" "}
                                <span>{this.state.profile.accountBalance}</span>
                        </p>
                        <p>
                            <span className="">Add credit:</span>
                            <Button
                                onClick={async () => {
                                    addMoneyBalanceToUserAccount();
                                    await this.loadProfile();
                                }}
                            >
                                Add 250€
                            </Button>
                        </p>
                    </div>
                    {this.state.profile.completedTransactions.length !== 0 ? (
                        <h3 style={{}} className="underline">
                            Money transactions:
                        </h3>
                    ) : null}
                    <div className="grid-article-view">
                        {this.state.profile.completedTransactions.map(
                            element => (
                                <article
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
                                    <p>
                                        <span className="underline">
                                            Sender:
                                        </span>{" "}
                                        {element.sender}
                                    </p>
                                    <p>
                                        <span className="underline">
                                            Receiver:
                                        </span>{" "}
                                        {element.receiver}
                                    </p>
                                    <p>Amount: {element.amount}</p>
                                </article>
                            )
                        )}
                    </div>
                </article>
            </div>
        );
    }
}
