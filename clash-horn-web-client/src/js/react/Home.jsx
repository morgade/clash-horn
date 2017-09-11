import React from 'react';
import { connect, dispatch } from 'react-redux'
import Jumbotron from 'react-bootstrap/lib/Jumbotron';
import Button from 'react-bootstrap/lib/Button';

import ServerHeap from './server-heap/ServerHeap.jsx';
import * as Actions from '../flux/actions'

class Home extends React.Component {
    testFetch() {
    }
    
    render() {
        return (
            <div>
                <Jumbotron>
                    <h1>MIND THE HIPPO PROJECT</h1>
                    <p>This is our project scaffold !</p>
                    <p>
                        <Button bsStyle="primary" onClick={this.testFetch}>
                            FETCH TEST 
                        </Button>
                    </p>
                </Jumbotron>
                
                <ServerHeap />
            </div>
        );
    }
};

export default Home;