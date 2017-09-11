import React from 'react';

import TitleBar from './titlebar/TitleBar.jsx';
import Notification from './notification/Notification.jsx';

class App extends React.Component {
    render() {
        return (
            <div>
                <TitleBar />
                <div className="container">
                    {this.props.children}
                </div>
                <Notification />
            </div>
        );
    }
};

export default App;