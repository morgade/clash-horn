import Index from 'file?name=[name].[ext]!../index.html';
import OurCSS from '../less/mth.less';
import Bootstrap from 'bootstrap/dist/css/bootstrap.min.css';

import React from 'react';
import ReactDom from 'react-dom';
import {Router, Route, IndexRedirect, Redirect, hashHistory} from 'react-router'
import { Provider } from 'react-redux'

import App from './react/App.jsx';
import Home from './react/Home.jsx';
import GroupManager from './react/group/GroupManager.jsx';
import ClanManager from './react/group/ClanManager.jsx';
import RegisterManager from './react/register/RegisterManager.jsx';
import WarPlanner from './react/warplan/WarPlanner.jsx';
import store from './flux/store'

require('es6-promise').polyfill();

/**
 *  Default path routes to RegisterManager at "/register"
 *  
 *  Any other first level path is treated is an account id
 *     /guilaaf -> GroupManager
 *     /guilaaf/2YPLGY2 -> ClanManager
 *     /guilaaf/2YPLGY2/current -> WarPlanner for a clan loading or creating current war
 *     /guilaaf/2YPLGY2/history -> WarPlanner for a clan loading past wars
 */
ReactDom.render(
        <Provider store={store}>
            <Router history={hashHistory}>
                <Route path="/" component={App}>
                    <IndexRedirect to="register"  />
                    <Route path="register" component={RegisterManager} />
                    <Route path=":gid" component={GroupManager} />
                    <Route path=":gid/:clanTag" component={ClanManager} />
                    <Route path=":gid/:clanTag/current" component={WarPlanner} />
                    <Route path=":gid/:clanTag/history" component={WarPlanner} />
                    <Redirect from="*" to="register" />
                </Route>
            </Router> 
        </Provider>
, document.getElementById('app'));