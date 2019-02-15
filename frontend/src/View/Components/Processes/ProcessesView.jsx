import React from "react";
import ProcessItem from "./ProcessItem/ProcessItem";

const mock = {
    title:"Der Gerät 9000",
    status:"Open",
    returnDate:"24.02.2019"
}

export default class ProcessesView extends React.Component {
    render () {
        return (
            <>
                <div id="testtext">
                    <ProcessItem data={mock}/>
                    <ProcessItem data={mock}/>
                    <ProcessItem data={mock}/>
                    <ProcessItem data={mock}/>
                    <ProcessItem data={mock}/>
                    <ProcessItem data={mock}/>
                </div>
            </>
        );
    }
}