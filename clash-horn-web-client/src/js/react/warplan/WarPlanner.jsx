import React from 'react';
import { connect } from 'react-redux'


export class WarPlanner extends React.Component {
    
    render() {
        return (
            <div>
                This is the war plan root component
            </div>
        );
    }
};

export default connect()(WarPlanner);
