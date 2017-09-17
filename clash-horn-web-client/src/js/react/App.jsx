import React from 'react';

import TitleBar from './titlebar/TitleBar.jsx';
import Notification from './notification/Notification.jsx';
import ClanManager from './clans/ClanManager.jsx';
import RegisterManager from './register/RegisterManager.jsx';
import WarPlanner from './warplan/WarPlanner.jsx';

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
                        <Route exact path="/:cid" component={ClanManager} />
                        <Route path="/:cid/:wid" component={WarPlanner} />
                    </Switch>
                </div>
                <Notification />
            </div>
        );
    }
};

export default App;