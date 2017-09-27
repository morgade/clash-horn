import React from 'react';
import CSSTransition  from 'react-transition-group/CSSTransition';
import TransitionGroup  from 'react-transition-group/TransitionGroup';
import { connect } from 'react-redux';

import WarQueueItem from './WarQueueItem.jsx'
import { getFilteredAttackQueue } from '../../../war-plan'

/**
 * WarQueue label
 */
class WarQueue extends React.Component {
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        const queue = getFilteredAttackQueue(this.props.war, this.props.position);
        
        return (
            <div className="wp-queue">
                <TransitionGroup>
                {queue.map( (plannedAttack, idx) =>
                    <CSSTransition key={plannedAttack.attacker} classNames="wp-queue-anim" timeout={300}>
                        <WarQueueItem war={this.props.war} position={this.props.position} plannedAttack={plannedAttack} index={idx} />
                    </CSSTransition>
                )}
                </TransitionGroup>
            </div>
        );
    }
};

WarQueue.defaultProps = {
    position: null,
    war: null
};

export default connect(
    (store) => ({    })
)(WarQueue);