import React from 'react';
import Grid from 'react-bootstrap/lib/Grid'
import Row from 'react-bootstrap/lib/Row'
import Col from 'react-bootstrap/lib/Col'
import Image from 'react-bootstrap/lib/Image'
import { connect } from 'react-redux'

import th9 from '../../../img/th9-small.png'

export class WarPlanner extends React.Component {
    
    render() {
        return (
            <div>
                TJF x DARK KINGS
                <Grid fluid>
                    <Row className="show-grid">
                        <Col xs={12} md={12}>
                            <span className="position">1</span>
                            <Image src={th9} rounded/>
                            <span className="villageName">DARK SLAYER</span>
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
};

export default connect()(WarPlanner);
