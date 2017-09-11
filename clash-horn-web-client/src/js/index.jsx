import Index from 'file?name=[name].[ext]!../index.html';
import OurCSS from '../less/mth.less';
import Bootstrap from 'bootstrap/dist/css/bootstrap.min.css';

import React from 'react';
import ReactDom from 'react-dom';
import {Router, Route, IndexRedirect, Redirect, hashHistory} from 'react-router'
import { Provider } from 'react-redux'

import App from './react/App.jsx';
import Home from './react/Home.jsx';
import CommentCrud from './react/comment-crud/CommentCrud.jsx';
import store from './flux/store'


require('es6-promise').polyfill();

ReactDom.render(
        <Provider store={store}>
            <Router history={hashHistory}>
                <Route path="/" component={App}>
                    <IndexRedirect to="home" />
                    <Route path="home" component={Home} />
                    <Route path="comment-crud" component={CommentCrud}>
                        <Route path=":id" component={CommentCrud} />
                    </Route>
                    <Redirect from="*" to="home" />
                </Route>
            </Router> 
        </Provider>
, document.getElementById('app'));