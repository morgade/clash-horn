import React from 'react';

import TitleBar from './ui/titlebar/TitleBar.jsx';
import Notification from './ui/notification/Notification.jsx';
import ClanAccountNavigator from './ui/ClanAccountNavigator.jsx';
import RegisterManager from './register/RegisterManager.jsx';

import { Switch, Route, IndexRedirect, Redirect } from 'react-router-dom'

class App extends React.Component {
    render() {
        return (
            <div>
                <TitleBar />
                <div className="container">
                    <Switch>
                        <Redirect exact from="/" to="/register"  />
                        <Route exact path="/register" component={RegisterManager} />
                        <Route path="/:cid" component={ClanAccountNavigator} />
                    </Switch>
                </div>
                <Notification />
            </div>
        );
    }
};

export default App;