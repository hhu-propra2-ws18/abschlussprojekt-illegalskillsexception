import React from "react";
import BorrowItemComponent from "../BorrowItemComponent/BorrowItemComponent";

const items = [{
    title: "Der Gerät 9000",
    dailyRate: "3,50 €",
    deposit: "500€",
    place: "Dönerbude um die Ecke"
}];

export default class BorrowView extends React.Component {
    render() {
        return (
            <>
                <div id="borrow-grid">
                {
                    items.map((dataItem)=> 
                        <BorrowItemComponent key={dataItem.title} data={dataItem}></BorrowItemComponent>
                    )
                }
                </div>
            </>
        );
    }
}
