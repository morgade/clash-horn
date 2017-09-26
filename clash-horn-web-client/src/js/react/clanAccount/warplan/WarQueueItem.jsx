import React from 'react';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';

import { connect } from 'react-redux';

import { removeFromAttackQueue } from '../../../flux/actions/clans'

/**
 * WarQueueItem label
 */
class WarQueueItem extends React.Component {
    
    removeFromQueue() {
        if (!this.isRemoving()) {
            this.props.dispatch(
                removeFromAttackQueue(this.props.war.id, this.props.position.number, this.attackerPosition())
            );
        }
    }
    
    attackerPosition() {
        return this.props.position.attackQueue[this.props.queueIndex].attacker;
    }
    
    isRemoving() {
        return this.props.fetching['removeFromAttackQueue'] 
                            && this.props.fetching['removeFromAttackQueue'][1]==this.props.position.number
                            && this.props.fetching['removeFromAttackQueue'][2]==this.attackerPosition();
    }
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        const attacker = this.props.war.positions[this.attackerPosition()-1].member;
        return (
            <span>
                {this.props.queueIndex>0 ? <Glyphicon glyph="chevron-left" /> : null}
                
                <a  className={`wp-queue-item ${this.isRemoving()?"wp-queue-item-removing":""}`}
                    onClick={this.removeFromQueue.bind(this)}>

                    {attacker.name}
                    {' '}
                    
                    
                    {this.isRemoving()?
                        <Glyphicon glyph="refresh" />
                    :
                        <Glyphicon glyph="remove" />
                    }
                </a>
            </span>
        );
    }
};

WarQueueItem.defaultProps = {
    war: null,
    queueIndex: -1,
    position: 0
};

export default connect(
    (store) => ({
        fetching: store.clans.fetching
    })
) (WarQueueItem);