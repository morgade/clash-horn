import React from 'react';

import CSSTransition  from 'react-transition-group/CSSTransition';
import TransitionGroup  from 'react-transition-group/TransitionGroup';

import TitleBar from './ui/titlebar/TitleBar.jsx';
import Notification from './ui/notification/Notification.jsx';

import ClanAccountManager from './clanAccount/ClanAccountManager.jsx';
import RegisterManager from './register/RegisterManager.jsx';


import { Switch, Route, Redirect } from 'react-router-dom'

class App extends React.Component {
    render() {
        return (
            <div>
                <Route path="/" component={TitleBar} />
                <div className="container">
                    <Switch>
                        <Redirect exact from="/" to="/register"  />
                        <Route exact path="/register" component={RegisterManager} />
                        <Route path="/:cid" component={ClanAccountManager}  />
                    </Switch>
                </div>
                <Notification />
            </div>
        );
    }
};

export default App;