import React from "react"
import {connect} from "react-redux";
import {getAllConflicts, punishConflict, resolveConflict} from "../../../../Services/Conflict/conflictCompleteService";
import ConflictDetailView from "./ConflictDetailView";

class ConflictView extends React.Component {

    componentWillMount() {
        getAllConflicts();
    }

    punishConf = async (id) => {
        await punishConflict(id);
        await getAllConflicts();
    };

    resolveConf = async (id) => {
        await resolveConflict(id);
        await getAllConflicts();
    };

    renderConflictList() {
        return this.props.conflictList.map(conf =>
            <ConflictDetailView
                key={conf.id}
                conflict={conf}
                article={conf.inquiry.article}
                lender={conf.inquiry.article.owner}
                borrower={conf.inquiry.borrower}
                getAllConflicts={this.getAllConflicts}
                resolveConflict={this.resolveConf}
                punishConflict={this.punishConf}
            />
        );
    }

    render() {
        console.log("conflictList: ",this.props.conflictList);
        return (
            <>
                {this.renderConflictList()}
            </>
        )
    }
}
const mapStateToProps = (state) => {
    return { conflictList: state.conflictstore.conflictList };
};

export default connect(mapStateToProps)(ConflictView);