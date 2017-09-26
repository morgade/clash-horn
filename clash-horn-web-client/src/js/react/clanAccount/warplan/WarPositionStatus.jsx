import React from 'react';
import Col from 'react-bootstrap/lib/Col';
import Row from 'react-bootstrap/lib/Row';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import Tooltip from 'react-bootstrap/lib/Tooltip';
import OverlayTrigger from 'react-bootstrap/lib/OverlayTrigger';
import Modal from 'react-bootstrap/lib/Modal';
import Label from 'react-bootstrap/lib/Label';
import Button from 'react-bootstrap/lib/Button';
import ButtonGroup from 'react-bootstrap/lib/ButtonGroup';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import FormControl from 'react-bootstrap/lib/FormControl';

import { bestPerformedAttack } from '../../../war-plan';

/**
 * WarPositionStatus label
 */
class WarPositionStatus extends React.Component {
    
    stars() {
        if (this.props.position.performedAttacks.length===0) {
            return -1;
        } else {
            return this.props.position.performedAttacks.map( a => a.stars ).reduce( (o,n) => Math.max(o,n));
        }
    }
    
    tooltip(text) {
        return <Tooltip id="tooltip">{text}</Tooltip>;
    }
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        let war = this.props.war;
        let position = this.props.position;
        let stars = this.stars();
        let bestAttack = bestPerformedAttack(position);
        let bestPerformedAttackTip = "";    
        
    
        let bestPerformedAttackContent = null;
        if (bestAttack===0) {
            bestPerformedAttackTip = "No destruction yet";
            bestPerformedAttackContent = (<Glyphicon glyph="asterisk" />);
        } else if (bestAttack===100) {
            bestPerformedAttackTip = "Position cleared!";
            bestPerformedAttackContent = (<Glyphicon glyph="ok" />); 
        } else {
            bestPerformedAttackTip = `Best attack: ${bestAttack}%`;
            bestPerformedAttackContent = bestAttack;
        }
        
        
        
        return (
            <div className={`wps ${this.props.className}`}>
                <span className={`wps-stars wps-stars-${stars}`} />
                <span className={`wps-th wps-th-${stars<3 ? position.enemy.townhallLevel : 'd'}`} />
                <OverlayTrigger overlay={this.tooltip(bestPerformedAttackTip)}>
                    <span className={`wps-destruction wps-destruction-${bestAttack>=100?'done':bestAttack>0?'incomplete':'not-done'}`}>
                        {bestPerformedAttackContent}
                    </span>
                </OverlayTrigger>
            </div>
        );
    }
};

WarPositionStatus.defaultProps = {
    war: null,
    position: null
};

export default WarPositionStatus;