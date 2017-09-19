import React from 'react';

import TitleBar from './ui/titlebar/TitleBar.jsx';
import Notification from './ui/notification/Notification.jsx';
import RegisterManager from './register/RegisterManager.jsx';
import ClanManager from './clans/ClanManager.jsx';
import WarPlanner from './warplan/WarPlanner.jsx';
import WarHistory from './warhistory/WarHistory.jsx';

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
                        <Route exact path="/:cid/history" component={WarHistory} />
                        <Route exact path="/:cid/:wid" component={WarPlanner} />
                    </Switch>
                </div>
                <Notification />
            </div>
        );
    }
};

export default App;