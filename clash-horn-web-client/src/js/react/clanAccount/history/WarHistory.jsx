import React from 'react';
import { connect } from 'react-redux';

export class WarHistory extends React.Component {
   
    render() {
        return (
                <div>
                    War Plan History
                </div>
        );
    }
};

export default connect(
    (store) => ({
        clanAccount: store.clans.clanAccount
    })
)(WarHistory);
