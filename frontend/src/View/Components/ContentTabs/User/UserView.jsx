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
                username: "String"
            }
        };
    }

    async loadProfile() {
        this.setState({
            profile: { username: "loading", accountBalance: "loading" }
        });
        let data = await getUserDetails();
        this.setState({ profile: data.data });
    }

    async componentDidMount() {
        await this.loadProfile();
    }

    render() {
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
            </article>
        );
    }
}
