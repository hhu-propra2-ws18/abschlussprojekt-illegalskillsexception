import React from "react";
import LendViewHeader from "./LendViewHeader/LendViewHeader";
import LendItemComponent from "./LendItemComponent/LendItemComponent";



import "./LendView.css";

const items = [
    {
        title: "Der Gerät 9000",
        dailyRate: "3.50",
        deposit: "500",
        place: "Dönerbude um die Ecke"
    }
];

export default class LendView extends React.Component {
    render() {
        return (
            <div id="lendview-container">

                <LendViewHeader />
                <div id="lend-grid">
                    {items.map(dataItem => (
                        <LendItemComponent
                            key={dataItem.title}
                            data={dataItem}
                        />
                    ))}
                </div>
            </div>
        );
    }
}
