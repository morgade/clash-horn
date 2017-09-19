import React from 'react';
import {connect} from 'react-redux';

import NotificationSystem from 'react-notification-system';

class Notification extends React.Component {
    componentDidUpdate() {
        if (this.props.notification) {
            this.notificationSystem.addNotification(this.props.notification);
        }
    }
    
    componentDidMount() {
        this.notificationSystem = this.refs.notificationSystem;
    }
    
    render() {
        return (
            <NotificationSystem ref="notificationSystem" />
        );
    }
};

export default connect( state => ({notification: state.notification}) )(Notification);