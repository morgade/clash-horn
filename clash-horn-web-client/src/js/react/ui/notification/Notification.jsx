import React from 'react';
import {connect} from 'react-redux';

import NotificationSystem from 'react-notification-system';

class Notification extends React.Component {
    componentDidUpdate() {
        if (this.props.notification.message && this.props.notification.level) {
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

export default connect( 
    store => ({notification: store.notification}) 
)(Notification);