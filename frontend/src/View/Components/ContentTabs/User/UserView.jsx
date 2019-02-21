import React from "react";
import { getUserDetails } from "../../../../Services/User/authentificationCompleteService";

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

    async componentDidMount() {
        let data = await getUserDetails();
        this.setState({ profile: data });
    }

    render() {
        return (
            <div>
                <h3>Username</h3>
                <p>{this.state.profile.username}</p>
                <h3>Balance</h3>
                <p>{this.state.profile.accountBalance}</p>
            </div>
        );
    }
}
